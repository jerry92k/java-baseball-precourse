package baseball.domain;

import java.util.HashSet;
import java.util.Set;

import baseball.exception.InvalidInputCommandException;

import nextstep.utils.Randoms;

public class NumberQuiz {

	/**
	 * @author Kim Jihee
	 * @version 1.0
	 * @since 1.0
	 *
	 * 게임 객체. 게임을 셋팅하고 게임 결과를 생성
	 */

	private final int quizSize = 3;

	private int[] quizNumbers;

	public NumberQuiz() {
		quizNumbers = new int[quizSize];
	}

	public int[] getQuizNumbers() {
		return quizNumbers;
	}

	/* 게임 문제가 될 서로 다른 세개의 숫자를 생성하여 int형 배열에 저장 */
	public void produceRandomQuiz() {
		Set<Integer> picked = new HashSet<>();

		for(int i = 0; i < quizNumbers.length; i++) {
			quizNumbers[i] = makeRandomNumber(picked);
			picked.add(quizNumbers[i]);
		}
	}

	private int makeRandomNumber(Set<Integer> picked) {
		int random = Randoms.pickNumberInRange(1,9);

		// 중복된 숫자를 뽑을 경우 다시 생성
		while(picked.contains(random)) {
			random = Randoms.pickNumberInRange(1,9);
		}

		return random;
	}

	/* String 타입으로 받은 입력값을 int[] 타입 배열로 변환하여 내부 호출 */
	public QuizResult solveQuiz(String answer) {
		return solveQuiz(changeStringToIntArr(answer));
	}

	/* 플레이어의 입력값에 대한 풀이 결과를 QuizResult로 리턴 */
	public QuizResult solveQuiz(int[] answer) {
		validateQuizProdueced();
		validateAnswer(answer);

		QuizResult quizResult = new QuizResult();
		quizResult.setBall(checkBall(answer));
		quizResult.setStrike(checkStrike(answer));
		return quizResult;
	}

	/* 플레이어 입력값에 대한 유효성 검증 */
	private void validateAnswer(int[] answer){
		validateAnswerLength(answer);
		checkIsNumber(answer);
		validateDistinctNumbers(answer);
	}

	/* produceRandomQuiz() 호출 전 solveQuiz() 호출하는 경우 예외 처리 */
	private void validateQuizProdueced(){
		if(quizNumbers==null){
			throw new IllegalStateException("[ERROR] : 문제 생성이 제대로 되지 않았습니다. 문제 생성을 먼저 해주세요.\n[HINT] produceRandomQuiz()");
		}

		if(getDistinctSet(quizNumbers).contains(0)){
			throw new IllegalStateException("[ERROR] : 문제 생성이 제대로 되지 않았습니다. 문제 생성을 먼저 해주세요.\n[HINT] produceRandomQuiz()");
		}
	}

	/* 볼 갯수 카운트 */
	private int checkBall(int[] answer) {
		Set<Integer> quizNumsSet = getDistinctSet(quizNumbers);
		int result = 0;

		for(int i = 0; i<answer.length; i++) {
			result += quizNumbers[i] != answer[i] ? (quizNumsSet.contains(answer[i]) ? 1 : 0) : 0;
		}

		return result;
	}

	/* 스트라이크 갯수 카운트 */
	private int checkStrike(int[] answer) {
		int result = 0;

		for(int i = 0; i < answer.length; i++) {
			result += quizNumbers[i] == answer[i] ? 1 : 0;
		}

		return result;
	}

	/* String을 int형 배열로 변환 */
	private int[] changeStringToIntArr(String answer) {
		int[] answerNums = new int[answer.length()];

		for(int i = 0; i < answerNums.length; i++) {
			answerNums[i] = answer.charAt(i) - 48;
		}

		return answerNums;
	}

	/* 입력값에 숫자가 아닌 문자가 있는지 체크 */
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

	private void validateAnswerLength(int[] answer) {
		if(answer.length!=quizSize) {
			throw new InvalidInputCommandException("1~9 사이 숫자 세 가지 조합으로 입력해주세요.");
		}
	}

	public void setQuizNumbers(String numbers) {
		int[] quizNums = changeStringToIntArr(numbers);
		validateAnswerLength(quizNums);
		checkIsNumber(quizNums);
		quizNumbers=quizNums;
	}

	/* 플레이어 입력값에 중복된 숫자가 존재하는 경우 알림 메세지 출력 */
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
