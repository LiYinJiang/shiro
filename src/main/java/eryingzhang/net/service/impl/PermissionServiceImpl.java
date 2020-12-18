package eryingzhang.net.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eryingzhang.net.entity.Permission;
import eryingzhang.net.entity.PermissionExample;
import eryingzhang.net.entity.Role;
import eryingzhang.net.entity.RolePermission;
import eryingzhang.net.entity.RolePermissionExample;
import eryingzhang.net.mapper.PermissionMapper;
import eryingzhang.net.mapper.RolePermissionMapper;
import eryingzhang.net.service.PermissionService;
import eryingzhang.net.service.RoleService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	PermissionMapper mapper;

	@Autowired
	RoleService roleService;

	@Autowired
	RolePermissionMapper rolePermissionMapper;


	@Override
	public Set<String> listPermissionByUserName(String userName) {
		Set<String> result = new HashSet<>();
		List<Role> roles = roleService.listRoles(userName);

		RolePermissionExample rolePermissionExample = new RolePermissionExample();
		// get Role
		List<Long> RIDs = new ArrayList<Long>();
		for (Role role : roles) {
			RIDs.add(role.getId());
		}
		rolePermissionExample.createCriteria().andRidIn(RIDs);
		// getPermission by rolePermission
		List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(rolePermissionExample);
		for (RolePermission rolePermission : rolePermissions) {
			result.add(mapper.selectByPrimaryKey(rolePermission.getPid()).getName());
		}

		return result;

	}

	@Override
	public List<Permission> list() {
		PermissionExample example = new PermissionExample();
		example.setOrderByClause("id desc");
		return mapper.selectByExample(example);
	}

	@Override
	public void add(Permission permission) {
		mapper.insert(permission);

	}

	@Override
	public void delete(Long id) {
		mapper.deleteByPrimaryKey(id);

	}

	@Override
	public Permission get(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(Permission permission) {
		mapper.updateByPrimaryKey(permission);
	}

	@Override
	public List<Permission> list(Role role) {
		List<Permission> permissions = new ArrayList<>();
		RolePermissionExample rolePermissionExample = new RolePermissionExample();
		rolePermissionExample.createCriteria().andRidEqualTo(role.getId());

		// getPermission by rolePermission
		List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(rolePermissionExample);
		for (RolePermission rolePermission : rolePermissions) {
			permissions.add(mapper.selectByPrimaryKey(rolePermission.getPid()));
		}
		return permissions;
	}

	@Override
	public boolean needInterceptor(String requestUri) {
		for (Permission permission : list()) {
			if (permission.getUrl().equals(requestUri))
				return true;
		}

		return false;
	}

	@Override
	public Set<String> listPermissionURLs(String userName) {
		Set<String> permissionUrls = new HashSet<>();
		List<Role> roles = roleService.listRoles(userName);
		if (roles.isEmpty())
			return permissionUrls;
		RolePermissionExample example = new RolePermissionExample();
		List<Long> rids = new ArrayList<>();
		for (Role role : roles) {
			rids.add(role.getId());
		}
		example.createCriteria().andRidIn(rids);
		List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(example);
		for (RolePermission rolePermission : rolePermissions) {
			permissionUrls.add(mapper.selectByPrimaryKey(rolePermission.getPid()).getUrl());
		}
		return permissionUrls;
	}

}
