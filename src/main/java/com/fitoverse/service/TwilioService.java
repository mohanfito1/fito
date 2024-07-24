package com.fitoverse.service;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    private final String twilioPhoneNumber;

    @Autowired
    public TwilioService(
            @Value("${twilio.account-sid}") String accountSid,
            @Value("${twilio.auth-token}") String authToken,
            @Value("${twilio.phone-number}") String twilioPhoneNumber) {
        Twilio.init(accountSid, authToken);
        this.twilioPhoneNumber = twilioPhoneNumber;
    }

    public void sendOtp(String to, String otp) {
        Message.creator(
                        new PhoneNumber(to),
                        new PhoneNumber(twilioPhoneNumber),
                        "Your Fitoverse Login OTP code is " + otp)
                .create();
    }
}
