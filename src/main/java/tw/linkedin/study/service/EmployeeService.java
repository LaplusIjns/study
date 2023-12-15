package tw.linkedin.study.service;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.linkedin.study.model.Employee;
import tw.linkedin.study.repository.EmployeeRepository;

@Service
public class EmployeeService{

	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

	
	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> listall() {
		List<Employee> result = employeeRepository.findAll();
		return result;
	}

	public Employee save(Employee employee) {
		Employee result = employeeRepository.save(employee);
		return result;
	}

	public Employee selectById(Integer Id) {
		Optional<Employee> result = employeeRepository.findById(Id);
		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}

	

}
