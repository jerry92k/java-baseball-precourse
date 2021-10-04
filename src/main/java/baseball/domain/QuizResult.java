package baseball.domain;

public class QuizResult {

	/**
	 * @author Kim Jihee
	 * @version 1.0
	 * @since 1.0
	 *
	 * 게임 결과 클래스
	 */

	private int strike;
	private int ball;
	private final int correctStrike = 3;

	public QuizResult() {
	}

	public int getStrike() {
		return strike;
	}

	public void setStrike(int strike) {
		this.strike = strike;
	}

	public int getBall() {
		return ball;
	}

	public void setBall(int ball) {
		this.ball = ball;
	}

	public void addStrike(){
		strike++;
	}

	public void addBall(){
		ball++;
	}

	@Override
	public String toString() {
		return "QuizResult{" +
			"strike=" + strike +
			", ball=" + ball +
			'}';
	}

	public boolean isSolved() {
		if(strike == correctStrike) {
			return true;
		}

		return false;
	}

	public String getHint() {
		if(strike == 0 && ball == 0 ) {
			return HintType.Nothing.getValue();
		}
		/* 스트라이크와 볼이 모두 존재하는 경우에는 두 힌트 사이를 공백으로 연결*/
		return strike > 0 && ball > 0 ? String.join(" ", getStrikeHint(), getBallHint()) : getStrikeHint() + getBallHint();
	}

	private String getBallHint() {
		if(ball == 0) {
			return "";
		}

		return ball + HintType.Ball.getValue();
	}

	private String getStrikeHint() {
		if(strike == 0) {
			return "";
		}

		return strike + HintType.Strike.getValue();
	}

}
