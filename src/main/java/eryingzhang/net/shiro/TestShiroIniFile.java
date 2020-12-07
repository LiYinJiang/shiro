package eryingzhang.net.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import eryingzhang.net.dao.DAO;
import eryingzhang.net.entity.User;

public class TestShiroIniFile {

	public static void main(String[] args) {
		// iniUserTest();
		System.out.println("=============================================");
		DAO d = new DAO();
		d.createUser("saltUser", "heiheihei");
		User user = new User();
		user.setName("saltUser");
		user.setPassword("heiheihei");
		if (login(user)) {
			System.out.printf("%s login ok, user password %s \t %n", user.getName(), user.getPassword());
		} else {
			System.err.printf("%s login fail, user password %s \t %n", user.getName(), user.getPassword());
		}
	}

	private static Subject getSubject() {
		// 根据文件配置获取工厂类
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		// 工厂类创建实例
		SecurityManager sm = factory.getInstance();

		// 将安全管理者放入全局对象
		SecurityUtils.setSecurityManager(sm);

		// 全局对象通过安全管理者获取subject对象
		Subject subject = SecurityUtils.getSubject();

		return subject;

	}

	private static boolean login(User user) {

		Subject subject = getSubject();

		if (subject.isAuthenticated()) {
			subject.logout();
		}

		UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
		try {

			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return false;
		}

		return subject.isAuthenticated();
	}

	private static boolean isPermitted(User user, String permit) {
		Subject subject = getSubject();
		return subject.isPermitted(permit);
	}

	private static boolean isRole(User user, String role) {
		Subject subject = getSubject();
		return subject.hasRole(role);
	}

	public static void iniUserTest() {
		User gaki = new User();
		gaki.setName("gaki");
		gaki.setPassword("3354");

		User wuhu = new User();
		wuhu.setName("Wuhu");
		wuhu.setPassword("9527");

		User error = new User();
		error.setName("error");
		error.setPassword("0000");
		List<User> users = new ArrayList<User>();
		users.add(gaki);
		users.add(wuhu);
		users.add(error);

		// coser
		String roleAdmin = "admin";
		String roleProductManager = "productManager";
		List<String> roles = new ArrayList<String>();
		roles.add(roleAdmin);
		roles.add(roleProductManager);

		// permits
		String permitAddProduct = "addProduct";
		String permiAddOrder = "addOrder";

		List<String> permits = new ArrayList<String>();
		permits.add(permitAddProduct);
		permits.add(permiAddOrder);

		for (User user : users) {
			if (login(user)) {
				System.out.printf("%s login ok, user password %s \t %n", user.getName(), user.getPassword());
			} else {
				System.err.printf("%s login fail, user password %s \t %n", user.getName(), user.getPassword());
			}
		}

		for (User user : users) {
			if (login(user)) {
				for (String role : roles) {
					if (isRole(user, role))
						System.out.printf("%s\t have role %s\t%n", user.getName(), role);
					else
						System.out.printf("%s\t haven`t role %s\t%n", user.getName(), role);
				}
			}
		}

		for (User user : users) {
			if (login(user)) {
				for (String permit : permits) {
					if (isPermitted(user, permit))
						System.out.printf("%s\t have permit %s\t%n", user.getName(), permit);
					else
						System.out.printf("%s\t haven`t permit %s\t%n", user.getName(), permit);
				}
			}
		}
	}

}
