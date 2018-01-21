package com.home.dreamcar.service;

import com.home.dreamcar.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceDefault implements EmailService{

    public final static String DREAM_CAR = "Dream Car Company";

    @Autowired
    public JavaMailSender emailSender;

    public void sendMailToWinnerOfAuction(String to, Long auctionId, Product product) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("sarateanu.daniel@yahoo.com");
        //message.setTo(to);
        message.setSubject(DREAM_CAR +" Auction");
        message.setText("Congratilations!\n\n" +
                "You won the auction number" + auctionId + "for product" + product.getName() +".\n " +
                "One of the company employees will call you to talk about the transaction details\n\n" +
                "Thank you,\n" +
                "Keep in touch for new colaboration,\n" +
                DREAM_CAR);
        emailSender.send(message);
    }

    public void sendMailToActivatedUser(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("sarateanu.daniel@yahoo.com");
        //message.setTo(to);
        message.setSubject(DREAM_CAR + " Account");
        message.setText("Congratilations!\n\n" +
                        "Your Dream Car account is active.\n\n" +
                        "Good luck in winning auctions,\n" +
                        DREAM_CAR);

        emailSender.send(message);
    }

    public void sendMailToUnactivatedUser(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("sarateanu.daniel@yahoo.com");
        //message.setTo(to);
        message.setSubject(DREAM_CAR + " Account");
        message.setText("Congratilations!\n\n" +
                "Your Dream Car account is created.\n\n" +
                "One of the company employees will activate it shortly.\n\n" +
                DREAM_CAR);
        emailSender.send(message);
    }
}
