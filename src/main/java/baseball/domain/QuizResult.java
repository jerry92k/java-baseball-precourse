package baseball.domain;

import java.util.Set;

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

	public String getResultPrint(){
		if(strike==0 && ball==0 ){
			return HintType.Nothing.getValue();
		}
		StringBuilder result=new StringBuilder();

		if(strike>0){
			result.append(strike);
			result.append(HintType.Strike.getValue());
			result.append(" ");
			// ball이 0개인 경우 출력시 마지막에 공백이 포함됨. 단순 출력이 아닌 경우에는 strike만 존재하는 경우를 고려하여 공백없는 문자열 생성 해야함
		}

		if(ball>0){
			result.append(ball);
			result.append(HintType.Ball.getValue());
		}

		return result.toString();
	}
}
