package eryingzhang.net.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class PageController {

	@RequestMapping("index")
	public String index() {
		return "index";
	}

	// @RequiresPermissions("deleteFeature")
	@RequestMapping("deleteFeature")
	public String deleteFeature() {
		return "deleteFeature";
	}

	// @RequiresRoles("admin")
	// @RequiresPermissions("deleteCode")
	@RequestMapping("deleteCode")
	public String deleteCode() {
		return "deleteCode";
	}

	@RequestMapping("listCode")
	public String listCode() {
		return "listCode";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping("unauthorized")
	public String unauthorized() {
		return "unauthorized";
	}
}
