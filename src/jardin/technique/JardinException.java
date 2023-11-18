package jardin.technique;

public class JardinException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String detail;
	
	public JardinException() {
		super();
	}
	
	public JardinException(String message) {
		super();
		this.message = message;
	}
	
	public JardinException(String message, String detail) {
		super();
		this.message = message;
		this.detail = "";
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
