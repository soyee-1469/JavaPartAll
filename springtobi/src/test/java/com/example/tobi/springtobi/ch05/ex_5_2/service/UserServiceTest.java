package com.example.tobi.springtobi.ch05.ex_5_2.service;

import com.example.tobi.springtobi.ch05.ex_5_2.dao.DaoFactory;
import com.example.tobi.springtobi.ch05.ex_5_2.dao.UserDao;
import com.example.tobi.springtobi.ch05.ex_5_2.domain.Level;
import com.example.tobi.springtobi.ch05.ex_5_2.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DaoFactory.class)
class UserServiceTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;
    List<User> users;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @BeforeEach
    public void setUp() {
        userDao.deleteAll();
        users = Arrays.asList(
                new User("bumjin", "박범진", "p1", Level.BASIC, 49, 0),
                new User("joytouch", "강명성", "p2", Level.BASIC, 50, 0),
                new User("erwins", "신승한", "p3", Level.SILVER, 60, 29),
                new User("madnite1", "이상호", "p4", Level.SILVER, 60, 30),
                new User("green", "오민규", "p5", Level.GOLD, 100, 100)
        );
    }

    @Test
    public void upgradeLevels_메서드_테스트() {
        // given
        for (User user : users) {
            userDao.add(user);
        }

        // when
        userService.upgradelevels();


        // then
        checkLevel(users.get(0), Level.BASIC);
        checkLevel(users.get(1), Level.SILVER);
        checkLevel(users.get(2), Level.SILVER);
        checkLevel(users.get(3), Level.GOLD);
        checkLevel(users.get(4), Level.GOLD);

    }

    @Test
    void upgradeAllOrNothing() {
        UserService testUserService = new UserService.TestUserService(users.get(3).getId());
        testUserService.setUserDao(userDao);
        for (User user : users) {
            userDao.add(user);
        }
        System.out.println(userDao.get("joytouch").getLevel()); //basic
        try {
            testUserService.upgradelevels(); //basic silver
        } catch (Exception e) {
            System.out.println(userDao.get("joytouch").getLevel()); //silver


        }
    }

    @Test //하나라도 충족못하면 전체 수정안되게 개선
    void upgradeAllOrNothingV2() {
        UserService testUserService = new UserService.TestUserService(users.get(3).getId());
        testUserService.setUserDao(userDao);
        testUserService.setDataSource(dataSource);
        for (User user : users) {
            userDao.add(user);
        }
        System.out.println(userDao.get("joytouch").getLevel()); //basic
        try {
            testUserService.upgradelevelsV2(); //basic basic
        } catch (Exception e) {
            System.out.println(userDao.get("joytouch").getLevel()); //basic,basic
        }
    }
    void upgradeAllOrNothingV3() {
        UserService testUserService = new UserService.TestUserService(users.get(3).getId());
        testUserService.setUserDao(userDao);
        testUserService.setDataSource(dataSource);
        for (User user : users) {
            userDao.add(user);
        }
        System.out.println(userDao.get("joytouch").getLevel()); //basic
        try {
            testUserService.upgradelevelsV3(); //basic basic
        } catch (Exception e) {
            System.out.println(userDao.get("joytouch").getLevel()); //basic,basic
        }
    }
    void upgradeAllOrNothingV4() {
        UserService testUserService = new UserService.TestUserService(users.get(3).getId());
        testUserService.setUserDao(userDao);
        testUserService.setDataSource(dataSource);
        testUserService.setPlatformTransactionManager(transactionManager);
        for (User user : users) {
            userDao.add(user);
        }
        System.out.println(userDao.get("joytouch").getLevel()); //basic
        try {
            testUserService.upgradelevelsV4(); //basic basic
        } catch (Exception e) {
            System.out.println(userDao.get("joytouch").getLevel()); //basic,basic
        }
    }

    private void checkLevel(User user, Level expectedLevel) {
        User userUpdate = userDao.get(user.getId());
        assertThat(userUpdate.getLevel()).isEqualTo(expectedLevel);
    }

}