package tw.linkedin.study.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import tw.linkedin.study.model.UserInfo;

//@WebFilter(filterName = "logFilter", urlPatterns = { "/*" })
//@Order(1)

@Component
public class TimeFilter implements Filter {
	/**
	 *  三種掛載方式
	 *  單 @Component
	 *  使用 @WebFilter + @Order(1) (Servlet3.0以上)
	 *  FilterRegistrationBean 註冊 filter
	 */

	
	private static final Logger log = LoggerFactory.getLogger(TimeFilter.class);
	
	

	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("===== TimeFilter 初始化 =====");
		Filter.super.init(filterConfig);
	}




	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
//		if(auth instanceof AnonymousAuthenticationToken) {
//			System.out.println("匿名登入");
//		}
		
		if(!(auth instanceof AnonymousAuthenticationToken) ) {
		HttpSession httpSession = httpServletRequest.getSession();
			if( ((UserInfo) httpSession.getAttribute("UserInfo")) == null) {
				UserInfo ui = (UserInfo) auth.getPrincipal();
				httpSession.setAttribute("UserInfo", ui);
				log.info("使用者登入: {},IP: {}",ui,httpServletRequest.getRemoteAddr());
			}
		}
		
		long firstTime = System.currentTimeMillis();
		chain.doFilter(request, response);
		long endTime = System.currentTimeMillis();
		log.info("路徑: {} 花費: {} 豪秒",httpServletRequest.getRequestURL().toString(),(endTime-firstTime));
		
	}

}
