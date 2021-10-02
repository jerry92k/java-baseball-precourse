package baseball.domain;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class NumberQuizTest {

	@RepeatedTest(100)
	@DisplayName("서로 다른 세자리 숫자 생성 확인")
	void produceQuiz(){
		NumberQuiz numberQuiz=new NumberQuiz();
		Set<Integer> nums=new HashSet<>();
		for(int randomNumber : numberQuiz.getQuizNumbers()){
			nums.add(randomNumber);
		}
		Assertions.assertThat(nums).hasSize(3);
	}


}