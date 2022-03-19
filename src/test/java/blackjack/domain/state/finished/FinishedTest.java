package blackjack.domain.state.finished;

import static blackjack.domain.card.Denomination.A;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Suit.SPADES;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.BlackjackGameState;
import java.util.List;
import org.junit.jupiter.api.Test;

class FinishedTest {

    @Test
    void 최종_수익_계산() {
        final Cards cards = new Cards(List.of(new Card(SPADES, KING), new Card(SPADES, QUEEN), new Card(SPADES, A)));
        final BlackjackGameState stand = new Stand(cards, cards.score());

        final Cards compareCards = new Cards(List.of(new Card(SPADES, KING), new Card(SPADES, QUEEN)));
        final BlackjackGameState compareStand = new Stand(compareCards, compareCards.score());

        assertThat(stand.profit(1000, compareStand)).isEqualTo(1000);
    }
}
