package eryingzhang.net.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eryingzhang.net.entity.User;
import eryingzhang.net.entity.UserExample;
import eryingzhang.net.mapper.UserMapper;
import eryingzhang.net.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper mapper;

	@Override
	public String getPassword(String name) {
		User u = getUser(name);
		if (u == null)
			return null;
		return u.getPassword();
	}

	@Override
	public User getUser(String name) {
		UserExample example = new UserExample();
		example.createCriteria().andNameEqualTo(name);
		List<User> users = mapper.selectByExample(example);
		if (users.isEmpty())
			return null;
		return users.get(0);
	}

	@Override
	public List<User> list() {
		UserExample example = new UserExample();
		example.setOrderByClause("id desc");

		return mapper.selectByExample(example);
	}

	@Override
	public void add(User user) {
		mapper.insert(user);

	}

	@Override
	public void delete(long id) {
		mapper.deleteByPrimaryKey(id);

	}

	@Override
	public User get(long id) {

		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(User user) {
		mapper.updateByPrimaryKeySelective(user);
	}

}
