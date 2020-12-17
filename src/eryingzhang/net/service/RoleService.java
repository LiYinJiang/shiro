package eryingzhang.net.service;

import java.util.List;
import java.util.Set;

import eryingzhang.net.entity.Role;
import eryingzhang.net.entity.User;

public interface RoleService {
	public Set<String> listRoleByUserName(String userName);

	public List<Role> listRoles(String userName);

	public List<Role> listRoles(User user);

	public List<Role> list();

	public void add(Role role);

	public void delete(Long id);

	public Role get(Long id);

	public void update(Role role);
}
