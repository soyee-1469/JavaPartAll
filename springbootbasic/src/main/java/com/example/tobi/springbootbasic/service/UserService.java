package com.example.tobi.springbootbasic.service;

import com.example.tobi.springbootbasic.dto.MemberResponseDTO;
import com.example.tobi.springbootbasic.mapper.UserMapper;
import com.example.tobi.springbootbasic.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //스트링부트 관리대상 >@Component 들어있는걸 다 스캔함 @service로 뺀 이유 용도구분 & @Controller랑 동일
@RequiredArgsConstructor //생성자 자동생성 >
public class UserService {
    private final UserMapper userMapper;

    public List<MemberResponseDTO> findAll() {
        List<User> users = userMapper.findAll();
     return users.stream() //리스트 쭉 빨아들임
//           .map(user -> user.tomemberListResponseDTO()) 방법1
             .map(User::toMemberResponseDTO) //방법2 람다표현식
             //매핑한 결과물을 아래로 보내기 ↓
             .collect(Collectors.toList());
    }
    public void createUser(User user) {
        userMapper.insertUser(user);
    }

    public MemberResponseDTO findById(Long id){
       return userMapper.findById(id).toMemberResponseDTO();
    }

    public MemberResponseDTO deleteById(Long id){
        return userMapper.deleteById(id).toMemberResponseDTO();
    }
}
