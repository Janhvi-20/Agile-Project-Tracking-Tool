package com.JK.The_Work_Bench.service;

import java.util.List;

import com.JK.The_Work_Bench.modal.Chat;
import com.JK.The_Work_Bench.modal.Project;
import com.JK.The_Work_Bench.modal.User;

public interface ProjectServices {

	Project createProject(Project project, User user) throws Exception;

	List<Project> getProjectByTeamList(User user, String category, String tag) throws Exception;

	Project getProjectById(Long projectId) throws Exception;

	// project owner and project deletion on requesting user are both same or not
	void deleteProject(Long projectId, Long userId) throws Exception;

	Project updateProject(Project updatedProject, Long id) throws Exception;

	void addUserToProject(Long projectId, Long userId) throws Exception;

	void removeUserToProject(Long projectId, Long userId) throws Exception;

	Chat getChatByProjectId(Long projectId) throws Exception;

	List<Project> searchProjects(String keyword, User user) throws Exception;

}
