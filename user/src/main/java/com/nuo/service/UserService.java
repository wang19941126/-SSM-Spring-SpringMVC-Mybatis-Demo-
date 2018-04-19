package com.nuo.service;

import java.util.List;

import com.nuo.model.User;

public interface UserService {
	
	public User getUserById(int id);

	public List<User> getAllUser();

	public List<User> getSample(String patientId);
	
	
}
