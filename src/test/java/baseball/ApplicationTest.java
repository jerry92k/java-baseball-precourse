package baseball;

import nextstep.test.NSTest;
import nextstep.utils.Randoms;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mockStatic;

public class ApplicationTest extends NSTest {
    @BeforeEach
    void beforeEach() {
        super.setUp();
    }

    @Test
    void 낫싱() {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            mockRandoms
                    .when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                    .thenReturn(1, 3, 5);
            running("246");
            verify("낫싱");
        }
    }

    @Test
    void 게임종료_후_재시작() {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                    .thenReturn(7, 1, 3)
                    .thenReturn(5, 8, 9);
            run("713", "1", "597", "589", "2");
            verify("3스트라이크", "게임 끝", "1스트라이크 1볼");
        }
    }

    @ParameterizedTest
    @ValueSource(strings={"3","0","00","exit"})
    void 유효하지않은_게임_흐름_명령어(String command) {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                .thenReturn(5, 1, 3);
            run("523", "513", command, "2");
            verify("2스트라이크","3스트라이크","올바른 값이 입력되지 않았습니다.","올바른 값이 입력되지 않았습니다.", "게임 끝");
        }
    }

    @Test
    void 중복숫자_입력_알림() {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            mockRandoms
                .when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                .thenReturn(1, 3, 5);
            running("333");
            verify("중복된 숫자는 존재하지 않습니다.","1스트라이크 2볼");
        }
    }

    @ParameterizedTest
    @ValueSource(strings={"53a","fsg","#$2","2한글"})
    void 숫자가_아닌값_입력(String input) {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            mockRandoms
                .when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                .thenReturn(1, 3, 5);
            running(input);
            verify("[ERROR] : 1~9 사이 숫자 세 가지 조합으로 입력해주세요.");
        }
    }

    @ParameterizedTest
    @ValueSource(strings={"001","530","20","300"})
    void 유효하지않은_숫자_입력(String input) {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            mockRandoms
                .when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                .thenReturn(1, 3, 5);
            running(input);
            verify("[ERROR] : 1~9 사이 숫자 세 가지 조합으로 입력해주세요.");
        }
    }

    @AfterEach
    void tearDown() {
        outputStandard();
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
