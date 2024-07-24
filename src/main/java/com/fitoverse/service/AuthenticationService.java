package com.fitoverse.service;

import com.fitoverse.repository.UserRepository;
import com.fitoverse.entity.User;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Builder
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TwilioService twilioService;

    public void sendOtp(String phoneNumber) {
        String otp = String.valueOf(new Random().nextInt(999999));

        User user = userRepository.findByPhoneNumber(phoneNumber).orElse(new User());
        user.setOtp(otp);
        user.setPhoneNumber(phoneNumber);
        user.setOtp(otp);
        user.setVerified(false);
        user.setExpiresAt(LocalDateTime.now().plusMinutes(5)); // Example expiration time
        userRepository.save(user);

        twilioService.sendOtp(phoneNumber, otp);
    }

    public boolean verifyOtp(String phoneNumber, String otp) {
        User user = userRepository.findByPhoneNumber(phoneNumber).orElse(null);
        if (user != null && otp.equals(user.getOtp()) && LocalDateTime.now().isBefore(user.getExpiresAt())) {
            user.setLastLogin(LocalDateTime.now());
            user.setOtp(null); // Clear OTP after successful login
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public void logout(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber).orElse(null);
        if (user != null) {
            user.setLastLogout(LocalDateTime.now());
            userRepository.save(user);
        }
    }
}
