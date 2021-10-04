package baseball.exception;

public class InvalidInputCommandException extends RuntimeException {

	/**
	 * @author Kim Jihee
	 * @version 1.0
	 * @since 1.0
	 *
	 * 사용자가 잘못된 값을 입력할 경우 발생하는 오류
	 */

	public InvalidInputCommandException(String message) {
		super("[ERROR] : "+message);
	}
}
