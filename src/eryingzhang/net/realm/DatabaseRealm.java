package eryingzhang.net.realm;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import eryingzhang.net.dao.DAO;
import eryingzhang.net.entity.User;
import eryingzhang.net.service.PermissionService;
import eryingzhang.net.service.RoleService;
import eryingzhang.net.service.UserService;

public class DatabaseRealm extends AuthorizingRealm {

	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	PermissionService permissionService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

		// 能进入到这里，表示账号已经通过验证了
		String userName = (String) principalCollection.getPrimaryPrincipal();
		// 通过DAO获取角色和权限
		Set<String> permissions = permissionService.listPermissionByUserName(userName);
		Set<String> roles = roleService.listRoleByUserName(userName);

		// 授权对象
		SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
		// 把通过DAO获取到的角色和权限放进去
		s.setStringPermissions(permissions);
		s.setRoles(roles);
		return s;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println(this.getCredentialsMatcher());
		// 获取账号密码
		String userName = token.getPrincipal().toString();
		// 获取数据库中的密码
		User user = userService.getUser(userName);
		String passwordInDB = user.getPassword();
		String salt = user.getSalt();

		// 认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
		// 盐也放进去
		// 这样通过shiro.ini里配置的 HashedCredentialsMatcher 进行自动校验
		SimpleAuthenticationInfo a = new SimpleAuthenticationInfo(userName, passwordInDB, ByteSource.Util.bytes(salt),
				getName());
		return a;
	}

}