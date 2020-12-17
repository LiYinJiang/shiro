package eryingzhang.net.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import eryingzhang.net.entity.Permission;
import eryingzhang.net.service.PermissionService;
import eryingzhang.net.service.RoleService;

@Controller
@RequestMapping("config")
public class PermissionController {

	@Autowired
	PermissionService permissionService;

	@RequestMapping("addPermission")
	public String add(Model m, String name, String desc, String url) {
		Permission p = new Permission();
		p.setName(name);
		p.setDesc(desc);
		p.setUrl(url);
		permissionService.add(p);

		return "redirect:listPermission";
	}

	@RequestMapping("deletePermission")
	public String delete(long id) {
		permissionService.delete(id);
		return "redirect:listPermission";
	}

	@RequestMapping("editPermission")
	public String edit(Model m, long id) {
		Permission p = permissionService.get(id);
		m.addAttribute("permission", p);

		return "editPermission";
	}

	@RequestMapping("updatePermission")
	public String update(Permission p) {
		permissionService.update(p);
		return "redirect:listPermission";
	}

	@RequestMapping("listPermission")
	public String list(Model m) {
		List<Permission> permissions = permissionService.list();
		m.addAttribute("permissions", permissions);
		return "listPermission";
	}

}
