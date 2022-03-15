package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Finished extends AbstractBlackjackGameState {

    public Finished(final Cards cards) {
        super(cards);
    }

    @Override
    public final BlackjackGameState hit(final Card card) {
        throw new IllegalStateException("종료된 상태는 hit을 할 수 없습니다.");
    }

    @Override
    public BlackjackGameState stand() {
        return null;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public int score() {
        return 0;
    }
}
