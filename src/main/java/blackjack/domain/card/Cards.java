package blackjack.domain.card;

import blackjack.domain.CardDeck;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cards {

    public static final int BLACKJACK_TARGET_NUMBER = 21;
    private static final int BLACKJACK_CARD_SIZE = 2;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        Objects.requireNonNull(cards, "카드에는 null이 들어올 수 없습니다.");
        this.cards = new ArrayList<>(cards);
        validateCardsSize(this.cards);
    }

    private void validateCardsSize(final List<Card> cards) {
        if (cards.size() != BLACKJACK_CARD_SIZE) {
            throw new IllegalArgumentException("카드 2장으로 생성해야 합니다.");
        }
    }

    public static Cards createByCardDeck(final CardDeck cardDeck) {
        return new Cards(Arrays.asList(cardDeck.provideCard(), cardDeck.provideCard()));
    }

    public int calculateScore() {
        final List<CardNumber> cardNumbers = cardNumbers();
        return CardNumber.calculateScore(cardNumbers);
    }

    public int calculateMaxScore() {
        final List<CardNumber> cardNumbers = cardNumbers();
        return CardNumber.calculateMaxScore(cardNumbers);
    }

    private List<CardNumber> cardNumbers() {
        return cards.stream()
                .map(Card::getNumber)
                .collect(Collectors.toUnmodifiableList());
    }

    public void addCard(final Card card) {
        Objects.requireNonNull(card, "null인 카드는 들어올 수 없습니다.");
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
        return calculateScore() > BLACKJACK_TARGET_NUMBER;
    }

    public boolean isBlackJack() {
        return cards.size() == BLACKJACK_CARD_SIZE && calculateScore() == BLACKJACK_TARGET_NUMBER;
    }
}
