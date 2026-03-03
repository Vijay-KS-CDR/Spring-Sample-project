package com.example.Notes.controller;

import com.example.Notes.models.User;
import com.example.Notes.repository.UserRepo;
import com.example.Notes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Notes.tokenManager.JwtUtil;

import java.util.Map;

@RestController
@RequestMapping("/authenticate")
public class authCon {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signin")
    public ResponseEntity<String> signUser(@RequestBody Map<String,String> body){
        String name=body.get("name");
        String pass=passwordEncoder.encode(body.get("pass"));
        if(userRepo.findByName(name).isPresent()){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body("Email alredy exist");
        }
        userService.create(User.builder().name(name).pass(pass).build());
        return new ResponseEntity("Successfully done",HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String,String> body){
            String name=body.get("name");
            String pass=body.get("pass");
            var user=userRepo.findByName(name);
            if(!user.isPresent()){
                return  ResponseEntity.status(HttpStatus.CONFLICT).body("Email not exist");
            }
            User s=user.get();
            if(!passwordEncoder.matches(pass,s.getPass())){
                return new ResponseEntity<>("pass miss match",HttpStatus.UNAUTHORIZED);
            }
            String token=jwtUtil.generate(name);
            return ResponseEntity.ok(Map.of("token",token));
    }
}
