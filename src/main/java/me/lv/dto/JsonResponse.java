package me.lv.dto;

/**
 * 返回给前端的JSON response 封装
 * 
 * @author u
 * 
 */
public class JsonResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3114689169200636598L;

	private String errorTrace;

	public String getErrorTrace() {
		return errorTrace;
	}

	public void setErrorTrace(String errorTrace) {
		this.errorTrace = errorTrace;
	}

}
