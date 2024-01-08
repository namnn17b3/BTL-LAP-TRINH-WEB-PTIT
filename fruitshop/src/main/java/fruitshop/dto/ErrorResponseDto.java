package fruitshop.dto;

public class ErrorResponseDto {
	private int status;
	private String message;
	
	public ErrorResponseDto() {}

	public ErrorResponseDto(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorResponseDto [status=" + status + ", message=" + message + "]";
	}
}
