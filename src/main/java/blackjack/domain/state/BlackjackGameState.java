package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public interface BlackjackGameState {

    BlackjackGameState hit(final Card card);

    BlackjackGameState stay();

    boolean isFinished();

    double profit(final int betMoney, final BlackjackGameState blackjackGameState);

    boolean isBust();

    boolean isBlackjack();

    int score();

    Cards cards();
}
