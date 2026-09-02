package com.JK.The_Work_Bench.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JK.The_Work_Bench.modal.Issue;
import com.JK.The_Work_Bench.modal.Project;
import com.JK.The_Work_Bench.modal.User;
import com.JK.The_Work_Bench.repository.IssueRepository;
import com.JK.The_Work_Bench.request.IssueRequest;

@Service
public class IssueServicesImpl implements IssueServices {

	@Autowired
	private IssueRepository issueRepository;
	@Autowired
	private ProjectServices projectServices;
	@Autowired
	private UserServices userServices;

	@Override
	public Issue getIssueById(Long issueId) throws Exception {
		Optional<Issue> issue = issueRepository.findById(issueId);
		if (issue.isPresent()) {
			return issue.get();
		}
		throw new Exception("No issues found with issueid" + issueId);
	}

	@Override
	public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
		return issueRepository.findByProjectId(projectId);
	}

	@Override
	public Issue createIssue(IssueRequest issueRequest, User userId) throws Exception {
		Project project = projectServices.getProjectById(issueRequest.getProjectID());

		Issue issue = new Issue();
		issue.setTitle(issueRequest.getTitle());
		issue.setDescription(issueRequest.getDescription());
		issue.setStatus(issueRequest.getStatus());
		issue.setProjectID(issueRequest.getProjectID());
		issue.setPriority(issueRequest.getPriority());
		issue.setDueDate(issueRequest.getDueDate());

		issue.setProject(project);

		return issueRepository.save(issue);
	}

	@Override
	public void deleteIssue(Long issueId, Long userId) throws Exception {
		getIssueById(issueId);
		issueRepository.deleteById(issueId);
	}

	@Override
	public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
		User user = userServices.findUserById(userId);
		Issue issue = getIssueById(issueId);

		issue.setAssignee(user);
		return issueRepository.save(issue);
	}

	@Override
	public Issue updateStatus(Long issueId, String status) throws Exception {
		Issue issue = getIssueById(issueId);
		issue.setStatus(status);

		return issueRepository.save(issue);
	}

}
