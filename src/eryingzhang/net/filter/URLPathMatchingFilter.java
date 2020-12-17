package eryingzhang.net.filter;

import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import eryingzhang.net.service.PermissionService;

public class URLPathMatchingFilter extends PathMatchingFilter {

	@Autowired
	PermissionService permissionService;

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		String requestURI = getPathWithinApplication(request);
		System.out.println("requestURI: " + requestURI);
		Subject subject = SecurityUtils.getSubject();

		// 如果没登录则跳转到登录页面
		if (!subject.isAuthenticated()) {
			WebUtils.issueRedirect(request, response, "/login");
			return false;
		}

		// 检查这个路径权限是否需要拦截
		boolean needInterceptor = permissionService.needInterceptor(requestURI);
		if (!needInterceptor) {
			return true;
		} else {
			// 拦截后判断角色是否有访问uri的权限
			String userName = subject.getPrincipal().toString();
			Set<String> permissionUrls = permissionService.listPermissionURLS(userName);
			for (String permissionUrl : permissionUrls) {
				if (permissionUrl.equals(requestURI)) {
					return true;
				}
			}

			// 未找到则抛出异常
			UnauthorizedException ex = new UnauthorizedException("当前用户没有访问路径 " + requestURI + " 的权限");
			subject.getSession().setAttribute("ex", ex);
			WebUtils.issueRedirect(request, response, "unauthorized");
			return false;

		}

	}

}
