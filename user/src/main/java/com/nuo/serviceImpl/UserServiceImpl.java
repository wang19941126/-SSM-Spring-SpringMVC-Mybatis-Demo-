package com.nuo.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nuo.dao.UserDao;
import com.nuo.model.User;
import com.nuo.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	public User getUserById(int id) {
		User user = userDao.getUserById(id);
		return user;
	}

	public List<User> getAllUser() {

		List<User> list = userDao.getAllUser();
		return list;
	}

	public List<User> getSample(String patientId) {
		List<User> list = userDao.getSample(patientId);

		return list;
	}

}
