package eryingzhang.net.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eryingzhang.net.entity.Role;
import eryingzhang.net.mapper.RoleMapper;
import eryingzhang.net.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleMapper mapper;

	@Override
	public Set<String> listRoleByUserName(String userName) {
		List<Role> roles = mapper.listRoleByUserName(userName);
		if (roles.isEmpty())
			return null;
		Set<String> roleNames = new HashSet<String>();
		for (Role role : roles) {
			roleNames.add(role.getName());
		}
		return roleNames;
	}

}
