package com.ssafy.AwA.service;

import com.ssafy.AwA.domain.user.User;
import com.ssafy.AwA.dto.EmailRequest;
import com.ssafy.AwA.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;

    public int sendMail(@Valid EmailRequest toEmail) throws Exception {

        Random random = new Random();
        String authKey = String.valueOf((random.nextInt(888888)+111111));


        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, toEmail.getEmail());
        message.setSubject("AwA 이메일 인증번호 입니다.");

        String msgg="";
        msgg+= "<div style='margin:100px;'>";
        msgg+= "<h1> 안녕하세요 AwA입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 인증번호 입력칸으로 돌아가 입력해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다!<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>AwA 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= authKey+"</strong><div><br/> ";
        msgg+= "</div>";

        message.setText(msgg, "utf-8", "html");
        message.setFrom(new InternetAddress("kimsejin159@gmail.com","AwA"));//보내는 사람

        try {
            javaMailSender.send(message);
            return Integer.valueOf(authKey);
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    public int sendMail2(@Valid EmailRequest toEmail) throws Exception {
        User targetUser = userRepository.findByEmail(toEmail.getEmail());
        System.out.println(targetUser.getNickname()+"닉네임");
        if(targetUser==null) {
            return 2;
        }

        Random random = new Random();
        String authKey = String.valueOf((random.nextInt(888888)+111111));


        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, toEmail.getEmail());
        message.setSubject("AwA 이메일 인증번호 입니다.");

        String msgg="";
        msgg+= "<div style='margin:100px;'>";
        msgg+= "<h1> 안녕하세요 AwA입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 인증번호 입력칸으로 돌아가 입력해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다!<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>AwA 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= authKey+"</strong><div><br/> ";
        msgg+= "</div>";

        message.setText(msgg, "utf-8", "html");
        message.setFrom(new InternetAddress("kimsejin159@gmail.com","AwA"));//보내는 사람

        try {
            javaMailSender.send(message);
            targetUser.setEmailCode(authKey);
            return Integer.valueOf(authKey);
        }
        catch (Exception e)
        {
            return 0;
        }
    }
    public int checkEmailCode(String userEmail, String code) {
        User targetUser = userRepository.findByEmail(userEmail);

        if(targetUser.getEmail_code().equals(code))
            return 1;
        else return 0;
    }
}
