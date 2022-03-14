package blackjack.view;

import blackjack.domain.GameOutcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.dto.OutComeResult;
import blackjack.dto.ParticipantCards;
import blackjack.dto.ParticipantScoreResult;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PROVIDE_FIRST_HIT_CARD_TO_PLAYER_MESSAGE = "%s와 %s에게 2장을 나누었습니다.%n";
    private static final String PROVIDED_CARD_TO_DEALER_CARD_MESSAGE = "%s: %s%n";
    private static final String PROVIDED_CARD_TO_PLAYER_CARD_MESSAGE = "%s 카드: %s%n";

    private static final String PROVIDE_CARD_TO_DEALER_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";

    private static final String PLAYER_CARD_RESULT_AND_SCORE_MESSAGE = "%s 카드: %s - 결과: %d%n";

    private static final String OUTCOME_TITLE = "## 최종 승패";
    private static final String DEALER_OUTCOME_RESULT_MESSAGE = "딜러: %s%n";
    private static final String PLAYER_OUTCOME_RESULT_MESSAGE = "%s: %s%n";

    private static final String PLAYER_NAME_DELIMITER = ", ";
    private static final String CARD_DELIMITER = ", ";

    private OutputView() {
        throw new AssertionError();
    }

    public static void showPlayersFirstCards(final ParticipantCards dealerCards, final List<ParticipantCards> playerCards) {
        System.out.printf(PROVIDE_FIRST_HIT_CARD_TO_PLAYER_MESSAGE, dealerCards.getName(), joinPlayerNames(playerCards));
        System.out.printf(PROVIDED_CARD_TO_DEALER_CARD_MESSAGE,
                dealerCards.getName(), joinPlayerCards(dealerCards.getCards()));
        playerCards.forEach(OutputView::printPlayerCards);
    }

    private static String joinPlayerNames(final List<ParticipantCards> playerCards) {
        return playerCards.stream()
                .map(ParticipantCards::getName)
                .collect(Collectors.joining(PLAYER_NAME_DELIMITER));
    }

    private static String joinPlayerCards(final List<Card> cards) {
        return cards.stream()
                .map(card -> joinCard(card.getPattern(), card.getNumber()))
                .collect(Collectors.joining(CARD_DELIMITER));
    }

    private static String joinCard(final CardPattern pattern, final CardNumber number) {
        return number.getPrintValue() + pattern.getName();
    }

    public static void printPlayerCards(final ParticipantCards playerCards) {
        System.out.printf(PROVIDED_CARD_TO_PLAYER_CARD_MESSAGE,
                playerCards.getName(), joinPlayerCards(playerCards.getCards()));
    }

    public static void printDealerHit() {
        System.out.println(PROVIDE_CARD_TO_DEALER_MESSAGE);
    }

    public static void printPlayerScoreResults(final List<ParticipantScoreResult> participantScoreResults) {
        participantScoreResults.forEach(OutputView::printPlayerScoreResult);
    }

    private static void printPlayerScoreResult(final ParticipantScoreResult participantScoreResult) {
        System.out.printf(PLAYER_CARD_RESULT_AND_SCORE_MESSAGE, participantScoreResult.getName(),
                joinPlayerCards(participantScoreResult.getCards()), participantScoreResult.getScore());
    }

    public static void printAllOutcomeResult(final OutComeResult outComeResult) {
        System.out.println(OUTCOME_TITLE);
        System.out.printf(DEALER_OUTCOME_RESULT_MESSAGE, printDealerResult(outComeResult.getDealerResult()));
        printPlayerResults(outComeResult.getPlayerResults());
    }

    private static String printDealerResult(final Map<GameOutcome, Integer> dealerResult) {
        return dealerResult.keySet().stream()
                .map(key -> dealerResult.get(key) + key.getPrintValue())
                .collect(Collectors.joining(" "));
    }

    private static void printPlayerResults(final Map<String, GameOutcome> playerResult) {
        playerResult.forEach(OutputView::printPlayerResult);
    }

    private static void printPlayerResult(final String playerName, final GameOutcome gameOutcome) {
        System.out.printf(PLAYER_OUTCOME_RESULT_MESSAGE, playerName, gameOutcome.getPrintValue());
    }
}
