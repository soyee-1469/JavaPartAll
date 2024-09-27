package com.example.tobi.springbootbasic.dto;

import lombok.*;

//노출시키고싶은항목만 가지고올파일
@Getter
@Builder //*******************
@AllArgsConstructor

    public class MemberResponseDTO {
        private Long id;
        private String name;
        private String email;
        private String userid; //대소문자 구분안함, 구분하려면 _
        private String password;


    }

