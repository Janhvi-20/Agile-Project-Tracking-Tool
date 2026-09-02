package com.JK.The_Work_Bench.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JK.The_Work_Bench.modal.Chat;
import com.JK.The_Work_Bench.modal.Message;
import com.JK.The_Work_Bench.modal.User;
import com.JK.The_Work_Bench.request.CreateMessageRequest;
import com.JK.The_Work_Bench.service.MessageService;
import com.JK.The_Work_Bench.service.ProjectServices;
import com.JK.The_Work_Bench.service.UserServices;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

	@Autowired
	private MessageService messageService;
	@Autowired
	private UserServices userServices;
	@Autowired
	private ProjectServices projectServices;

	@PostMapping("/send")
	public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest request) throws Exception {
		User user = userServices.findUserById(request.getSenderId());
		if (user == null) {
			throw new Exception("user not found with id" + request.getSenderId());
		}
		Chat chats = projectServices.getProjectById(request.getProjectId()).getChat();
		if (chats == null) {
			throw new Exception("chats not found");
		}

		Message sentMessage = messageService.sendMessage(request.getSenderId(), request.getProjectId(),
				request.getContent());
		return ResponseEntity.ok(sentMessage);
	}

	@GetMapping("/chat/{projectId}")
	public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable long projectId) throws Exception {
		List<Message> messages = messageService.getMessagesByProjectIdList(projectId);
		return ResponseEntity.ok(messages);
	}
}
