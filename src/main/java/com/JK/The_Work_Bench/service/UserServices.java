package com.JK.The_Work_Bench.service;

import com.JK.The_Work_Bench.modal.User;

public interface UserServices {
	User findUserProfileByJwt(String jwt) throws Exception;

	User findUserByEmail(String email) throws Exception;

	User findUserById(Long userId) throws Exception;

	User updateUsersProjectSize(User user, int number);

}
