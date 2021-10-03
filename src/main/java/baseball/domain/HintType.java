package baseball.domain;

enum HintType {

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
