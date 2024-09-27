package com.example.tobi.springbootbasic.model;

import com.example.tobi.springbootbasic.dto.MemberResponseDTO;
import lombok.*;

@Getter
@Setter
@Builder //생성자대신 쓰기위해
public class User {
    // lombok이 생성자들 알아서 만들어줌
    //    @NoArgsConstructor //기본생성자
    //    @AllArgsConstructor //모든생성자
    //    @RequiredArgsConstructor //final 있는놈꺼

    private Long id;
    private String name;
    private String email;
    private String userid; //대소문자 구분안함, 구분하려면 _
    private String password;

    public MemberResponseDTO toMemberResponseDTO() {
        return MemberResponseDTO.builder()
                .id(id)
                .name(name)
                .email(email)
                .userid(userid)
                .password(password)
                .build();
    }
}
