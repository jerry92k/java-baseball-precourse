package baseball;

import baseball.domain.NumberQuiz;
import baseball.domain.QuizResult;
import baseball.exception.InvalidInputCommandException;
import nextstep.utils.Console;

public class Application {
    public static void main(String[] args) {
        Application application=new Application();
        application.startGame();
    }

    public void startGame() {
        do{
            gameRoutine();
        }while (isContinueGame());
    }

    public boolean isContinueGame() {
        try {
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            String input = Console.readLine();
            validateGameStateCommand(input);
            return input.equals("1") ? true : false;
        }
        catch (InvalidInputCommandException ex){
            System.out.println(ex.getMessage());
            return isContinueGame();
        }
    }

    public void gameRoutine(){

        NumberQuiz numberQuiz=new NumberQuiz();
        numberQuiz.produceRandomQuiz();
        while (!tryMatchNumber(numberQuiz)){
        }
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
    }

    private void validateGameStateCommand(String input) {

        if(input.equals("1")||input.equals("2")){
            return;
        }
        throw new InvalidInputCommandException("올바른 값이 입력되지 않았습니다.");
    }

    public boolean tryMatchNumber(NumberQuiz numberQuiz){
        try {
            System.out.println("서로 다른 세자리 숫자를 입력해주세요 : ");
            QuizResult quizResult = numberQuiz.solveQuiz(Console.readLine());
            System.out.println(quizResult.getHint());
            return quizResult.isSolved();
        }
        catch (InvalidInputCommandException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
