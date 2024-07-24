package com.fitoverse.controller;

import com.fitoverse.service.AuthenticationService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Builder
@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String phoneNumber) {
        authenticationService.sendOtp(phoneNumber);
        return ResponseEntity.ok("OTP sent to your phone.");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String phoneNumber, @RequestParam String otp) {
        boolean isVerified = authenticationService.verifyOtp(phoneNumber, otp);
        if (isVerified) {
            return ResponseEntity.ok("Phone number verified.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP.");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam String phoneNumber) {
        authenticationService.logout(phoneNumber);
        return ResponseEntity.ok("Logout successfully");
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("login");
    }

    @GetMapping("/verifyOtp")
    public ModelAndView showVerifyOtpPage() {
        return new ModelAndView("verifyOtp");
    }
}
