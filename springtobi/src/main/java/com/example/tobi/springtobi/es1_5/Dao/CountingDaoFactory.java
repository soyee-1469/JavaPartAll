package com.example.tobi.springtobi.es1_5.Dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class CountingDaoFactory {
    @Bean
    public UserDao userDao() {
        return new UserDao(dataSource());
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new CountingConnectionMaker(realConnection());
    }
    @Bean
    public ConnectionMaker realConnection() {
        return new DConnectionMaker();
    }

    @Bean
    public AccountDao accountDao() {

        return new AccountDao(getConnection());
    }
    @Bean
    public MessageDao messageDao() {

        return new MessageDao(getConnection());
    }
    @Bean
    public ConnectionMaker getConnection() {

        return new DConnectionMaker();
    }
    @Bean
    public DataSource dataSource(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring_tobi");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        return dataSource;
    }
}
