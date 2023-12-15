package tw.linkedin.study.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//@Table(name="EMPLOYEE")
public class Employee {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="NAME")
	private String name;
	
	@Column
	private String phone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Employee(Integer id, String name, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
	}
	
	public Employee() {
		super();
	}
	
	public Employee(String name, String phone) {
		super();
		this.name = name;
		this.phone = phone;
	}
	
}
