package com.example.tobi.springtobi.ch05.ex_5_2.service;

import com.example.tobi.springtobi.ch05.ex_5_2.dao.UserDao;
import com.example.tobi.springtobi.ch05.ex_5_2.domain.Level;
import com.example.tobi.springtobi.ch05.ex_5_2.domain.User;

import java.util.List;

public abstract class UserService {
    public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
    public static final int MIN_RECCOMMEND_FOR_GOLD = 30;

    private UserDao userDao;

    public UserService(String id) {
    }

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(User user) {
        if (user.getLevel() == null) {
            user.setLevel(Level.BASIC);
        }
        userDao.add(user);
    }

    public void upgradeLevels() {
        List<User> users = userDao.getAll();
        for (User user : users) {
            if (canUpgradeLevel(user)) {
                upgradeLevel(user);
            }
        }
    }

    private boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevel();
        switch (currentLevel) {
            case BASIC:
                return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
            case SILVER:
                return (user.getLogin() >= MIN_RECCOMMEND_FOR_GOLD);
            case GOLD:
                return false;
            default:
                throw new IllegalArgumentException("Unexpected value: " + currentLevel);
        }
    }

    protected void upgradeLevel(User user) {
        user.upgradeLevel();
        userDao.update(user);

    }

    protected abstract void upgradeLevels(User user);

    static class TestUserService extends UserService {
        private String id;


        public TestUserService(String id) {
            super(id);
            this.id = id;
        }

        @Override
        protected void upgradeLevels(User user) {
            if (user.getId().equals(id)) {
                throw new TextUserServiceException();
            }
        }
    }

    static class TextUserServiceException extends RuntimeException {

    }
}