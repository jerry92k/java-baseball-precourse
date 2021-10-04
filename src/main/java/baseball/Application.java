package baseball;

import baseball.domain.NumberQuiz;
import baseball.domain.QuizResult;
import baseball.exception.InvalidInputCommandException;
import nextstep.utils.Console;

public class Application {

    public static void main(String[] args) {
        Application application = new Application();
        application.startGame();
    }

    /* 게임 시작 인입 지점 */
    public void startGame() {
        do{
            gameRoutine();
        } while (isContinueGame());
    }

    /* 플레이어의 정답 후 다시 시작 혹은 종료 흐름 제어 */
    public boolean isContinueGame() {
        try {
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            String input = Console.readLine();
            validateGameStateCommand(input);
            return input.equals("1") ? true : false;
        } catch (InvalidInputCommandException ex) {
            System.out.println(ex.getMessage());
            return isContinueGame();
        }
    }

    /* 게임 문제를 생성하여 플레이어가 정답을 맞출 때 까지 흐름 제어 */
    public void gameRoutine(){
        NumberQuiz numberQuiz = new NumberQuiz();
        numberQuiz.produceRandomQuiz();

        while (!tryMatchNumber(numberQuiz)) {}

        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
    }

    private void validateGameStateCommand(String input) {
        if(input.equals("1") || input.equals("2")) {
            return;
        }

        throw new InvalidInputCommandException("올바른 값이 입력되지 않았습니다.");
    }

    /* 플레이어의 입력에 따른 결과를 플레이어에게 리턴 */
    public boolean tryMatchNumber(NumberQuiz numberQuiz){
        try {
            System.out.println("서로 다른 세자리 숫자를 입력해주세요 : ");
            QuizResult quizResult = numberQuiz.solveQuiz(Console.readLine());
            System.out.println(quizResult.getHint());
            return quizResult.isSolved();
        } catch (InvalidInputCommandException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
