package tw.linkedin.study.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.linkedin.study.model.UserInfo;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@GetMapping("/first")
	public String first(Authentication authentication,@AuthenticationPrincipal UserInfo userInfo) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(! (auth instanceof AnonymousAuthenticationToken) ) {
				UserInfo ui = (UserInfo) auth.getPrincipal();
				if(ui.getName()!=null) {
				System.out.println(ui);
				}
			}
		
		if(! (authentication instanceof AnonymousAuthenticationToken) ) {
			UserInfo ui = (UserInfo) authentication.getPrincipal();
			if(ui.getName()!=null) {
			System.out.println(ui);
			}
		}
		
		if(userInfo != null) {
			System.out.println(userInfo);
		}
		
		return "歡迎回來 " + userInfo.getName();		
	}	
	
	
	
//	@GetMapping("/first")
//	public String first(Authentication authentication,@AuthenticationPrincipal UserInfo userInfo) {
////		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(userInfo);
//		System.out.println(userInfo);
//		System.out.println(userInfo);
//		
//		if(! (authentication instanceof AnonymousAuthenticationToken)) {
//				UserInfo ui = (UserInfo) authentication.getPrincipal();
//				if(ui.getName()!=null) {
//				System.out.println(ui);
//				return "歡迎回來 "+ui.getName();
//				}
//			}
//		return "Your are not allow";		
//	}	
	
	
	
	
	/**
	 * 丟出錯誤
	 */
	@GetMapping(path = "er")
	public void res6() {
		int a = 0/0;
//		return ResponseEntity.ok().body("throw error 1");
	}
}
