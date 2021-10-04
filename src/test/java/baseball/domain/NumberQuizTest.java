package baseball.domain;

import static org.assertj.core.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import baseball.exception.InvalidInputCommandException;

class NumberQuizTest {

	@RepeatedTest(100)
	@DisplayName("서로 다른 세자리 숫자 생성 확인")
	void produceQuiz() {
		NumberQuiz numberQuiz = new NumberQuiz();
		numberQuiz.produceRandomQuiz();

		Set<Integer> nums = new HashSet<>();
		for(int randomNumber : numberQuiz.getQuizNumbers()) {
			nums.add(randomNumber);
		}
		assertThat(nums).hasSize(3);
	}

	/* 자바 리플렉션을 이용하여 private 매소드인 checkBall, checkStrike 테스트 */

	@ParameterizedTest
	@DisplayName("checkBall 함수 기능 테스트")
	@CsvSource(value={"136:163:2","247:743:1"},delimiter=':')
	void checkBall(String correctAnswer, String userAnswer, int ballCount) throws
		NoSuchMethodException,
		InvocationTargetException,
		IllegalAccessException {

		NumberQuiz numberQuiz = new NumberQuiz();
		numberQuiz.setQuizNumbers(correctAnswer);

		int[] answerNums = changeStringToIntArr(userAnswer);

		Method method = NumberQuiz.class.getDeclaredMethod("checkBall",int[].class);
		method.setAccessible(true);

		assertThat((int) method.invoke(numberQuiz, answerNums)).isEqualTo(ballCount);
	}

	@ParameterizedTest
	@DisplayName("checkStrike 함수 기능 테스트")
	@CsvSource(value={"136:136:3","136:743:0","136:613:0"},delimiter=':')
	void checkStrike(String correctAnswer, String userAnswer, int strikeCount) throws
		NoSuchMethodException,
		InvocationTargetException,
		IllegalAccessException {
		NumberQuiz numberQuiz = new NumberQuiz();
		numberQuiz.setQuizNumbers(correctAnswer);

		int[] answerNums = changeStringToIntArr(userAnswer);

		Method method = NumberQuiz.class.getDeclaredMethod("checkStrike",int[].class);
		method.setAccessible(true);

		assertThat((int) method.invoke(numberQuiz, answerNums)).isEqualTo(strikeCount);
	}

	private int[] changeStringToIntArr(String answer) {
		int[] answerNums = new int[answer.length()];

		for(int i = 0; i < answerNums.length; i++) {
			answerNums[i] = answer.charAt(i) - 48;
		}

		return answerNums;
	}

	@ParameterizedTest
	@DisplayName("유효하지 않은 값으로 답 입력시 예외 발생 테스트")
	@ValueSource(strings={"1654","3","","str","16#"})
	void solveQuizWithInvalidArgument(String input) {
		NumberQuiz numberQuiz = new NumberQuiz();
		numberQuiz.produceRandomQuiz();

		assertThatThrownBy(()->{
			numberQuiz.solveQuiz(input);
		}).isInstanceOf(InvalidInputCommandException.class);

	}

	@ParameterizedTest
	@DisplayName("플레이어의 입력 답에 대한 풀이 결과 생성 테스트")
	@CsvSource(value={"528:521:2:0","247:743:1:1","359:247:0:0","123:123:3:0"},delimiter=':')
	void solveQuiz(String correctAnswer, String userAnswer, int strikeCount, int ballCount) {
		NumberQuiz numberQuiz = new NumberQuiz();
		numberQuiz.setQuizNumbers(correctAnswer);

		QuizResult quizResult = numberQuiz.solveQuiz(userAnswer);
		assertThat(quizResult.getStrike()).isEqualTo(strikeCount);
		assertThat(quizResult.getBall()).isEqualTo(ballCount);

		String expectedHint = getExpectedHint(strikeCount, ballCount);

		assertThat(quizResult.getHint()).isEqualTo(expectedHint);
	}

	String getExpectedHint(int strikeCount, int ballCount){
		if(strikeCount == 0 && ballCount == 0){
			return "낫싱";
		}
		String expectedHint="";

		if(strikeCount > 0) {
			expectedHint = strikeCount + "스트라이크";
		}

		if(ballCount > 0) {
			expectedHint = expectedHint.length() > 0 ? String.join(" ", expectedHint, ballCount + "볼") : ballCount + "볼";
		}

		return expectedHint;
	}
}