package blackjack.domain.participant;

import blackjack.domain.GameOutcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;
import java.util.Objects;

public class Player extends AbstractParticipant {

    private static final int FIRST_HIT_CARD_SIZE = 2;

    private Player(final String name, final Cards cards, final ParticipantStatus participantStatus) {
        super(name, cards, participantStatus);
    }

    public static Player createNewPlayer(final String name, final Cards cards) {
        Objects.requireNonNull(cards, "cards는 null이 들어올 수 없습니다.");
        return new Player(name, cards, ParticipantStatus.RUNNING);
    }

    @Override
    public List<Card> firstCards() {
        return List.copyOf(cards().subList(0, FIRST_HIT_CARD_SIZE));
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    boolean isEnd() {
        return super.calculateScore() > Cards.BLACKJACK_TARGET_NUMBER;
    }


    public GameOutcome fight(final Dealer dealer) {
        validateFightGame(dealer);
        return GameOutcome.calculateOutcome(this, dealer);
    }

    private void validateFightGame(final Dealer dealer) {
        if (canHit() || dealer.canHit()) {
            throw new IllegalStateException("턴이 종료되지 않아 비교할 수 없습니다.");
        }
    }
}
