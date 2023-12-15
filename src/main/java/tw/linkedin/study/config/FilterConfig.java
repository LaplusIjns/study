package tw.linkedin.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {
	
//	@Autowired
//	LogFilter logFilter;
//	
//	@Bean
//	public FilterRegistrationBean<LogFilter> filterRegistrationBean() {
//		FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
//		// 过滤所有路径
//		registrationBean.setFilter(logFilter);
//		registrationBean.addUrlPatterns("/*");
//		// 添加不过滤路径
////		registrationBean.addInitParameter("noFilter", "/one,/two");
//		registrationBean.setName("logFilter");
//		registrationBean.setOrder(1);
//		return registrationBean;
//	}
}
