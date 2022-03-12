package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cards {

    public static final int BLACK_JACK_TARGET_NUMBER = 21;
    private static final int BLACK_JACK_SIZE = 2;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        Objects.requireNonNull(cards, "카드에는 null이 들어올 수 없습니다.");
        this.cards = new ArrayList<>(cards);
        validateCardsSize(this.cards);
    }

    private void validateCardsSize(final List<Card> cards) {
        if (cards.size() != BLACK_JACK_SIZE) {
            throw new IllegalArgumentException("카드 2장으로 생성해야 합니다.");
        }
    }

    public int calculateScore() {
        final List<CardNumber> cardNumbers = cards.stream()
                .map(Card::getNumber)
                .collect(Collectors.toUnmodifiableList());
        return CardNumber.calculateScore(cardNumbers);
    }

    public void addCard(final Card card) {
        validateDuplicateCard(card);
        cards.add(card);
    }

    private void validateDuplicateCard(final Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException("중복된 카드를 추가할 수 없습니다.");
        }
    }

    public List<Card> cards() {
        return List.copyOf(cards);
    }

    public boolean isBust() {
        return calculateScore() > BLACK_JACK_TARGET_NUMBER;
    }

    public boolean isBlackJack() {
        return cards.size() == BLACK_JACK_SIZE && calculateScore() == BLACK_JACK_TARGET_NUMBER;
    }
}
