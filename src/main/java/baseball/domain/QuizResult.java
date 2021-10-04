package baseball.domain;

/**
 * 사용자가 게임을 푼 결과 객체
 */

public class QuizResult {
	private int strike;
	private int ball;

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
		if(strike==3) {
			return true;
		}
		return false;
	}

	public String getHint() {
		if(strike==0 && ball==0 ) {
			return HintType.Nothing.getValue();
		}
		String strikeHint=getStrikeHint();
		String ballHint=getBallHint();
		return strike>0 && ball>0 ?
			String.join(" ",strikeHint,ballHint) : strikeHint+ballHint;
	}

	private String getBallHint() {
		if(ball==0) {
			return "";
		}
		return ball+HintType.Ball.getValue();
	}

	private String getStrikeHint() {
		if(strike==0) {
			return "";
		}
		return strike+HintType.Strike.getValue();
	}

}
