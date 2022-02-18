package com.web.home.account.service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.web.home.account.model.AccountDTO;
import com.web.home.account.model.MailUtils;

@Service
public class MailSendService {

	    @Autowired
	    private JavaMailSenderImpl mailSender;
	    private int size;
	    
	    //인증키 생성
	    private String getKey(int size) {
	        this.size = size;
	        return getAuthCode();
	    }

	    //인증코드 난수 발생
	    private String getAuthCode() {
	        Random random = new Random();
	        StringBuffer buffer = new StringBuffer();
	        int num = 0;

	        while(buffer.length() < size) {
	            num = random.nextInt(10);
	            buffer.append(num);
	        }

	        return buffer.toString();
	    }

	    //인증메일 보내기
	    public String sendAuthMail(String email) {
	        //6자리 난수 인증번호 생성
	        String authKey = getKey(6);

	        //인증메일 보내기
	        try {
	            MailUtils sendMail = new MailUtils(mailSender);
	            sendMail.setSubject("회원가입 이메일 인증");
	            sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
	            .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
	            .append("<a href='http://localhost/signUpConfirm?email=")
	            .append(email)
	            .append("&authKey=")
	            .append(authKey)
	            .append("' target='_blenk'>이메일 인증 확인</a>")
	            .toString());
	            sendMail.setFrom("dlwlstn98710@naver.com", "관리자");
	            sendMail.setTo(email);
	            sendMail.send();
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }

	          return authKey;
	    }
	    
	    //비밀번호찾기 보내기
	    public void sendFindPwd(AccountDTO dto) {


	        //인증메일 보내기
	        try {
	            MailUtils sendMail = new MailUtils(mailSender);
	            sendMail.setSubject("KH문고 비밀번호 찾기");
	            sendMail.setText(new StringBuffer().append("<h1>[비밀번호 찾기]</h1><p>")
	            .append(dto.getUser_id())
	            .append("님의 비밀번호는 ")
	            .append(dto.getUser_pwd())
	            .append("입니다. </p>")
	            .toString());
	            sendMail.setFrom("dlwlstn98710@naver.com", "관리자");
	            sendMail.setTo(dto.getUser_email());
	            sendMail.send();
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }

	    }
	    
}
