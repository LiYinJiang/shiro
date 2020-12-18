package eryingzhang.net.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eryingzhang.net.entity.User;
import eryingzhang.net.entity.UserRole;
import eryingzhang.net.entity.UserRoleExample;
import eryingzhang.net.mapper.UserRoleMapper;
import eryingzhang.net.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleMapper userRoleMapper;

	@Override
	public void setRoles(User user, long[] roleIds) {
		deleteByUser(user.getId());
		if (roleIds == null)
			return;
		for (long rid : roleIds) {
			UserRole userRole = new UserRole();
			userRole.setUid(user.getId());
			userRole.setRid(rid);
			userRoleMapper.insert(userRole);
		}

	}

	@Override
	public void deleteByUser(long userId) {
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUidEqualTo(userId);
		List<UserRole> userRoles = userRoleMapper.selectByExample(example);
		for (UserRole userRole : userRoles) {
			userRoleMapper.deleteByPrimaryKey(userRole.getId());
		}

	}

	@Override
	public void deleteByRole(long role) {
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andRidEqualTo(role);
		List<UserRole> userRoles = userRoleMapper.selectByExample(example);
		for (UserRole userRole : userRoles) {
			userRoleMapper.deleteByPrimaryKey(userRole.getId());
		}
	}

}
