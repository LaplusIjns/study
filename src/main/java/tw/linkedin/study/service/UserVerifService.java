package tw.linkedin.study.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tw.linkedin.study.model.Employee;
import tw.linkedin.study.model.UserInfo;
import tw.linkedin.study.repository.EmployeeRepository;

@Service
public class UserVerifService implements UserDetailsService {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserVerifService.class);

	
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	/**
	 * 姓名當 username 電話當 password 無身分
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			Optional<Employee> result = employeeRepository.findByName(username);
			if (result.isPresent()) {
				log.info("使用者: {} 登入",result.get().getName());
				return new UserInfo(result.get().getName(), result.get().getPhone(),"HSP","IT");

			} else {
				log.info("使用者: {} 不存在",username);
				throw new UsernameNotFoundException("該帳號不存在");
			}
	}

}
