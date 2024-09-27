package com.example.tobi.springbootbasic.dto;

import com.example.tobi.springbootbasic.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter//주소안에 들어있는 값이 찍힘
@Builder
public class MemberCreateRequestDTO {
    private String name;
    private String email;
    private String userid; //대소문자 구분안함, 구분하려면 _
    private String password;

    public User toUser() {
        return User.builder()
                .name(name)
                .email(email)
                .userid(userid)
                .password(password)
                .build();


    }
}
