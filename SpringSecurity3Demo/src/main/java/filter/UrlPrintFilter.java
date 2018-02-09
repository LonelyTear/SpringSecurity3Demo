package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.savedrequest.DefaultSavedRequest;

/**
 * Servlet Filter implementation class UrlPrint
 */
public class UrlPrintFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public UrlPrintFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hsr = (HttpServletRequest) request;

		// 读servlet
		System.out.println("请求地址" + hsr.getRequestURL());

		// 读session
		HttpSession session = hsr.getSession();
		java.util.Enumeration e = session.getAttributeNames();
		System.out.println("==========================session start=====================================");
		while (e.hasMoreElements()) {
			String sessionName = (String) e.nextElement();
			System.out.println(sessionName + ":" + session.getAttribute(sessionName));
			if(session.getAttribute(sessionName) instanceof DefaultSavedRequest){
				DefaultSavedRequest savedRequest = (DefaultSavedRequest) session.getAttribute(sessionName);
			}
		}
		System.out.println("==========================session end=====================================");
		// 读cookie
		Cookie[] cookies = hsr.getCookies();
		System.out.println("----------------------------cookie start-------------------------------------");
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				System.out.println(c.getName() + "---" + c.getValue());
			}
		}
		System.out.println("----------------------------cookie end-------------------------------------");
		System.out.println();
		System.out.println();
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
