package com.JK.The_Work_Bench.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JK.The_Work_Bench.modal.Comments;
import com.JK.The_Work_Bench.modal.User;
import com.JK.The_Work_Bench.request.CreateCommentRequest;
import com.JK.The_Work_Bench.response.MessageResponse;
import com.JK.The_Work_Bench.service.CommentServices;
import com.JK.The_Work_Bench.service.UserServices;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	@Autowired
	private CommentServices commentServices;

	@Autowired
	private UserServices userServices;

	@PostMapping()
	public ResponseEntity<Comments> createComment(@RequestBody CreateCommentRequest req,
			@RequestHeader("Authorization") String jwt
			) throws Exception
	{
		User user = userServices.findUserProfileByJwt(jwt);
		Comments createComment = commentServices.createComment(req.getIssueId(), user.getId(),req.getContent());
		return new ResponseEntity<>(createComment,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<MessageResponse> deleteComment(@PathVariable Long commentId,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userServices.findUserProfileByJwt(jwt);
		commentServices.deleteComment(commentId, user.getId());

		MessageResponse response = new MessageResponse();
		response.setMessage("comment was deleted");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{issueId}")
	public ResponseEntity<List<Comments>> getCommentsByIssueId(@PathVariable Long issueId) {
		List<Comments> comments = commentServices.findCommentByIssueId(issueId);
		return new ResponseEntity<>(comments, HttpStatus.OK);

	}
}
