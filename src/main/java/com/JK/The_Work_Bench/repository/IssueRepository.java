package com.JK.The_Work_Bench.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JK.The_Work_Bench.modal.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long> {

	public List<Issue> findByProjectId(Long id);

}
