package com.JK.The_Work_Bench.modal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String fullName, email, password;
	private int projectSize;
	
	@JsonIgnore
	@OneToMany (mappedBy= "assignee" , cascade = CascadeType.ALL)
	private List<Issue> aasignedIssues = new ArrayList<>();
}
