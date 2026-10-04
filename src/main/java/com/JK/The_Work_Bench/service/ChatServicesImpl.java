package com.JK.The_Work_Bench.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JK.The_Work_Bench.modal.Chat;
import com.JK.The_Work_Bench.repository.ChatRepository;

@Service
public class ChatServicesImpl implements ChatServices {

	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Chat createChat(Chat chat) {

		return chatRepository.save(chat);
	}

}
