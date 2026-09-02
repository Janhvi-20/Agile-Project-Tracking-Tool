package com.JK.The_Work_Bench.service;

import java.util.List;

import com.JK.The_Work_Bench.modal.Message;

public interface MessageService {

	Message sendMessage(long senderId, Long projectId, String content) throws Exception;

	List<Message> getMessagesByProjectIdList(Long projectId) throws Exception;
}
