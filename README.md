# java-blackjack

# 기능구현목록

### 플레이어

- [x] 사람은 이름을 가진다.
- [x] [Error] 사람의 이름에 null이 들어오면 예외를 발생시킨다.
- [x] [Error] 사람의 이름에 공백이 들어오면 예외를 발생시킨다.
- [ ] [Error] 사람의 이름들 간에 중복이 있으면 예외를 발생시킨다.

### 카드

- [x] 카드는 문양과 번호를 가진다.
- [ ] A는 현재 번호의 +10 스페셜 점수를 반환할 수 있다.
- [x] King, Queen, Jack은 각각 10으로 계산한다.

### 카드덱
- [ ] 중복 없는 카드 52개를 가지는 덱을 생성할 수 있다.
- [x] 카드 2장을 덱에서 꺼내서 반환할 수 있다.
- [x] 카드 1장을 덱에서 꺼내서 반환할 수 있다.
- [x] [Error] 남은 카드의 수보다 많은 카드의 수를 반환하려고 하면 예외를 발생시킨다.

### 게임

- [ ] 첫 턴에 딜러와 참가자 모두는 카드를 2장씩 받는다.

### 유저 턴

- [ ] 본인의 턴에서 카드를 더 받을지 말지를 결정할 수 있다.
- [ ] 카드덱에서 카드를 받을 수 있다.
- [ ] 카드의 합이 21 초과이거나 턴이 종료된다.
- [ ] 카드를 더 받지 않는다고 하면 턴이 종료된다.
- [ ] [Error] 턴이 종료되었는데, 카드를 더 받으려하면 예외를 발생시킨다.

### 딜러 턴

- [ ] 딜러는 카드의 합이 16이하면 카드를 한 장 더 받는다.
- [ ] 딜러는 카드의 합이 17이상이면 턴이 종료된다.
- [ ] [Error] 딜러는 카드의 합이 17이상일 때, 카드를 한 장 더 받으려하면 예외를 발생시킨다.

### 카드 합 계산

- [ ] 카드의 숫자의 합을 반환한다.
- [ ] [Error] 게임 종료되지 않은 사람의 카드 합을 계산할 경우 예외를 발생시킨다.
- [ ] A가 포함될 경우,
    - [ ] 버스트가 아닌 21에 가장 가까운 합을 반환한다.
    - [ ] 모든 상황이 버스트인 경우, 가장 작은 합을 반환한다.
- [ ] K, Q J은 10으로 계산한다.

### 승패 결과

- [ ] 딜러가 Bust인 경우
    - [ ] 유저가 Bust면 무승부한다.
    - [ ] 유저가 Bust가 아니면 유저가 승리한다.
- [ ] 딜러가 Bust가 아닌 경우
    - [ ] 유저가 Bust면 딜러가 승리한다.
    - [ ] 유저와 딜러의 카드 합이 같으면 무승부한다.
    - [ ] 유저가 Bust가 아니면, 21에 가까운 카드 합을 가진 사람이 승리한다.
- [ ] BlackJack
    - [ ] 유저와 딜러가 모두 블랙잭이면 무승부한다.
    - [ ] 블랙잭인 사람이 승리한다.