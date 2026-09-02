package com.JK.The_Work_Bench.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class InviteRequest {
	private Long projectId;
	private String email;
}
