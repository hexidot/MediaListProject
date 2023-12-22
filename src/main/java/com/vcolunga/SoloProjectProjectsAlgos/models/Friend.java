package com.vcolunga.SoloProjectProjectsAlgos.models;

import jakarta.validation.constraints.NotNull;

public class Friend {
	
	@NotNull(message = "name cannot be null")
	private Long friendId;

	public Friend() {
	}

	public Long getFriendId() {
		return friendId;
	}

	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}
}
