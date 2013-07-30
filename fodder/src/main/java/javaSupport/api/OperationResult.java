package javaSupport.api;

public class OperationResult {
	private ApiStatus status;
	private String message;

	public OperationResult(ApiStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public ApiStatus getStatus() {
		return status;
	}

	public void setStatus(ApiStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
