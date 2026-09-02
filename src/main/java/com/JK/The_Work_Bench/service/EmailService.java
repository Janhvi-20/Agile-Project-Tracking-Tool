package com.JK.The_Work_Bench.service;

import jakarta.mail.MessagingException;

public interface EmailService {
	public void sendEmailWithToken(String userEmail, String link) throws MessagingException;
}
