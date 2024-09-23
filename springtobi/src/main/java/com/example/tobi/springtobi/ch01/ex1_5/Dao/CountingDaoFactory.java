package com.example.tobi.springtobi.ch01.ex1_5.Dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {

    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new CountingConnectionMaker( realConnection() );
    }

    @Bean
    public ConnectionMaker realConnection() {
        return new DConnectionMaker();
    }

}
