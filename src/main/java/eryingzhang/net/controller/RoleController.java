package eryingzhang.net.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import eryingzhang.net.entity.Permission;
import eryingzhang.net.entity.Role;
import eryingzhang.net.service.PermissionService;
import eryingzhang.net.service.RolePermissionService;
import eryingzhang.net.service.RoleService;

@Controller
@RequestMapping("config")
public class RoleController {

	@Autowired
	RoleService roleService;

	@Autowired
	PermissionService permissionService;

	@Autowired
	RolePermissionService rolePermissionService;

	@RequestMapping("listRole")
	public String list(Model m) {
		List<Role> roles = roleService.list();
		m.addAttribute("roles", roles);
		Map<Role, List<Permission>> rolePermissions = new HashMap<>();
		for (Role role : roles) {
			rolePermissions.put(role, permissionService.list(role));
		}
		m.addAttribute("rolePermissions", rolePermissions);
		return "listRole";
	}

	@RequestMapping("addRole")
	public String add(Model m, String name, String desc) {
		Role r = new Role();
		r.setDesc(desc);
		r.setName(name);
		roleService.add(r);

		m.addAttribute("role", r);
		return "redirect:listRole";
	}

	@RequestMapping("deleteRole")
	public String delete(long id) {
		roleService.delete(id);
		return "redirect:listRole";
	}

	@RequestMapping("editRole")
	public String edit(Model m, long id) {
		Role r = roleService.get(id);
		m.addAttribute("role", r);
		List<Permission> permissions = permissionService.list();
		m.addAttribute("permissions", permissions);

		List<Permission> currentPermission = permissionService.list(r);
		m.addAttribute("currentPermission", currentPermission);

		return "editRole";
	}

	@RequestMapping("updateRole")
	public String update(Role r, long[] pids) {
		rolePermissionService.setPermissions(r, pids);
		return "redirect:listRole";
	}

}
