package eryingzhang.net.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eryingzhang.net.entity.Permission;
import eryingzhang.net.mapper.PermissionMapper;
import eryingzhang.net.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	PermissionMapper mapper;

	@Override
	public Set<String> listPermissionByUserName(String userName) {
		List<Permission> permissions = mapper.listPermissionByUserName(userName);
		if (permissions.isEmpty())
			return null;
		Set<String> permissionNames = new HashSet<String>();
		for (Permission permission : permissions) {
			permissionNames.add(permission.getName());
		}
		return permissionNames;
	}

}
