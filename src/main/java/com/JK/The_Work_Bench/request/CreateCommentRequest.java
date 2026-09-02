package com.JK.The_Work_Bench.request;

import lombok.Data;

@Data
public class CreateCommentRequest {

	private Long issueId;
	private String content;

}
