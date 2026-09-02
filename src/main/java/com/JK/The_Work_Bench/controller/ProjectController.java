package com.JK.The_Work_Bench.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JK.The_Work_Bench.modal.Chat;
import com.JK.The_Work_Bench.modal.Invitation;
import com.JK.The_Work_Bench.modal.Project;
import com.JK.The_Work_Bench.modal.User;
import com.JK.The_Work_Bench.request.InviteRequest;
import com.JK.The_Work_Bench.response.MessageResponse;
import com.JK.The_Work_Bench.service.InvitationService;
import com.JK.The_Work_Bench.service.ProjectServices;
import com.JK.The_Work_Bench.service.UserServices;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	@Autowired
	private ProjectServices projectServices;

	@Autowired
	private UserServices userServices;

	@Autowired
	private InvitationService invitationService;

	@GetMapping
	public ResponseEntity<List<Project>> getProjects(@RequestParam(required = false) String category,
			@RequestParam(required = false) String tag, @RequestHeader("Authorization") String jwt) throws Exception {
		User user = userServices.findUserProfileByJwt(jwt);
		List<Project> projects = projectServices.getProjectByTeamList(user, category, tag);

		return new ResponseEntity<>(projects, HttpStatus.OK);

	}

	@GetMapping("/{projectId}")
	public ResponseEntity<Project> getProjectById(@PathVariable Long projectId,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userServices.findUserProfileByJwt(jwt);
		Project project = projectServices.getProjectById(projectId);

		return new ResponseEntity<>(project, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Project> createProject(@PathVariable Long projectId,
			@RequestHeader("Authorization") String jwt, @RequestBody Project project) throws Exception {
		User user = userServices.findUserProfileByJwt(jwt);
		Project createdproject = projectServices.createProject(project, user);

		return new ResponseEntity<>(createdproject, HttpStatus.OK);
	}

	@PatchMapping("{projectId}")
	public ResponseEntity<Project> updateProject(@PathVariable Long projectId,
			@RequestHeader("Authorization") String jwt, @RequestBody Project project) throws Exception {
		User user = userServices.findUserProfileByJwt(jwt);
		Project updatedproject = projectServices.updateProject(project, projectId);

		return new ResponseEntity<>(updatedproject, HttpStatus.OK);
	}

	@DeleteMapping("{projectId}")
	public ResponseEntity<MessageResponse> deleteProject(@PathVariable Long projectId,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userServices.findUserProfileByJwt(jwt);
		projectServices.deleteProject(projectId, user.getId());
		MessageResponse response = new MessageResponse("project deleted sucessfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// searching for the project
	@GetMapping("/search")
	public ResponseEntity<List<Project>> searchProjects(@RequestParam(required = false) String keyword,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userServices.findUserProfileByJwt(jwt);
		List<Project> projects = projectServices.searchProjects(keyword, user);

		return new ResponseEntity<>(projects, HttpStatus.OK);

	}

	@GetMapping("/{projectId}/chat")
	public ResponseEntity<Chat> getChatByProjectId(@PathVariable Long projectId,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userServices.findUserProfileByJwt(jwt);
		Chat chat = projectServices.getChatByProjectId(projectId);

		return new ResponseEntity<>(chat, HttpStatus.OK);

	}

	@PostMapping("/invite")
	public ResponseEntity<MessageResponse> inviteProject(
			@RequestBody InviteRequest req, @RequestHeader("Authorization") String jwt, @RequestBody Project project)
			throws Exception {
		User user = userServices.findUserProfileByJwt(jwt);

		invitationService.sendInvitation(req.getEmail(), req.getProjectId());
		MessageResponse response = new MessageResponse("user invitattion sent");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping("/accept_invitation")
	public ResponseEntity<Invitation> acceptInvitateProject(@RequestParam String token,
			@RequestHeader("Authorization") String jwt, @RequestBody Project project) throws Exception {
		User user = userServices.findUserProfileByJwt(jwt);
		Invitation invitation = invitationService.acceptInvitation(token, user.getId());
		projectServices.addUserToProject(invitation.getProjectId(), user.getId());

		return new ResponseEntity<>(invitation, HttpStatus.ACCEPTED);

	}
	

}
