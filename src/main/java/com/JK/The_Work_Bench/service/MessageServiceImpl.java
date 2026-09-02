package com.JK.The_Work_Bench.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JK.The_Work_Bench.modal.Chat;
import com.JK.The_Work_Bench.modal.Message;
import com.JK.The_Work_Bench.modal.User;
import com.JK.The_Work_Bench.repository.MessageRepository;
import com.JK.The_Work_Bench.repository.UserRepository;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProjectServices projectServices;

	@Override
	public Message sendMessage(long senderId, Long projectId, String content) throws Exception {
		User sender = userRepository.findById(senderId)
				.orElseThrow(() -> new RuntimeException("User not found with id " + senderId));

		Chat chat = projectServices.getProjectById(projectId).getChat();
		Message message = new Message();
		message.setContentString(content);
		message.setSender(sender);
		message.setCreateDateTime(LocalDateTime.now());
		message.setChat(chat);
		Message savedMessage = messageRepository.save(message);
		chat.getMessages().add(savedMessage);
		return savedMessage;
	}

	@Override
	public List<Message> getMessagesByProjectIdList(Long projectId) throws Exception {
		Chat chat = projectServices.getChatByProjectId(projectId);
		List<Message> findByChatIdOrderByCreatedAtAsc = messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
		return findByChatIdOrderByCreatedAtAsc;
	}

}
