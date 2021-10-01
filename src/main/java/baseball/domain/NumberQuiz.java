package baseball.domain;

import nextstep.utils.Randoms;

public class NumberQuiz {

	private int randomNumber;

	public NumberQuiz() {
		randomNumber= Randoms.pickNumberInRange(1,999);
	}

	public void changeQuiz(){
		randomNumber= Randoms.pickNumberInRange(1,999);
	}
}
