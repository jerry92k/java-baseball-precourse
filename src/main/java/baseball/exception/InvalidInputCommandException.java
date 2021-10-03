package baseball.exception;

/**
 * 사용자가 잘못된 값을 입력할 경우 발생하는 오류
 */
public class InvalidInputCommandException extends RuntimeException {

	public InvalidInputCommandException(String message) {
		super("[ERROR] : "+message);
	}
}
