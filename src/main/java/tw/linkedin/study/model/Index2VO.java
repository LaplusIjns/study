package tw.linkedin.study.model;

import java.util.List;

public class Index2VO {

	
	private Integer serno;
	private String productName;
	private List<String> version;
	
	public Integer getSerno() {
		return serno;
	}
	public void setSerno(Integer serno) {
		this.serno = serno;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public List<String> getVersion() {
		return version;
	}
	public void setVersion(List<String> version) {
		this.version = version;
	}
	public Index2VO(Integer serno, String productName, List<String> version) {
		super();
		this.serno = serno;
		this.productName = productName;
		this.version = version;
	}
	
	

}
