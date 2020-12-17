package eryingzhang.net.realm;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

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

		// �ܽ��뵽�����ʾ�˺��Ѿ�ͨ����֤��
		String userName = (String) principalCollection.getPrimaryPrincipal();
		// ͨ��DAO��ȡ��ɫ��Ȩ��
		Set<String> permissions = permissionService.listPermissionByUserName(userName);
		Set<String> roles = roleService.listRoleByUserName(userName);

		// ��Ȩ����
		SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
		// ��ͨ��DAO��ȡ���Ľ�ɫ��Ȩ�޷Ž�ȥ
		s.setStringPermissions(permissions);
		s.setRoles(roles);
		return s;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println(this.getCredentialsMatcher());
		// ��ȡ�˺�����
		String userName = token.getPrincipal().toString();
		// ��ȡ���ݿ��е�����
		User user = userService.getUser(userName);
		String passwordInDB = user.getPassword();
		String salt = user.getSalt();

		// ��֤��Ϣ�����˺�����, getName() �ǵ�ǰRealm�ļ̳з���,ͨ�����ص�ǰ���� :databaseRealm
		// ��Ҳ�Ž�ȥ
		// ����ͨ��shiro.ini�����õ� HashedCredentialsMatcher �����Զ�У��
		SimpleAuthenticationInfo a = new SimpleAuthenticationInfo(userName, passwordInDB, ByteSource.Util.bytes(salt),
				getName());
		return a;
	}

}