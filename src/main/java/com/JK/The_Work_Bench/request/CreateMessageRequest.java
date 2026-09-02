package com.JK.The_Work_Bench.request;

import lombok.Data;

@Data
public class CreateMessageRequest {

	private Long senderId, projectId;
	private String content;

}
