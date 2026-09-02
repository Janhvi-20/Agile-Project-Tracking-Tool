package com.JK.The_Work_Bench.modal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueDTO {
	private Long id, projectId;
	private String title, description, status, priority;
	private LocalDate dueDate;
	private List<String> tags = new ArrayList<>();
	private Project project;
	private User assignee;

}