package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

    @ParameterizedTest
    @NullSource
    @DisplayName("플레이어의 이름에 null이 들어올 경우 예외가 발생해야 한다.")
    void createExceptionByNull(String input) {
        assertThatThrownBy(() -> new Player(input, true))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[Error] 플레이어의 이름은 null이 들어올 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("플레이어의 이름에 공백이 들어올 경우 예외가 발생해야 한다.")
    void createExceptionByEmpty(String input) {
        assertThatThrownBy(() -> new Player(input, true))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[Error] 플레이어의 이름은 공백이 들어올 수 없습니다.");
    }

    @Nested
    @DisplayName("드로우 가능 여부를 확인할 수 있다.")
    class CanDraw {

        @Test
        @DisplayName("가능한 경우 true를 반환한다.")
        void canDraw() {
            final Player player = new Player("user", true);
            assertTrue(player.canDraw());
        }

        @Test
        @DisplayName("불가능한 경우 false를 반환한다.")
        void cannotDraw() {
            final Player player = new Player("user", false);
            assertFalse(player.canDraw());
        }
    }

    @Test
    @DisplayName("드로우가 불가능한데, 카드를 받으려 하면 예외를 발생시킨다.")
    void drawException() {
        final Player player = new Player("user", false);
        assertThatThrownBy(() -> player.draw(Card.cards().get(0)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 턴이 종료되었으면 카드를 받을 수 없습니다.");
    }
}
