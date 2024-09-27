package com.example.tobi.springtobi.ch05.ex_5_2.service;

import com.example.tobi.springtobi.ch05.ex_5_2.dao.UserDao;
import com.example.tobi.springtobi.ch05.ex_5_2.domain.Level;
import com.example.tobi.springtobi.ch05.ex_5_2.domain.User;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
    public static final int MIN_RECCOMMEND_FOR_GOLD = 30;

    private DataSource dataSource;
    private UserDao userDao;
    private PlatformTransactionManager transactionManager;

    public void setPlatformTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }



    public UserService(String id) {
    }

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) {
        if (user.getLevel() == null) {
            user.setLevel(Level.BASIC);
        }
        userDao.add(user);
    }

    public void upgradelevels() {
        List<User> users = userDao.getAll();

        for (User user : users) {
            if (canUpgradeLevel(user)) {
                // upgrade
                upgradeLevel(user);
            }
        }
    }

    public void upgradelevelsV2() throws SQLException { //하나라도 실패하면 몽땅 처리하지말어라~~
        TransactionSynchronizationManager.initSynchronization();
        Connection c = DataSourceUtils.getConnection(dataSource);
        //Connection 은 dataSoucrcUtils에 종속되서 유연하지 않다. 걷어내야함
        c.setAutoCommit(false); //기본은 true임 일부러불러와서 false로 만들고 커스터마이징함 > 트랜잭션경계설정

        try {
            List<User> users = userDao.getAll();

            for (User user : users) {
                if (canUpgradeLevel(user)) {
                    // upgrade
                    upgradeLevel(user);
                }
            }//아직 db에 확정이 안된상황
            c.commit(); //정상처리되었으니 반영해라~~ basic > silver , silver > gold
        } catch (Exception e) {
            c.rollback();
            throw e;
        } finally {
            DataSourceUtils.releaseConnection(c, dataSource);
            TransactionSynchronizationManager.unbindResource(dataSource);
            TransactionSynchronizationManager.clearSynchronization();
        }
    }

    public void upgradelevelsV3() throws SQLException {
        PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            List<User> users = userDao.getAll();

            for (User user : users) {
                if (canUpgradeLevel(user)) {
                    // upgrade
                    upgradeLevel(user);
                }
            }//아직 db에 확정이 안된상황
            transactionManager.commit(status); //정상처리되었으니 반영해라~~ basic > silver , silver > gold
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }
    public void upgradelevelsV4() throws SQLException {
        TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            List<User> users = userDao.getAll();

            for (User user : users) {
                if (canUpgradeLevel(user)) {
                    // upgrade
                    upgradeLevel(user);
                }
            }//아직 db에 확정이 안된상황
            this.transactionManager.commit(status); //정상처리되었으니 반영해라~~ basic > silver , silver > gold
        } catch (Exception e) {
            this.transactionManager.rollback(status);
            throw e;
        }
    }
    private boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevel();

        switch (currentLevel) {
            case BASIC:
                return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
            case SILVER:
                return (user.getRecommend() >= MIN_RECCOMMEND_FOR_GOLD);
            case GOLD:
                return false;
            default:
                throw new IllegalStateException("Unexpected value: " + currentLevel);
        }
    }

    protected void upgradeLevel(User user) {
        user.upgradeLevel();
        userDao.update(user);
    }

    static class TestUserService extends UserService {

        private String id;

        public TestUserService(String id) {
            super(id);
            this.id = id;
        }

        @Override
        protected void upgradeLevel(User user) {
            if (user.getId().equals(id)) {
                throw new TestUserServiceException();
            }
            super.upgradeLevel(user);
        }
    }

    static class TestUserServiceException extends RuntimeException {
    }

}