package eryingzhang.net.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eryingzhang.net.entity.User;
import eryingzhang.net.mapper.UserMapper;
import eryingzhang.net.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper mapper;

	@Override
	public String getPassword(String name) {
		User u = mapper.getByName(name);
		if (u == null)
			return null;
		return u.getPassword();
	}

	@Override
	public User getUser(String name) {
		if (mapper.getByName(name) != null)
			return mapper.getByName(name);
		return null;
	}

}
