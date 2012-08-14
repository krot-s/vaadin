package com.pls.service;

import java.util.List;
import com.pls.domain.User;

public interface UserService {

	public User getById(User id);

	public List<User> getAllUsers();

	public void addUser(User user);
	
	public List<User> search(String str);

}
