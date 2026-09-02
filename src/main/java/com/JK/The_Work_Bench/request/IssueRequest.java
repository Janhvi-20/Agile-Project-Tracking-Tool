package com.JK.The_Work_Bench.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class IssueRequest {
	private Long projectID;

	private String title, description, status, priority;
	private LocalDate dueDate;
}
