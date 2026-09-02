package com.JK.The_Work_Bench.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JK.The_Work_Bench.modal.Comments;
import com.JK.The_Work_Bench.modal.Issue;
import com.JK.The_Work_Bench.modal.User;
import com.JK.The_Work_Bench.repository.CommentRepository;
import com.JK.The_Work_Bench.repository.IssueRepository;
import com.JK.The_Work_Bench.repository.UserRepository;

@Service
public class CommentServicesImpl implements CommentServices {

	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private IssueRepository isssIssueRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public Comments createComment(Long issueId, Long userId, String content) throws Exception {
		Optional<Issue> issueOptional = isssIssueRepository.findById(issueId);
		Optional<User> userOptional = userRepository.findById(userId);

		if (issueOptional.isEmpty()) {
			throw new Exception("issue not found with id " + issueId);
		}
		if (userOptional.isEmpty()) {
			throw new Exception("user not found with id" + userId);
		}

		Issue issue = issueOptional.get();
		User user = userOptional.get();

		Comments comment = new Comments();

		comment.setIssue(issue);
		comment.setUser(user);
		comment.setCreatedLocalDateTime(LocalDateTime.now());
		comment.setContent(content);

		Comments saveComments = commentRepository.save(comment);
		issue.getComments().add(saveComments);

		return saveComments;
	}

	@Override
	public void deleteComment(Long commentId, Long userId) throws Exception {
		Optional<Comments> commentOptional = commentRepository.findById(commentId);
		Optional<User> userOptional = userRepository.findById(userId);

		if (commentOptional.isEmpty()) {
			throw new Exception("comment not found with id" + commentId);
		}
		if (userOptional.isEmpty()) {
			throw new Exception("user not found" + userId);
		}

		Comments comments = commentOptional.get();
		User user = userOptional.get();

		if (comments.getUser().equals(user)) {
			commentRepository.delete(comments);
		} else {
			throw new Exception("no permission");
		}

	}

	@Override
	public List<Comments> findCommentByIssueId(Long issueId) {

		return commentRepository.findByIssueId(issueId);
	}

}
