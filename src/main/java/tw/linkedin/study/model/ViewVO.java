package tw.linkedin.study.model;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;

public class ViewVO extends RepresentationModel<ViewVO> {

	private HttpStatus httpStatus;
	private Pageable pageable;
	private Object data;
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public Pageable getPageable() {
		return pageable;
	}
	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
