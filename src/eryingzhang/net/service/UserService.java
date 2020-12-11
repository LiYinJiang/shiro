package eryingzhang.net.service;

import eryingzhang.net.entity.User;

public interface UserService {
	public String getPassword(String name);

	public User getUser(String name);
}
