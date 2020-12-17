package eryingzhang.net.service;

import java.util.List;

import eryingzhang.net.entity.User;

public interface UserService {
	public String getPassword(String name);

	public User getUser(String name);

	public List<User> list();

	public void add(User user);

	public void delete(long id);

	public User get(long id);

	public void update(User user);

}

