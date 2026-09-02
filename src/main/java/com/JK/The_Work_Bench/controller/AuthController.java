package com.JK.The_Work_Bench.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JK.The_Work_Bench.config.JwtProvider;
import com.JK.The_Work_Bench.modal.User;
import com.JK.The_Work_Bench.repository.UserRepository;
import com.JK.The_Work_Bench.request.LoginRequest;
import com.JK.The_Work_Bench.response.AuthResponse;
import com.JK.The_Work_Bench.service.CustomUserDetailsImpl;
import com.JK.The_Work_Bench.service.SubscriptionServices;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomUserDetailsImpl customUserDetails;

	@Autowired
	private SubscriptionServices subscriptionServices;

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception
	{
		User isUserExistUser = userRepo.findByEmail(user.getEmail());
		
		if(isUserExistUser != null) {
			throw new Exception("email already exist with another account");
		}
		
		User createUser = new User();
		

		createUser.setPassword(passwordEncoder.encode(user.getPassword()));
		createUser.setEmail(user.getEmail());
		createUser.setFullName(user.getFullName());
		
		User savedUser = userRepo.save((createUser));

		subscriptionServices.createSubscription(savedUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = JwtProvider.generateToken(authentication);

		AuthResponse res = new AuthResponse();
		res.setMessage("signup sucess");
		res.setJwt(jwt);

		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@PostMapping("/signing")
	public ResponseEntity<AuthResponse> signing(@RequestBody LoginRequest loginRequest) {
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();

		Authentication authentication = authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = JwtProvider.generateToken(authentication);

		AuthResponse res = new AuthResponse();
		res.setMessage("signin sucess");
		res.setJwt(jwt);

		return new ResponseEntity<>(res, HttpStatus.CREATED);

	}

	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = customUserDetails.loadUserByUsername(username);
		if (userDetails == null) {
			throw new BadCredentialsException("invalid username");
		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("invalid password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
}
