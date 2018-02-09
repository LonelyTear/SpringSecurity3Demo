package com.erdangjiade.spring.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
/**
 * 
 * @author King
 * @ref http://blog.csdn.net/u012367513/article/details/38866465
 */
public class MyUserDetailService implements UserDetailsService {

	// 登陆验证时，通过username获取用户的所有权限信息，
	// 并返回User放到spring的全局缓存SecurityContextHolder中，以供授权器使用
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();

		//GrantedAuthorityImpl是security2.0的类,3.0中推荐使用SimpleGrantedAuthority,基本一样
		GrantedAuthorityImpl adminAuth = new GrantedAuthorityImpl("ROLE_ADMIN");
		GrantedAuthorityImpl userAuth = new GrantedAuthorityImpl("ROLE_USER");

		auths = new ArrayList<GrantedAuthority>();
		if (username.equals("admin")) {
			auths.add(userAuth);
			auths.add(adminAuth);
		}else if (username.equals("user")) {
			auths.add(userAuth);
		}else{
			throw new UsernameNotFoundException("用户名/密码错误");
		}
		
		//                  用户名		密码								权限
		User user = new User(username, username, true, true, true, true, auths);
		return user;
	}
}