package com.JK.The_Work_Bench.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JK.The_Work_Bench.modal.Issue;
import com.JK.The_Work_Bench.modal.IssueDTO;
import com.JK.The_Work_Bench.modal.User;
import com.JK.The_Work_Bench.request.IssueRequest;
import com.JK.The_Work_Bench.response.MessageResponse;
import com.JK.The_Work_Bench.service.IssueServices;
import com.JK.The_Work_Bench.service.UserServices;

@RestController
@RequestMapping("/api/issues")
public class IssueController {
	@Autowired
	private IssueServices issueServices;
	@Autowired
	private UserServices userServices;

	@GetMapping("/{issueId}") // which issue do we want find
	public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception {
		return ResponseEntity.ok(issueServices.getIssueById(issueId));
	}

	@GetMapping
	public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId) throws Exception {
		return ResponseEntity.ok(issueServices.getIssueByProjectId(projectId));
	}

	@PostMapping
	public ResponseEntity<IssueDTO> createIssue(@RequestBody IssueRequest issue,
			@RequestHeader("Authorization") String token) throws Exception {
		User tokenUser = userServices.findUserProfileByJwt(token);
		// User user = userServices.findUserById(tokenUser.getId());

		Issue createdIssue = issueServices.createIssue(issue, tokenUser);
		IssueDTO issueDTO = new IssueDTO();
		issueDTO.setDescription(createdIssue.getDescription());
		issueDTO.setDueDate(createdIssue.getDueDate());
		issueDTO.setId(createdIssue.getId());
		issueDTO.setPriority(createdIssue.getPriority());
		issueDTO.setProject(createdIssue.getProject());
		issueDTO.setProjectId(createdIssue.getProjectID());
		issueDTO.setStatus(createdIssue.getStatus());
		issueDTO.setTitle(createdIssue.getTitle());
		issueDTO.setTags(createdIssue.getTags());
		issueDTO.setAssignee(createdIssue.getAssignee());

		return ResponseEntity.ok(issueDTO);

	}

	@DeleteMapping("/{issueId}")
	public ResponseEntity<MessageResponse> deleteIssuEntity(@PathVariable Long issueId,
			@RequestHeader("Authorization") String token) throws Exception {
		User user = userServices.findUserProfileByJwt(token);
		issueServices.deleteIssue(issueId, user.getId());

		MessageResponse response = new MessageResponse();
		response.setMessage("issue Deleted");

		return ResponseEntity.ok(response);

	}

	@PostMapping("/{issueId}/assignee/{userId}")
	public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId, @PathVariable Long userId)
			throws Exception {
		Issue issue = issueServices.addUserToIssue(issueId, userId);
		return ResponseEntity.ok(issue);
	}

	@PutMapping("/{issueId}/status/{status}")
	public ResponseEntity<Issue> updateIssueSatusEntity(@PathVariable String status, @PathVariable Long issueId)
			throws Exception {
		Issue issue = issueServices.updateStatus(issueId, status);
		return ResponseEntity.ok(issue);
	}
}
