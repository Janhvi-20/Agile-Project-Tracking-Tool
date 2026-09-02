package com.JK.The_Work_Bench.service;

import com.JK.The_Work_Bench.modal.Invitation;

public interface InvitationService {
	public void sendInvitation(String email, Long projectId) throws Exception;

	public Invitation acceptInvitation(String token, Long userId) throws Exception;

	public String getTokenByUserMail(String userEmail);

	void deleteToken(String token);
}
