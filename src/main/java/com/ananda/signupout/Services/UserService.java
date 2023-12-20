package com.ananda.signupout.Services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ananda.signupout.Repository.UserRepository;
import com.ananda.signupout.StaticInfo.StaticInfos;
import com.ananda.signupout.model.EmailModel;
import com.ananda.signupout.model.OtpUserModel;
import com.ananda.signupout.model.User;
import com.ananda.signupout.model.VerifyUser;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailModel emailModel;

    @Autowired
    private OtpUserModel otpUserModel;

    public ResponseEntity<Object> userAddService(User user) {
        try {
            if ((user.getUserName() != "") & (user.getEmail() != "")) {
                User userByEmail = userRepository.findByEmail(user.getEmail());
                if (userByEmail == null) {
                    String strong_salt = BCrypt.gensalt(10);
                    String encyptedPassword = BCrypt.hashpw(user.getPassword(), strong_salt);
                    user.setPassword(encyptedPassword);
                    userRepository.save(user);
                    return ResponseEntity.ok("Account Created!");
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
                if (BCrypt.checkpw(verifyUser.getPassword(), user.getPassword())) {
                    StaticInfos.loginStatus = true;
                    return ResponseEntity.ok().body("Logged in!");
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

    public ResponseEntity<Object> getUserDetailsByEmailService(String email) {
        try {
            User userByEmail = userRepository.findByEmail(email);
            if (userByEmail != null) {
                return ResponseEntity.ok(userByEmail);
            } else {
                return ResponseEntity.badRequest().body("Invalid email");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error!");
        }
    }

    public ResponseEntity<String> sendingEmailService(String email, OtpUserModel otpUserModel) {
        try {
            User userByEmail = userRepository.findByEmail(email);
            if (userByEmail != null) {
                otpUserModel.setEmail(email);
                int otp = StaticInfos.generateRandom6DigitNumber();
                otpUserModel.setOtp(otp);

                // Setting the Email by EmailModel

                emailModel.setRecipient(email);
                emailModel.setSubject("OTP for Resetting your password");
                emailModel.setMsgBody("Your OTP for resetting your password is " + Integer.toString(otp)
                        + ". It is valid only for 1 minute.");

                String response = emailService.sendSimpleMail(emailModel);

                return ResponseEntity.ok().body(response);
            } else {
                return ResponseEntity.badRequest().body("Invalid email");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error!");
        }
    }

    public ResponseEntity<String> verifyTheOtpEnteredByUser(String otpFromUser) {
        try {
            if (otpFromUser.equals(Integer.toString(otpUserModel.getOtp()))) {
                return ResponseEntity.ok().body("OTP Verified");
            } else {
                return ResponseEntity.badRequest().body("Invalid OTP, check your Email to get the 6-digit OTP");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error!");
        }
    }

    public ResponseEntity<String> forgotPasswordService(String email) {
        return sendingEmailService(email, otpUserModel);
    }

}
