package blackjack.domain.state.finished;

import static blackjack.domain.card.Denomination.A;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.domain.card.Suit.SPADES;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.BlackjackGameState;
import java.util.List;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    void 서로_블랙잭이면_수익률이_제로() {
        final Cards cards = new Cards(List.of(new Card(SPADES, A), new Card(SPADES, KING)));
        final Blackjack blackjack = new Blackjack(cards);
        final BlackjackGameState compareState = new Blackjack(cards);

        assertThat(blackjack.earningRate(compareState)).isEqualTo(0);
    }

    @Test
    void 본인만_블랙잭이면_수익률이_1_5배() {
        final Cards cards = new Cards(List.of(new Card(SPADES, A), new Card(SPADES, KING)));
        final Blackjack blackjack = new Blackjack(cards);

        final Cards compareCards =
                new Cards(List.of(new Card(SPADES, KING), new Card(SPADES, QUEEN), new Card(SPADES, TWO)));
        final BlackjackGameState compareState = new Bust(compareCards, compareCards.score());

        assertThat(blackjack.earningRate(compareState)).isEqualTo(1.5);
    }
}
