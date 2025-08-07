package com.example.loginapi.Service;

import com.example.loginapi.DTO.Request.LoginRequest;
import com.example.loginapi.DTO.Request.SignUpRequest;
import com.example.loginapi.Entity.User;
import com.example.loginapi.Repository.UserRepository;
import com.example.loginapi.exception.NoWordException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("사용자이름을 찾을 수 없습니다."));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return "로그인 성공";
    }

    public void signup(SignUpRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        List<String> noWord = List.of("니애미", "대충 욕", "병신", "애미", "씨발", "개새끼", "좆");

        for(String word : noWord){
            if(username.contains(word)){
                throw new NoWordException("사용자 이름", word);
            }
            else if(password.contains(word)){
                throw new NoWordException("비밀번호", word);
            }
        }

        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 사용자 이름입니다.");
        }

        String encodePassword = passwordEncoder.encode(request.getPassword());
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encodePassword);

        userRepository.save(user);
    }
}
