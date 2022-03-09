package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class Dealer {

    private static final String NAME = "딜러";
    private static final int DEALER_LIMIT_SCORE = 17;

    private final Cards cards;

    private Dealer(final Cards cards) {
        this.cards = cards;
    }

    public Dealer(final List<Card> cards) {
        this(new Cards(cards));
    }

    public List<Card> initCards() {
        return cards.firstCard();
    }

    public boolean isEnd() {
        return cards.calculateScore() >= DEALER_LIMIT_SCORE;
    }

    public void draw(final Card card) {
        validateLimitScore();
        cards.addCard(card);
    }

    private void validateLimitScore() {
        if (cards.calculateScore() >= DEALER_LIMIT_SCORE) {
            throw new IllegalStateException("딜러 카드가 이미 17이상입니다.");
        }
    }

    public int calculateResultScore() {
        validateCanCalculateResultScore();
        return cards.calculateScore();
    }

    private void validateCanCalculateResultScore() {
        if (!isEnd()) {
            throw new IllegalStateException("턴이 종료되지 않아 카드의 합을 계산할 수 없습니다.");
        }
    }

    public List<Card> getCards() {
        validateEndTurn();
        return cards.cards();
    }

    private void validateEndTurn() {
        if (!isEnd()) {
            throw new IllegalStateException("딜러는 턴이 종료되지 않을 때 모든 카드를 반환할 수 없습니다.");
        }
    }

    public String getName() {
        return NAME;
    }
}
