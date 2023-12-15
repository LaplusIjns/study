package tw.linkedin.study.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.linkedin.study.model.UserInfo;

@Component
public class LoggerInterceptor implements HandlerInterceptor {

	private static final Logger log = LoggerFactory.getLogger(LoggerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.info("== LOG Interceptor 進入前 請求路徑: {} IP:{} =====", request.getRequestURI(), request.getRemoteAddr());
		Authentication auth = (Authentication) request.getUserPrincipal();
		UserInfo ui = (UserInfo) auth.getPrincipal();
		log.info("== 當前登入者: {} 登入部門: {}", ui.getName(),ui.getDeptCode());
		if(handler instanceof HandlerMethod handlerMethod){
            //can be added different logics
            Class<?> controllerClass = handlerMethod.getBeanType();
            String methodName = handlerMethod.getMethod().getName();
            log.info("Controller name: {}",controllerClass.getName());
            log.info("Method name: {}",methodName);
        }
		
		if(handler instanceof ResourceHttpRequestHandler resourceHttpRequestHandler){
        }
		
		return true;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView){
		
		log.info("== LOG Interceptor 處理後 =====");
		log.info("ModelAndView: {}",modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			{

		log.info("== LOG Interceptor 完成 =====");
		if (ex != null) {
			log.error("== 出現錯誤 =====");
			log.error(handler.toString());
			log.error(ex.getMessage());
			log.error("== 出現錯誤 打印結束 =====");
		}
	}
}
