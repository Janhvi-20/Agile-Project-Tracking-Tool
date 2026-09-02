package com.JK.The_Work_Bench.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.JK.The_Work_Bench.modal.Comments;

@Service
public interface CommentServices {
	Comments createComment(Long issueId, Long userId, String comment) throws Exception;

	void deleteComment(Long commentId, Long userId) throws Exception;

	List<Comments> findCommentByIssueId(Long issueId);
}
