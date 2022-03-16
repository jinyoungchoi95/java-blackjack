package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class DealerRunning extends Running {

    public DealerRunning(final Cards cards) {
        super(cards);
    }

    @Override
    public BlackjackGameState hit(final Card card) {
        cards.addCard(card);
        if (cards.isMaxScoreBust()) {
            return new Bust(cards, cards.maxScore());
        }
        if (cards.maxScore() >= 17) {
            return new Stand(cards, cards.maxScore());
        }
        return new DealerRunning(cards);
    }
}
