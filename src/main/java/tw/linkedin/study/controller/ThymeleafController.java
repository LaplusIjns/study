package tw.linkedin.study.controller;


import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import tw.linkedin.study.model.Index2VO;
import tw.linkedin.study.model.UserInfo;

@Controller
public class ThymeleafController {

	
	private static final Logger log = LoggerFactory.getLogger(ThymeleafController.class);
	
	@GetMapping("/index")
	@PostMapping("/index")
	public String index0(@SessionAttribute @org.springframework.lang.Nullable UserInfo UserInfo) {

		if(UserInfo!=null) {
			System.out.println("當前登入者:  " + UserInfo);
		}else {
//			System.out.println("未登入");
		}
		return "index.html";
	}

	@PostMapping("/index")
	@GetMapping("/")
	public String index() {		
		return "index.html";
	}
	
	@GetMapping("/error")
	public String error() {
		return "error.html";
	}
	
	@GetMapping("/index2")
	public String index2( @RequestParam Map<String, Object> map2 , Model model) {
		map2.forEach( (K,V)->{
			log.info("方法 index2 K:{} V:{} ",K,V);
		});
		Index2VO vo = new Index2VO(1, "toyota", Arrays.asList("version1", "version2", "version3"));
		model.addAttribute("map2",map2);
		model.addAttribute("vo",vo);
		return "index2.html";
		
	}
	
	
	@GetMapping(value="/index3")
    public ModelAndView index3(){
        ModelAndView mv = new ModelAndView("index3.html");
		Index2VO vo = new Index2VO(2, "volvo", Arrays.asList("A", "AA", "AAA"));
        mv.addObject("vo", vo);
        return mv;
    } 
	
	@GetMapping(value="/index4")
    public void index4(){
    }
	
	@GetMapping(value="/404")
    public String error404(){
		return "error404.html";
    } 
	
	@GetMapping(value="/403")
    public String error403(){
		return "error403.html";
    }
	
	@GetMapping(value="/ssologin")
	@PostMapping(value="/ssologin")
    public String login2(){
		return "ssologin.html";
    }
}
