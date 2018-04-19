package com.nuo.dao;

import java.util.List;

import com.nuo.model.User;

public interface UserDao {
	public User getUserById(int id);

	public List<User> getAllUser();
	public void exportExcel();

	public List<User> getSample(String patientId);
}
