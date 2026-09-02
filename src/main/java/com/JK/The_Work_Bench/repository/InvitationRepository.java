package com.JK.The_Work_Bench.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JK.The_Work_Bench.modal.Invitation;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

	Invitation findByToken(String token);

	Invitation findByEmail(String userEmail);

}
