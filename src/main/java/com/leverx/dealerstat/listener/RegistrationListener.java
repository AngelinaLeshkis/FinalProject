//package com.leverx.dealerstat.listener;
//
//import com.leverx.dealerstat.entity.User;
//import com.leverx.dealerstat.event.OnRegistrationCompleteEvent;
//import com.leverx.dealerstat.serviceimpl.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.MessageSource;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@Component
//public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
//
//    @Autowired
//    private UserServiceImpl userService;
//
//    @Autowired
//    private MessageSource messages;
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Override
//    public void onApplicationEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {
//        this.confirmRegistration(event);
//    }
//
//    private void confirmRegistration(OnRegistrationCompleteEvent event) {
//        User user = event.getUser();
//        String token = UUID.randomUUID().toString();
//        userService.createVerificationToken(user, token);
//
//        String recipientAddress = user.getEmail();
//        String subject = "Registration Confirmation";
//        String confirmationUrl
//                = event.getAppUrl() + "/registrationConfirm.html?token=" + token;
//        String message = messages.getMessage("message.regSucc", null, event.getLocale());
//
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setTo(recipientAddress);
//        email.setSubject(subject);
//        email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
//        mailSender.send(email);
//    }
//}
