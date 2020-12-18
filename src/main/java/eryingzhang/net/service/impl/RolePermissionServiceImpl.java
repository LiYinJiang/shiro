package eryingzhang.net.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eryingzhang.net.entity.Role;
import eryingzhang.net.entity.RolePermission;
import eryingzhang.net.entity.RolePermissionExample;
import eryingzhang.net.mapper.RolePermissionMapper;
import eryingzhang.net.service.RolePermissionService;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

	@Autowired
	RolePermissionMapper rolePermissionMapper;

	@Override
	public void setPermissions(Role role, long[] permissionIds) {
		deleteByRole(role.getId());

		for (long pid : permissionIds) {
			RolePermission pr = new RolePermission();
			pr.setRid(role.getId());
			pr.setPid(pid);
			rolePermissionMapper.insert(pr);
		}

	}

	@Override
	public void deleteByRole(long roleId) {

	}

	@Override
	public void deleteByPermission(long permissionId) {
		RolePermissionExample example = new RolePermissionExample();
		example.createCriteria().andPidEqualTo(permissionId);
		List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(example);
		for (RolePermission rolePermission : rolePermissions) {
			rolePermissionMapper.deleteByPrimaryKey(rolePermission.getId());
		}

	}

}
