package com.JK.The_Work_Bench.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JK.The_Work_Bench.config.JwtProvider;
import com.JK.The_Work_Bench.modal.User;
import com.JK.The_Work_Bench.repository.UserRepository;

@Service
public class UserServicesImpl implements UserServices {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUserProfileByJwt(String jwt) throws Exception {
		String email = JwtProvider.getEmailFromToken(jwt);

		return findUserByEmail(email);
	}

	@Override
	public User findUserByEmail(String email) throws Exception {
		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new Exception("user not fount");
		}
		return user;
	}

	@Override
	public User findUserById(Long userId) throws Exception {
		Optional<User> optionalUser = userRepository.findById(userId);

		if (optionalUser.isEmpty()) {
			throw new Exception("user not found");
		}
		return optionalUser.get();
	}

	@Override
	public User updateUsersProjectSize(User user, int number) {
		user.setProjectSize(user.getProjectSize() + number);

		return userRepository.save(user);
	}

}
