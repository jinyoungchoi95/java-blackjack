package blackjack.domain.participant;

import blackjack.domain.GameOutcome;
import blackjack.domain.card.Card;
import java.util.List;

public interface Participant {

    List<Card> firstDrawCard();

    void draw(final Card card);

    boolean canDraw();

    void changeFinishStatus();

    int calculateResultScore();

    GameOutcome fightResult(final Participant participant);

    List<Card> cards();

    String getName();
}
