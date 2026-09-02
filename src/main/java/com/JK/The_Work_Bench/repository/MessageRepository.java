package com.JK.The_Work_Bench.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JK.The_Work_Bench.modal.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	List<Message> findByChatIdOrderByCreatedAtAsc(long chatId);

}
