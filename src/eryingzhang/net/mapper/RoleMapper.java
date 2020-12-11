package eryingzhang.net.mapper;

import java.util.List;

import eryingzhang.net.entity.Role;

public interface RoleMapper {

	public List<Role> listRoleByUserName(String userName);
}
