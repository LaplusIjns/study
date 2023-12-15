package tw.linkedin.study.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.linkedin.study.model.Employee;
import tw.linkedin.study.model.UserInfo;
import tw.linkedin.study.model.ViewVO;
import tw.linkedin.study.service.EmployeeService;

/**
 * 
 *  @PathVariable 使用
	@RequestHeader 使用
	@RequestParam 使用
	@CookieValue 使用
	@RequestBody 使用
	
	@ModelAttribute 
	 
	@MatrixVariable
	
	BindingResult result 方法加此參數可除錯
 */
@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	/**
	 * 使用 ResponseEntity 回傳全部員工資訊
	 */
	@GetMapping(path = "hello")
	public ResponseEntity<?> listall() {
		List<Employee> result = employeeService.listall();
		if (!result.isEmpty()) {
			return ResponseEntity.ok().body(result);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(path = "helloa")
	public EntityModel<?> listalla() {
//	public Collection<Resource<ViewVO>> listalla() {
		List<Employee> result = employeeService.listall();
		ViewVO vo = new ViewVO();
		vo.setData(result.get(0));
		vo.setHttpStatus(HttpStatus.OK);
		Link aggregateRoot = Link.of("123");
		return EntityModel.of(vo,aggregateRoot);
//		if (!result.isEmpty()) {
//			return ResponseEntity.ok().body(vo);
//		} else {
//			return ResponseEntity.notFound().build();
//		}
	}

	/**
	 * 使用 response 回傳字串
	 */
	@GetMapping(path = "hello2")
	public void img(HttpServletRequest req, HttpServletResponse response) throws IOException {
		response.getWriter().println("Hello World!");
	}

	/**
	 * 使用 response 回傳資源
	 */
	@GetMapping(path = "hello3")
	public void img2(HttpServletRequest req, HttpServletResponse response) throws IOException {
		try {
		Resource cpr = new ClassPathResource("static/Lenna.jpg");
		response.setContentType("image/"+cpr.getFilename().split("\\.")[1]);
		var x = response.getOutputStream();
		x.write(cpr.getContentAsByteArray());
		x.flush();
		x.close();
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

	/**
	 * 讀取路徑當變數 
	 */
	@GetMapping(path = "hello4/{path1}")
	public ResponseEntity<?> res3(@PathVariable String path1) {
		return ResponseEntity.ok().body(path1);
	}
	
	/**
	 * 讀取 URLParam 當變數
	 */
	@GetMapping(path = "hello5")
	public ResponseEntity<?> res4(
			@RequestParam Map<String, Object> map1) {
		map1.forEach( (K,V)->{
			log.info("方法 hello5 K:{} V:{} ",K,V);
		}
		);
		
		return ResponseEntity.ok().body(map1);
	}
	
	/**
	 * 讀取 PostBody 當變數
	 */
	@PostMapping(path = "hello6")
	public ResponseEntity<?> res5( @RequestBody Map<String, Object> map1 ) {
		map1.forEach( (K,V)->{
			log.info("方法 hello6   Key: {} Value: {} ", K,V);
		}
		);
		
		return ResponseEntity.ok().body(map1);
	}
	
	
	@GetMapping(path = "hello6")
	public ResponseEntity<?> res52( @RequestBody @Nullable Map<String, Object> map1 ) {
		return ResponseEntity.ok().body("請用 POST 方法");
	}

	@GetMapping(path = "hello7")
	public Object res7( @RequestBody @Nullable Map<String, Object> map1 ) {
		Calendar a = Calendar.getInstance();
		a.getTime();
		return  a.getTimeInMillis();
	}
}
