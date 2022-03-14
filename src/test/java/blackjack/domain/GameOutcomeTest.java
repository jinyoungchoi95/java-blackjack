package blackjack.domain;

import static blackjack.domain.GameOutcome.DRAW;
import static blackjack.domain.GameOutcome.LOSE;
import static blackjack.domain.GameOutcome.WIN;
import static blackjack.domain.card.CardNumber.A;
import static blackjack.domain.card.CardNumber.FIVE;
import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.NINE;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardNumber.SEVEN;
import static blackjack.domain.card.CardNumber.SIX;
import static blackjack.domain.card.CardPattern.HEART;
import static blackjack.domain.card.CardPattern.SPADE;
import static blackjack.testutil.CardFixtureGenerator.createCards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameOutcomeTest {

    @Test
    @DisplayName("둘 다 블랙잭인 경우 무승부를 반환한다.")
    void calculateOutcomeBothBlackJack() {
        final Cards playerCards = createCards(Card.of(SPADE, KING), Card.of(SPADE, A));
        final Player player = Player.createNewPlayer("player", playerCards);
        player.changeFinishStatus();

        final Cards dealerCards = createCards(Card.of(HEART, KING), Card.of(HEART, A));
        final Dealer dealer = Dealer.createNewDealer(dealerCards);

        assertThat(player.fight(dealer)).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("자신만 블랙잭인 경우 우승를 반환한다.")
    void calculateOutcomeSelfBlackJack() {
        final Cards playerCards = createCards(Card.of(SPADE, KING), Card.of(SPADE, A));
        final Player player = Player.createNewPlayer("player", playerCards);
        player.changeFinishStatus();

        final Cards dealerCards = createCards(Card.of(HEART, KING), Card.of(HEART, JACK));
        final Dealer dealer = Dealer.createNewDealer(dealerCards);

        assertThat(player.fight(dealer)).isEqualTo(WIN);
    }

    @Test
    @DisplayName("상대방만 블랙잭인 경우 패배를 반환한다.")
    void calculateOutcomeNotSelfBlackJack() {
        final Cards playerCards = createCards(Card.of(HEART, KING), Card.of(HEART, JACK));
        final Player player = Player.createNewPlayer("player", playerCards);
        player.changeFinishStatus();

        final Cards dealerCards = createCards(Card.of(SPADE, KING), Card.of(SPADE, A));
        final Dealer dealer = Dealer.createNewDealer(dealerCards);

        assertThat(player.fight(dealer)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("모두 블랙잭이 아닌 경우 숫자로 비교한다.")
    void calculateOutcomeBothNotBlackJack() {
        final Cards playerCards = createCards(Card.of(HEART, KING), Card.of(HEART, JACK));
        final Player player = Player.createNewPlayer("player", playerCards);
        player.hit(Card.of(HEART, A));
        player.changeFinishStatus();

        final Cards dealerCards = createCards(Card.of(SPADE, SEVEN), Card.of(SPADE, NINE));
        final Dealer dealer = Dealer.createNewDealer(dealerCards);
        dealer.hit(Card.of(SPADE, FIVE));

        assertThat(player.fight(dealer)).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("둘 다 버스트일 경우, 패배를 반환한다.")
    void calculateOutcomeBothBust() {
        final Cards playerCards = createCards(Card.of(SPADE, KING), Card.of(SPADE, QUEEN));
        final Player player = Player.createNewPlayer("player", playerCards);
        player.hit(Card.of(SPADE, JACK));
        player.changeFinishStatus();

        final Cards dealerCards = createCards(Card.of(HEART, KING), Card.of(HEART, SIX));
        final Dealer dealer = Dealer.createNewDealer(dealerCards);
        dealer.hit(Card.of(HEART, JACK));

        assertThat(player.fight(dealer)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("본인만 버스트일 경우, 패배를 반환한다.")
    void calculateOutcomeSelfBust() {
        final Cards playerCards = createCards(Card.of(SPADE, KING), Card.of(SPADE, QUEEN));
        final Player player = Player.createNewPlayer("player", playerCards);
        player.hit(Card.of(SPADE, JACK));
        player.changeFinishStatus();

        final Cards dealerCards = createCards(Card.of(HEART, KING), Card.of(HEART, QUEEN));
        final Dealer dealer = Dealer.createNewDealer(dealerCards);

        assertThat(player.fight(dealer)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("상대만 버스트일 경우, 우승을 반환한다.")
    void calculateOutcomeNotSelfBust() {
        final Cards playerCards = createCards(Card.of(HEART, KING), Card.of(HEART, QUEEN));
        final Player player = Player.createNewPlayer("player", playerCards);
        player.changeFinishStatus();

        final Cards dealerCards = createCards(Card.of(HEART, KING), Card.of(HEART, SIX));
        final Dealer dealer = Dealer.createNewDealer(dealerCards);
        dealer.hit(Card.of(HEART, JACK));

        assertThat(player.fight(dealer)).isEqualTo(WIN);
    }
}
