package baseball.domain;

import java.util.HashSet;
import java.util.Set;

import nextstep.utils.Randoms;

public class NumberQuiz {
	private final int quizSize=3;

	private int[] quizNumbers;

	public NumberQuiz() {
		quizNumbers=new int[quizSize];
	}

	public int[] getQuizNumbers() {
		return quizNumbers;
	}

	public void produceRandomQuiz(){
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
		produceRandomQuiz();
	}

	public QuizResult solveQuiz(String answer){
		validateAnswerLength(answer);
		return solveQuiz(changeStringToIntArr(answer));
	}

	public QuizResult solveQuiz(int[] answer) {

		checkIsNumber(answer);
		QuizResult quizResult = new QuizResult();

		quizResult.setBall(checkBall(answer));
		quizResult.setStrike(checkStrike(answer));

		return quizResult;
	}

	public int checkBall(int[] answer){
		int result=0;
		Set<Integer> quizNumsSet=new HashSet<>();
		for(int number : quizNumbers){
			quizNumsSet.add(number);
		}
		for(int i=0; i<answer.length; i++){
			result+=quizNumbers[i] != answer[i] ? (quizNumsSet.contains(answer[i]) ? 1 : 0) : 0;
		}
		return result;
	}
	public int checkStrike(int[] answer){
		int result=0;

		for(int i=0; i<answer.length; i++){
			result+=quizNumbers[i]==answer[i] ? 1 : 0;
		}
		return result;
	}

	private int[] changeStringToIntArr(String answer){
		int[] answerNums=new int[answer.length()];
		for(int i=0; i<answerNums.length; i++){
			answerNums[i]=answer.charAt(i)-48;;
		}
		return answerNums;
	}

	private void checkIsNumber(int[] answer){
		for(int num : answer){
			validateNumber(num);
		}
	}

	private void validateNumber(int number) {
		if(number<=0 || number>9){
			throw new IllegalArgumentException("[ERROR] 1~9 숫자 세가지 조합으로 입력해주세요.");
		}
	}

	private void validateAnswerLength(String answer){
		if(answer.length()!=quizNumbers.length){
			throw new IllegalArgumentException("[ERROR] "+quizNumbers.length+"자리 숫자를 입력해주세요.");
		}

	}

	public void setQuizNumbers(String numbers){
		validateAnswerLength(numbers);
		quizNumbers= changeStringToIntArr(numbers);
	}

}
