package com.JK.The_Work_Bench.service;

import java.util.List;

import com.JK.The_Work_Bench.modal.Issue;
import com.JK.The_Work_Bench.modal.User;
import com.JK.The_Work_Bench.request.IssueRequest;

public interface IssueServices {
	Issue getIssueById(Long issueId) throws Exception;

	List<Issue> getIssueByProjectId(Long projectId) throws Exception;

	Issue createIssue(IssueRequest issue, User userId) throws Exception;

	void deleteIssue(Long issueId, Long userId) throws Exception;

	Issue addUserToIssue(Long issueId, Long userId) throws Exception;

	Issue updateStatus(Long issueId, String status) throws Exception;

}
