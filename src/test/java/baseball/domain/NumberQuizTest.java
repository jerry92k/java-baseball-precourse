package baseball.domain;

import static org.assertj.core.api.Assertions.*;

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
		Set<Integer> nums = new HashSet<>();
		for(int randomNumber : numberQuiz.getQuizNumbers()) {
			nums.add(randomNumber);
		}
		assertThat(nums).hasSize(3);
	}

	@ParameterizedTest
	@CsvSource(value={"136:163:2","247:743:1"},delimiter=':')
	void checkBall(String correctAnswer, String userAnswer, int ballCount) {
		NumberQuiz numberQuiz = new NumberQuiz();
		numberQuiz.setQuizNumbers(correctAnswer);

		int[] answerNums = new int[userAnswer.length()];
		for(int i = 0; i < answerNums.length; i++) {
			answerNums[i] = userAnswer.charAt(i) - 48;;
		}
		assertThat(numberQuiz.checkBall(answerNums)).isEqualTo(ballCount);

	}

	@ParameterizedTest
	@CsvSource(value={"136:136:3","136:743:0","136:613:0"},delimiter=':')
	void checkStrike(String correctAnswer, String userAnswer, int strikeCount) {
		NumberQuiz numberQuiz = new NumberQuiz();
		numberQuiz.setQuizNumbers(correctAnswer);

		int[] answerNums = new int[userAnswer.length()];
		for(int i = 0; i < answerNums.length; i++) {
			answerNums[i] = userAnswer.charAt(i) - 48;;
		}

		assertThat(numberQuiz.checkStrike(answerNums)).isEqualTo(strikeCount);

	}

	@ParameterizedTest
	@ValueSource(strings={"1654","3","","str","16#"})
	void solveQuizWithInvalidArgument(String input) {
		NumberQuiz numberQuiz = new NumberQuiz();
		numberQuiz.produceRandomQuiz();

		assertThatThrownBy(()->{
			numberQuiz.solveQuiz(input);
		}).isInstanceOf(InvalidInputCommandException.class);

	}

	@ParameterizedTest
	@CsvSource(value={"528:521:2:0","247:743:1:1","359:247:0:0","123:123:3:0"},delimiter=':')
	void solveQuiz(String correctAnswer, String userAnswer, int strikeCount, int ballCount) {
		NumberQuiz numberQuiz = new NumberQuiz();
		numberQuiz.setQuizNumbers(correctAnswer);

		QuizResult quizResult = numberQuiz.solveQuiz(userAnswer);
		assertThat(quizResult.getStrike()).isEqualTo(strikeCount);
		assertThat(quizResult.getBall()).isEqualTo(ballCount);

	}
}