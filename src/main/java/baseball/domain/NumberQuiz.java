package baseball.domain;

import java.util.HashSet;
import java.util.Set;

import nextstep.utils.Randoms;

public class NumberQuiz {

	private int[] quizNumbers;

	public NumberQuiz() {
		quizNumbers=new int[3];
		produceQuiz();
	}

	public int[] getQuizNumbers() {
		return quizNumbers;
	}

	private void produceQuiz(){
		Set<Integer> picked=new HashSet<>();

		for(int i=0; i<quizNumbers.length; i++){

			// 서로 다른 세개의 숫자를 추출하기 위해, 중복된 숫자를 뽑을 경우 다시 뽑기
			quizNumbers[i]=makeRandomNumber(picked);
			picked.add(quizNumbers[i]);
		}
	}

	private int makeRandomNumber(Set<Integer> picked){
		int random= Randoms.pickNumberInRange(1,9);
		while(picked.contains(random)){
			random= Randoms.pickNumberInRange(1,9);
		}
		return random;
	}

	public void changeQuiz(){
		produceQuiz();
	}
}
