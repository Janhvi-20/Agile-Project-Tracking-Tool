package com.JK.The_Work_Bench.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JK.The_Work_Bench.modal.Chat;
import com.JK.The_Work_Bench.modal.Project;
import com.JK.The_Work_Bench.modal.User;
import com.JK.The_Work_Bench.repository.ProjectRepository;

@Service
public class ProjectServicesImpl implements ProjectServices {

	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private UserServices userServices;
	@Autowired
	private ChatServices chatServices;

	@Override
	public Project createProject(Project project, User user) throws Exception {
		Project createdProject = new Project();

		createdProject.setOwner(user);
		createdProject.setTags(project.getTags());
		createdProject.setName(project.getName());
		createdProject.setCategory(project.getCategory());
		createdProject.setDescription(project.getDescription());
		createdProject.getTeam().add(user);

		Project savedProject = projectRepository.save(createdProject);
		Chat chat = new Chat();
		chat.setProject(savedProject);

		Chat projetChat = chatServices.createChat(chat);

		savedProject.setChat(projetChat);

		return savedProject;
	}


	@Override
	public List<Project> getProjectByTeamList(User user, String category, String tag) throws Exception {
		List<Project> projects = projectRepository.findByTeamContainingOrOwner(user, user);

		if (category != null) {
			projects = projects.stream().filter(project -> project.getCategory().equals(category))
					.collect(Collectors.toList());
		}

		if (tag != null) {
			projects = projects.stream().filter(project -> project.getTags().contains(tag))
					.collect(Collectors.toList());
		}
		return projects;
	}


	@Override
	public Project getProjectById(Long projectId) throws Exception {
		Optional<Project> optionalProject = projectRepository.findById(projectId);
		if (optionalProject.isEmpty()) {
			throw new Exception("project not fount");
		}
		return optionalProject.get();
	}


	@Override
	public void deleteProject(Long projectId, Long userId) throws Exception {
		getProjectById(projectId);
		projectRepository.deleteById(projectId);

	}


	@Override
	public Project updateProject(Project updatedProject, Long id) throws Exception {
		Project project = getProjectById(id);

		project.setName(updatedProject.getName());
		project.setDescription(updatedProject.getDescription());
		project.setTags(updatedProject.getTags());

		return projectRepository.save(project);
	}

	@Override
	public void addUserToProject(Long projectId, Long userId) throws Exception {
		Project project = getProjectById(projectId);
		User user = userServices.findUserById(userId);

		if (!project.getTeam().contains(user)) {
			project.getChat().getUsers().add(user);
			project.getTeam().add(user);
		}

		projectRepository.save(project);

	}

	@Override
	public void removeUserToProject(Long projectId, Long userId) throws Exception {
		Project project = getProjectById(projectId);
		User user = userServices.findUserById(userId);

		if (project.getTeam().contains(user)) {
			project.getChat().getUsers().remove(user);
			project.getTeam().remove(user);
		}

		projectRepository.save(project);

	}

	@Override
	public Chat getChatByProjectId(Long projectId) throws Exception {

		Project project = getProjectById(projectId);
		return project.getChat();
	}

	@Override
	public List<Project> searchProjects(String keyword, User user) throws Exception {

		List<Project> projects = projectRepository.findByNameContainingAndTeamContains(keyword, user);
		return projects;
	}

}
