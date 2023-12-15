package tw.linkedin.study.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tw.linkedin.study.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>,JpaSpecificationExecutor<Employee> {

	Optional<Employee> findByName(String name);
	
	
	@Query(value="SELECT * FROM EMPLOYEE WHERE NAME = ?1",nativeQuery = true)
	List<Employee> testQuery(String name);
}
