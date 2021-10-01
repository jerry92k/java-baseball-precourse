# 숫자 야구 게임
1부터 9까지 서로 다른 수로 이루어진 3자리의 수를 맞추는 게임이다.

## 기능 요구사항
- 같은 수가 같은 자리에 있으면 스트라이크, 다른 자리에 있으면 볼, 같은 수가 전혀 없으면 포볼 또는 낫싱이란 힌트를 얻고, 그 힌트를 이용해서 먼저 상대방(컴퓨터)의 수를 맞추면 승리한다.
    - [예] 상대방(컴퓨터)의 수가425일 때, 123을 제시한 경우:1스트라이크, 456을 제시한 경우:1스트라이크 1볼, 789를 제시한 경우:낫싱
- 위 숫자 야구게임에서 상대방의 역할을 컴퓨터가 한다. 컴퓨터는 1에서9까지 서로 다른 임의의 수 3개를 선택한다. 게임 플레이어는 컴퓨터가 생각하고 있는 3개의 숫자를 입력하고, 컴퓨터는 입력한 숫자에 대한 결과를 출력한다.
- 이 같은 과정을 반복해 컴퓨터가 선택한 3개의 숫자를 모두 맞히면 게임이 종료 된다.
- 게임을 종료한 후 게임을 다시 시작하거나 완전히 종료할 수 있다.

## 단위 기능 정의
- 게임 플레이어가 맞출 임의의 세자리 숫자 생성한다.
- 플레이어로 부터 정답을 맞추기 위한 데이터를 입력 받는다.
- 플레이어의 입력값과 정답(임의의 세자리 숫자)을 비교하여 결과를 출력한다.
- 게임의 흐름 제어(시작, 종료, 재시작)한다.

