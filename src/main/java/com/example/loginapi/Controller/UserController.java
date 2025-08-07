package com.example.loginapi.Controller;

import com.example.loginapi.DTO.Request.LoginRequest;
import com.example.loginapi.DTO.Request.SignUpRequest;
import com.example.loginapi.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/login") // 로그인 url설정 뒤 LoginService롸 매핑시켜준다.
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest) {
        String result = userService.login(loginRequest);
        return ResponseEntity.ok(result); // 여기서 ok는 200의 응답을 한다는 의미이다.
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignUpRequest signUpRequest) {
        userService.signup(signUpRequest);
        return ResponseEntity.ok("회원가입에 성공하였습니다.");
    }
}
