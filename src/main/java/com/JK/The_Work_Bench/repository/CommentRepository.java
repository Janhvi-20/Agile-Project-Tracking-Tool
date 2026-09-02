package com.JK.The_Work_Bench.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JK.The_Work_Bench.modal.Comments;

public interface CommentRepository extends JpaRepository<Comments, Long> {

	List<Comments> findByIssueId(long issueId);

}
