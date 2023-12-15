package tw.linkedin.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.Filter;

/**
 * 不要加 @EnableWebMvc 或 extends webMVCSupport 自動配置會失效 靜態資源連不到!!
 */
@Configuration
public class SpringMVCConfig implements WebMvcConfigurer  {
	

	@Autowired
	LoggerInterceptor loggerInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loggerInterceptor).addPathPatterns("/auth/*");
//		WebMvcConfigurer.super.addInterceptors(registry);
	}

}
