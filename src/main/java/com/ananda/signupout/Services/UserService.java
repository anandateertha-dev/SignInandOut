package com.ananda.signupout.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ananda.signupout.Repository.UserRepository;
import com.ananda.signupout.StaticInfo.StaticInfos;
import com.ananda.signupout.model.User;
import com.ananda.signupout.model.VerifyUser;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Object> userAddService(User user) {
        try {
            if ((user.getUserName() != "") & (user.getEmail() != "")) {
                User userByEmail = userRepository.findByEmail(user.getEmail());
                if (userByEmail == null) {
                    userRepository.save(user);
                    return ResponseEntity.ok("User added");
                } else {
                    return ResponseEntity.ok("User with this email already exists!");
                }
            } else {
                return ResponseEntity.badRequest().body("Invalid user name or email");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal Server Error!");
        }
    }

    public ResponseEntity<String> verifyingTheUserService(VerifyUser verifyUser) {
        try {
            User user = userRepository.findByEmail(verifyUser.getEmail());
            if (user != null) {
                if (user.getPassword().equals(verifyUser.getPassword())) {
                    StaticInfos.loginStatus=true;
                    System.out.println(StaticInfos.loginStatus);
                    return ResponseEntity.ok().body("Logged in Successfully");
                } else {
                    return ResponseEntity.badRequest().body("Invalid email or password");
                }
            } else {
                return ResponseEntity.badRequest().body("Invalid email or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal Server Error!");
        }
    }
}
