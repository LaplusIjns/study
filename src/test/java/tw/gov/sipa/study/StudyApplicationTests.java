package tw.gov.sipa.study;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sun.tools.attach.AttachNotSupportedException;

import tw.linkedin.study.repository.EmployeeRepository;
import tw.linkedin.study.service.EmployeeService;
import tw.linkedin.study.service.EmployeeService;
import tw.linkedin.study.StudyApplication 

@SpringBootTest(classes = StudyApplication .class)
class StudyApplicationTests {
	
	
	private static final Logger log = LoggerFactory.getLogger(StudyApplicationTests.class);


	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Test
	void list() throws IntrospectionException {
	}
	
//	@Test
//	void list() {
//		List<Employee> emplyoeePhoneHave1 = 
//				employeeRepository.findAll(CustomSepcification.employeeSpecificationPhone("1"));
//		
//		for (Employee employee : emplyoeePhoneHave1) {
//			System.out.println(employee.getName()+" "+employee.getPhone());
//		}
//		
//		System.out.println("===");
//	}
//	
//	@Test
//	void list2() {
//		List<Employee> emplyoeePhoneHave1 = 
//				employeeRepository.findAll(CustomSepcification.employeeSpecificationPhoneAndName("1","王"));
//		
//		for (Employee employee : emplyoeePhoneHave1) {
//			System.out.println(employee.getName()+" "+employee.getPhone());
//		}
//		System.out.println("===");
//	}
//	
//	@Test
//	void list3() {
//		List<Employee> emplyoeePhoneHave1 = 
//				employeeRepository.testQuery("1234");
//		
//		for (Employee employee : emplyoeePhoneHave1) {
//			System.out.println(employee.getName());
//		}
//	}
	
	@Test
	void list4() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException, AttachNotSupportedException {
	}
	
	/**
	 * 列出員工
	 */
//	@SuppressWarnings("unchecked")
//	@Test
//	void list() {
////		var listEmployee = employeeService.listall();
////		for (Employee employee : listEmployee) {
////			log.info("{}",employee.getName());
////		}
//		
//	}
	
	/**
	 * 新增員工
	 */
//	@Test
//	void save() {
//		var listEmployee3 = employeeService.listall();
//		for (Employee employee2 : listEmployee3) {
//			log.info("編號: {} 姓名: {}",employee2.getId(), employee2.getName());
//		}
//		Employee employee = new Employee("曹操","123");
//		Employee result = employeeService.save(employee);
//		System.out.println(result);
//		
//		
//		var listEmployee4 = employeeService.selectById(1);
//		listEmployee4.setName("關羽");
//		employeeService.save(listEmployee4);
//		
//		var listEmployee = employeeService.listall();
//		for (Employee employee2 : listEmployee) {
//			log.info("編號: {} 姓名: {}",employee2.getId(), employee2.getName());
//		}
		
//		var x = employeeRepository.findByName("張三");
//		x.ifPresent(e->{System.out.println(e.getName());System.out.println(e.getName());System.out.println(e.getName());System.out.println(e.getName());});
//		System.out.println("11111111111111");
//		a("111");
//		a(null);
//	}
}
