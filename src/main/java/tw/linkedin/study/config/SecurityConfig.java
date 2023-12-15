package tw.linkedin.study.config;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * 舊方法新版本可能已經遭刪除或棄用
 * https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
	
	
	@Autowired
	@Qualifier("userVerifService")
	UserDetailsService userDetailsService;
	
	

	@Bean
	 public SecurityFilterChain filterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
		http.sessionManagement((sessionManagement) ->
	       sessionManagement
	       .invalidSessionUrl("/index")
	       .sessionFixation().none()
//	       .migrateSession()
	       );
		
//		預設啟用 保護除了get 以外的 http method
		http
       .csrf(csrf -> csrf.disable());
		
       http
       .authorizeHttpRequests(
    		   authorize -> {
    			   authorize
//    			   .requestMatchers(new AntPathRequestMatcher("/api/*")).permitAll()
    			   .requestMatchers(AntPathRequestMatcher.antMatcher("/auth/**") ).authenticated()
//    			   .requestMatchers(mvc.pattern("/**")).permitAll()
    		       .anyRequest().permitAll();
//    		       .anyRequest().authenticated();
    		   });
		
//      登入失敗轉URL /403       
		http.exceptionHandling(exceptionHandling->{exceptionHandling
			.authenticationEntryPoint(new CustomAuthenticationEntryPoint() );
		}
		);
       
       /**
        * 不需要可設定 form.disable()
        */
       http.formLogin(form -> form
   			.loginPage("/ssologin")
   			.defaultSuccessUrl("/index",true) //直接登入跳轉首頁 FALSE會是最後一個授權網頁
   			);
       
       http.logout(logout->logout.logoutUrl("/logout").logoutSuccessUrl("/index"));
       
       // 核心驗證方法
       http.authenticationProvider(daoAuthenticationProvider());
       

       return http.build();
   }
	
	
	


	@Bean
	public AuthenticationProvider daoAuthenticationProvider() {
	      DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	      // 添加加解密物件 不加鹽
	      provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
	      provider.setUserDetailsService(userDetailsService);
	      return provider;
	}
	
	@Bean
	MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
		return new MvcRequestMatcher.Builder(introspector);
	}
	

	class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

		@Override
		public void commence(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException authException) throws IOException, ServletException {
			response.sendRedirect("/403");
//			request
			
		}
	}
	
//	@Bean
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//      // withDefaultPasswordEncoder被弃用，用以下方式
////      PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		auth.authenticationProvider(daoAuthenticationProvider);
//  }
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password")
//				.roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}

//	@Bean
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // withDefaultPasswordEncoder被弃用，用以下方式
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        auth.inMemoryAuthentication()
////                .withUser(User.withDefaultPasswordEncoder().username("admin")
//                .withUser("admin")
//                .password(encoder.encode("admin")).roles("USER");
//    }
	
//	@Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
////        return (web) -> web.ignoring().requestMatchers(new MvcRequestMatcher(null, "/**"));
//		return (web) -> web.ignoring().anyRequest();
//    }
}
