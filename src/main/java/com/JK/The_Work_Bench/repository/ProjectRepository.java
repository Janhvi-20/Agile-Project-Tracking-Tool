package com.JK.The_Work_Bench.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JK.The_Work_Bench.modal.Project;
import com.JK.The_Work_Bench.modal.User;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	// List<Project> findByOwner(User user);

	// searching purpose by project name
	List<Project> findByNameContainingAndTeamContains(String partialName, User user);

	// finding project by team
//	@Query("SELECT p FROM Project p join p.team t where t= :user")
//	List<Project> findProjectByTeam(@Param("user") User user);

	List<Project> findByTeamContainingOrOwner(User user, User owner);

}
