package com.erdangjiade.spring.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
/**
 * 
 * @author King
 * @ref http://blog.csdn.net/u012367513/article/details/38866465
 */
public class MyAccessDecisionManager implements AccessDecisionManager {

	/**
	 * 检查用户是否够权限访问资源
	 * @param authentication 从spring的全局缓存SecurityContextHolder中拿到的，里面是用户的权限信息
	 * @param object 真实想要访问的url
	 * @param configAttributes 访问url所需的权限
	 * @throws AccessDeniedException
	 * @throws InsufficientAuthenticationException
	 */
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null) {
			return;
		}
		for(ConfigAttribute ca : configAttributes){
			String needRole = ((SecurityConfig) ca).getAttribute();
			for (GrantedAuthority ga : authentication.getAuthorities()) { //authentication.getAuthorities()是个类似这样的[ROLE_ADMIN,ROLE_USER]数组
				System.out.println("needRole="+needRole+"  currentRole="+ga.getAuthority());
				if (needRole.equals(ga.getAuthority())) {
					System.err.println("needRole="+needRole+"  currentRole="+ga.getAuthority());
					return;//匹配上了就直接返回,不抛异常,该方法以抛异常来判断是否有权限
				}
			}
		}
		// 目前研究发现AccessDeniedException异常毫无效果
		// 只有InsufficientAuthenticationException异常有效,可以跳转到所配的access-denied-page页面
		throw new AccessDeniedException("no right");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}