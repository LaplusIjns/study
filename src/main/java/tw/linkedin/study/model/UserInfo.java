package tw.linkedin.study.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfo extends Employee implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String companyCode;
	private String deptCode;
	
	

	public UserInfo(String name, String phone, String companyCode, String deptCode) {
		super(name, phone);
		this.companyCode = companyCode;
		this.deptCode = deptCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.getPhone() ;
	}

	@Override
	public String getUsername() {
		return this.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "UserInfo [companyCode=" + companyCode + ", deptCode=" + deptCode + ", name=" +super.getName() + ", phone=" +super.getPhone() + "]";
	}
	
	

}
