package baseball.domain;

enum HintType {

	/**
	 * @author Kim Jihee
	 * @version 1.0
	 * @since 1.0
	 *
	 * 힌트 종류 정의
	 */

	Strike("스트라이크"),
	Ball("볼"),
	Nothing("낫싱");

	private String value;

	HintType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
