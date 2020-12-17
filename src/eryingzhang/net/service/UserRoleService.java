package eryingzhang.net.service;

import eryingzhang.net.entity.User;

public interface UserRoleService {

	public void setRoles(User user, long[] roleIds);

	public void deleteByUser(long userId);

	public void deleteByRole(long role);
}
