package baseball.domain;

import java.util.HashSet;
import java.util.Set;

import baseball.exception.InvalidInputCommandException;

import nextstep.utils.Randoms;

/**
 * 3개의 서로 다른 숫자로 이루어진 게임
 */

public class NumberQuiz {
	private final int quizSize = 3;

	private int[] quizNumbers;

	public NumberQuiz() {
		quizNumbers = new int[quizSize];
	}

	public int[] getQuizNumbers() {
		return quizNumbers;
	}

	public void produceRandomQuiz() {
		Set<Integer> picked = new HashSet<>();
		for(int i = 0; i < quizNumbers.length; i++) {
			// 서로 다른 세개의 숫자를 추출하기 위해, 중복된 숫자를 뽑을 경우 다시 뽑기
			quizNumbers[i] = makeRandomNumber(picked);
			picked.add(quizNumbers[i]);
		}
	}

	private int makeRandomNumber(Set<Integer> picked) {
		int random = Randoms.pickNumberInRange(1,9);
		while(picked.contains(random)) {
			random = Randoms.pickNumberInRange(1,9);
		}
		return random;
	}

	public QuizResult solveQuiz(String answer) {
		validateAnswerLength(answer);
		return solveQuiz(changeStringToIntArr(answer));
	}

	public QuizResult solveQuiz(int[] answer) {
		checkIsNumber(answer);
		validateDistinctNumbers(answer);
		QuizResult quizResult = new QuizResult();
		quizResult.setBall(checkBall(answer));
		quizResult.setStrike(checkStrike(answer));
		return quizResult;
	}

	public int checkBall(int[] answer) {
		int result = 0;
		Set<Integer> quizNumsSet = getDistinctSet(quizNumbers);
		for(int i = 0; i<answer.length; i++) {
			result += quizNumbers[i] != answer[i] ? (quizNumsSet.contains(answer[i]) ? 1 : 0) : 0;
		}
		return result;
	}
	public int checkStrike(int[] answer) {
		int result = 0;
		for(int i = 0; i < answer.length; i++) {
			result += quizNumbers[i] == answer[i] ? 1 : 0;
		}
		return result;
	}

	private int[] changeStringToIntArr(String answer) {
		int[] answerNums = new int[answer.length()];
		for(int i = 0; i < answerNums.length; i++) {
			answerNums[i] = answer.charAt(i) - 48;
		}
		return answerNums;
	}

	private void checkIsNumber(int[] answer) {
		for(int num : answer) {
			validateNumber(num);
		}
	}

	private void validateNumber(int number) {
		if(number <= 0 || number > 9) {
			throw new InvalidInputCommandException("1~9 사이 숫자 세 가지 조합으로 입력해주세요.");
		}
	}

	private void validateAnswerLength(String answer) {
		if(answer.length() != quizNumbers.length) {
			throw new InvalidInputCommandException("1~9 사이 숫자 세 가지 조합으로 입력해주세요.");
		}
	}

	public void setQuizNumbers(String numbers) {
		validateAnswerLength(numbers);
		quizNumbers= changeStringToIntArr(numbers);
	}

	private void validateDistinctNumbers(int[] answer) {
		Set<Integer> answerSet = getDistinctSet(answer);
		if(answerSet.size() < quizSize) {
			System.out.println("[INFO] : 정답은 서로 다른 세 개의 수로 이루어진 세 자리 수 입니다. 중복된 숫자는 존재하지 않습니다.");
		}
	}

	private Set<Integer> getDistinctSet(int[] numbers) {
		Set<Integer> answerSet = new HashSet<>();
		for(int number : numbers) {
			answerSet.add(number);
		}
		return answerSet;
	}

}
