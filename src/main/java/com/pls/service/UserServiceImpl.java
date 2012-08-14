package com.pls.service;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.pls.domain.User;

public class UserServiceImpl implements UserService {

	private final List<User> users;

	public UserServiceImpl() {
		users = new Vector<User>(50);
		for (long i = 0; i < 50; i++) {
			users.add(createUser(i));
		}
	}

	@Override
	public User getById(User id) {
		for (User user : users) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		return users;
	}

	@Override
	public void addUser(User user) {
		users.add(user);
	}

	private static User createUser(Long id) {
		User user = new User();
		user.setId(id);
		user.setCreatedBy((long)0);
		user.setDateCreated(new Date());
		user.setDateModified(new Date());
		user.setEmailAddress("mail@gmail.com");
		user.setFirstName("Alex");
		user.setLastName("Dos");
		user.setModifiedBy((long)0);
		user.setPassword( "*********");
		user.setStatus("off");
		return user;
	}

	@Override
	public List<User> search(String str) {
		
		List<User> searchusers = new Vector<User>();
		
		for(User user :users){
			if(user.getId()==Long.valueOf(str)){
				searchusers.add(user);
			}
		}
		return searchusers;
	}
}
