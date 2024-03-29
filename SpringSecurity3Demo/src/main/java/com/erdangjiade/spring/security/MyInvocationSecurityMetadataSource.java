package com.erdangjiade.spring.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.erdangjiade.spring.security.tool.AntUrlPathMatcher;
import com.erdangjiade.spring.security.tool.UrlMatcher;
/**
 * 
 * @author King
 * @ref http://blog.csdn.net/u012367513/article/details/38866465
 */
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	// tomcat启动时实例化一次
	public MyInvocationSecurityMetadataSource() {
		loadResourceDefine();
	}

	// tomcat开启时加载一次，加载所有url和权限（或角色）的对应关系
	private void loadResourceDefine() {
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		
		Collection<ConfigAttribute> adminAtts = new ArrayList<ConfigAttribute>();
		ConfigAttribute adminCa = new SecurityConfig("ROLE_ADMIN");
		adminAtts.add(adminCa);
		resourceMap.put("/admin.jsp", adminAtts);
		
		Collection<ConfigAttribute> userAtts = new ArrayList<ConfigAttribute>();
		ConfigAttribute userCa = new SecurityConfig("ROLE_USER");
		userAtts.add(userCa);
		resourceMap.put("/user.jsp", userAtts);
		
		Collection<ConfigAttribute> otherAtts = new ArrayList<ConfigAttribute>();
		ConfigAttribute otherCa = new SecurityConfig("ROLE_USER");
		otherAtts.add(otherCa);
		resourceMap.put("/other.jsp", otherAtts);
	}

	// 参数是要访问的url，返回这个url对应的所有权限（或角色）
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// 将参数转为url
		String url = ((FilterInvocation) object).getRequestUrl();
		for(String resURL : resourceMap.keySet()){
			if (urlMatcher.pathMatchesUrl(resURL, url)) {
				return resourceMap.get(resURL);
			}
		}
		return null;
//		Iterator<String> ite = resourceMap.keySet().iterator();
//		while (ite.hasNext()) {
//			String resURL = ite.next();
//			if (urlMatcher.pathMatchesUrl(resURL, url)) {
//				return resourceMap.get(resURL);
//			}
//		}
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
}