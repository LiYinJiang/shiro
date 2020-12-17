package eryingzhang.net.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eryingzhang.net.entity.Role;
import eryingzhang.net.entity.RoleExample;
import eryingzhang.net.entity.User;
import eryingzhang.net.entity.UserRole;
import eryingzhang.net.entity.UserRoleExample;
import eryingzhang.net.mapper.RoleMapper;
import eryingzhang.net.mapper.UserRoleMapper;
import eryingzhang.net.service.RoleService;
import eryingzhang.net.service.UserService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleMapper mapper;
	@Autowired
	UserService userService;

	@Autowired
	UserRoleMapper userRoleMapper;

	@Override
	public Set<String> listRoleByUserName(String userName) {
		List<Role> roles = listRoles(userName);
		if (roles.isEmpty())
			return null;
		Set<String> roleNames = new HashSet<String>();
		for (Role role : roles) {
			roleNames.add(role.getName());
		}
		return roleNames;
	}

	@Override
	public List<Role> listRoles(User user) {
		return listRoles(user.getName());
	}

	@Override
	public List<Role> listRoles(String userName) {
		User u = userService.getUser(userName);
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUidEqualTo(u.getId());
		List<UserRole> userRoles = userRoleMapper.selectByExample(example);
		List<Role> roles = new ArrayList<>();
		for (UserRole userRole : userRoles) {
			roles.add(mapper.selectByPrimaryKey(userRole.getRid()));
		}
		return roles;
	}

	@Override
	public List<Role> list() {
		RoleExample example = new RoleExample();
		example.setOrderByClause("id desc");
		return mapper.selectByExample(example);
	}

	@Override
	public void add(Role role) {
		mapper.insert(role);

	}

	@Override
	public void delete(Long id) {
		mapper.deleteByPrimaryKey(id);

	}

	@Override
	public Role get(Long id) {

		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(Role role) {
		mapper.updateByPrimaryKeySelective(role);

	}

}
