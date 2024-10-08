package com.example.tobi.springbootbasic.controller;

import com.example.tobi.springbootbasic.dto.MemberCreateRequestDTO;
import com.example.tobi.springbootbasic.dto.MemberResponseDTO;
import com.example.tobi.springbootbasic.model.User;
import com.example.tobi.springbootbasic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users") //무조건 8080/user 붙이게 주소설정
@RequiredArgsConstructor //final놈 생성자자동생성
public class UserController {
    private final UserService userService;

    @GetMapping
    public String findAllUsers(Model model) {
        List<MemberResponseDTO> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "sign-up";
    }

    @PostMapping("/register")
    public String createUser(@RequestBody MemberCreateRequestDTO request) {
        userService.createUser(request.toUser());
        return "redirect:/users";
    }

//    @GetMapping("/update/{id}")
//    public String updateForm(@PathVariable Long id, Model model) {
//        System.out.println("id::"+id);
//    return "user-update";
//    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        // 로직을 추가... -> id를 조건으로 조회한 데이터를
        // 프론트(수정)화면에 뿌릴 것.
        MemberResponseDTO user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

//    @GetMapping("/delete/{id}")
//    public String deleteForm(@PathVariable Long id, Model model) {
//        // 로직을 추가... -> id를 조건으로 조회한 데이터를
//        // 프론트(수정)화면에 뿌릴 것.
//        MemberResponseDTO user = userService.deleteById(id);
//        model.addAttribute("user", user);
//        return "user-delete";
//    } 칸에 값은 불러와짐

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id, Model model) {
        MemberResponseDTO user = userService.deleteById(id);
        model.addAttribute("user", user);
        return "user-delete";
    }
}