package com.JK.The_Work_Bench.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.JK.The_Work_Bench.modal.User;
import java.util.*;
import com.JK.The_Work_Bench.repository.UserRepository;

@Service
public class CustomUserDetailsImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		User user = userRepo.findByEmail(username);		
		
		if (user == null)
		{
			throw new UsernameNotFoundException ("user not foound with email" + username);
		}
		List <GrantedAuthority> authorities = new ArrayList<>();
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),authorities);
	}
}
