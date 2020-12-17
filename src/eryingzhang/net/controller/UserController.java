package eryingzhang.net.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import eryingzhang.net.entity.Role;
import eryingzhang.net.entity.User;
import eryingzhang.net.service.RoleService;
import eryingzhang.net.service.UserRoleService;
import eryingzhang.net.service.UserService;

@Controller
@RequestMapping("config")
public class UserController {
	public static final String ALGORITHM_NAME = "md5";
	public static final int ENCODE_ITERATIONS = 2;

	@Autowired
	UserService userService;

	@Autowired
	UserRoleService userRoleService;

	@Autowired
	RoleService roleService;

	@RequestMapping("addUser")
	public String add(Model m, String name, String password) {

		User u = new User();
		u.setName(name);
		String salt = new SecureRandomNumberGenerator().nextBytes().toString();
		String encodedPassword = new SimpleHash(ALGORITHM_NAME, password, salt, ENCODE_ITERATIONS).toString();

		u.setSalt(salt);
		u.setPassword(encodedPassword);

		userService.add(u);
		return "redirect:listUser";
	}

	@RequestMapping("updateUser")
	public String updateUser(User user, long[] roleIds) {
		userRoleService.setRoles(user, roleIds);
		String password = null;
		if (user.getPassword().length() != 0) {
			String salt = new SecureRandomNumberGenerator().nextBytes().toString();
			user.setSalt(salt);
			password = new SimpleHash(ALGORITHM_NAME, user.getPassword(), salt, ENCODE_ITERATIONS).toString();

		}
		user.setPassword(password);

		userService.update(user);

		return "redirect:listUser";
	}

	@RequestMapping("deleteUser")
	public String deletUser(Model m, int id) {
		userService.delete(id);
		return "redirect:listUser";
	}

	@RequestMapping("editUser")
	public String edit(Model m, long id) {
		List<Role> roles = roleService.list();

		m.addAttribute("roles", roles);
		User u = userService.get(id);
		m.addAttribute("user", u);
		List<Role> currentRoles = roleService.listRoles(u);
		m.addAttribute("currentRoles", currentRoles);

		return "editUser";
	}

	@RequestMapping("listUser")
	public String list(Model m) {
		List<User> users = userService.list();
		m.addAttribute("users", users);
		Map<User, List<Role>> userRoles = new HashMap<>();
		for (User user : users) {
			userRoles.put(user, roleService.listRoles(user));
		}
		m.addAttribute("userRoles", userRoles);
		return "listUser";
	}

}
