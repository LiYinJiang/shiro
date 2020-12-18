package eryingzhang.net.service;

import java.util.List;
import java.util.Set;

import eryingzhang.net.entity.Permission;
import eryingzhang.net.entity.Role;

public interface PermissionService {
	public Set<String> listPermissionByUserName(String userName);

	public List<Permission> list();

	public void add(Permission permission);

	public void delete(Long id);

	public Permission get(Long id);

	public void update(Permission permission);

	public List<Permission> list(Role role);

	public boolean needInterceptor(String requestUri);

	public Set<String> listPermissionURLs(String userName);
}
