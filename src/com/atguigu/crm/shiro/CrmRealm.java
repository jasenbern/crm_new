package com.atguigu.crm.shiro;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.service.UserService;

@Component
public class CrmRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {

		User user = (User) arg0.getPrimaryPrincipal();

		Set<String> roles = new HashSet<>();

		for (Authority authority : user.getRole().getAuthorities()) {
			roles.add(authority.getName());
		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg0) throws AuthenticationException {

		UsernamePasswordToken token = (UsernamePasswordToken) arg0;

		String username = token.getUsername();

		User user = userService.getByName(username);

		if (user == null) {
			throw new UnknownAccountException("用户不存在.");
		}
		if (user.getEnabled() != 1) {
			throw new LockedAccountException("用户被锁定.");
		}

		Object principal = user;

		Object credentials = user.getPassword();

		ByteSource salt = ByteSource.Util.bytes(user.getSalt());

		String realmName = getName();

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, salt, realmName);

		return info;
	}

	@PostConstruct
	// 相当于 init-method 属性
	public void initCredentailsMatcher() {
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher(
				"MD5");
		credentialsMatcher.setHashIterations(1024);

		setCredentialsMatcher(credentialsMatcher);
	}

	public static void main(String[] args) {
		// 加密算法
		String algorithmName = "MD5";
		// 源密码
		String source = "123456";
		// 计算盐值. 盐值建议使用随机字符串, 或用户 id(唯一).
		ByteSource salt = ByteSource.Util.bytes("e2b87e6eced06509");
		// 加密次数
		int hashIterations = 1024;
		Object result = new SimpleHash(algorithmName, source, salt,
				hashIterations);
		System.out.println(result);
	}
}
