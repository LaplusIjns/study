package tw.linkedin.study.config;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import tw.linkedin.study.model.Employee;

public class CustomSepcification {

	public static Specification<Employee> employeeSpecificationPhone(String inputPhone) {

		return new Specification<Employee>() {

			@Override
			public Predicate toPredicate(
					Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				
				Path<String> phone = root.get("phone");
				Predicate result = criteriaBuilder.like(phone, "%" + inputPhone + "%");
				query.where(result);
				query.orderBy(criteriaBuilder.asc(phone.as(String.class)));
				return query.getRestriction();
			}
		};
	}

	public static Specification<Employee> employeeSpecificationPhoneAndName(String inputPhone, String name) {
		
		return (root, query, builder) -> {
			Path<String> phone = root.get("phone");
			Path<String> ename = root.get("name");
			Predicate pOne = builder.like(phone, "%" + inputPhone + "%");
			Predicate pTwo = builder.like(ename, "%" + name + "%");
			query.where(pOne).where(pTwo).orderBy(builder.asc(phone.as(String.class)));
			return query.getRestriction();
		};
	}
}
