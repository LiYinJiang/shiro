package eryingzhang.net.mapper;

import java.util.List;

import eryingzhang.net.entity.Permission;

public interface PermissionMapper {

	public List<Permission> listPermissionByUserName(String userName);
}
