import com.oracle.webservices.internal.api.EnvelopeStyle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {
    Deck deck;
    Player playerOne;
    Player dealer;
    Game game;
    Card highCard;
    Card lowCard;

    @Before
    public void before() {
        deck = new Deck();
        game = new Game(deck);
        game.prepareDeck();
        playerOne = new Player("Iain");
        dealer = new Player("Dealer");
        game.addPlayer(playerOne);
        game.addPlayer(dealer);
        highCard = new Card(CardSuit.DIAMONDS, CardRank.KING);
        lowCard = new Card(CardSuit.SPADES, CardRank.ACE);
    }

    @Test
    public void canAddPlayer() {
        assertEquals(2, game.playerCount());
    }

    @Test
    public void canAddFullDeckToGame() {
        assertEquals(52, deck.getNumOfCards());
    }

    @Test
    public void startGameDealsTwoCardsEach() {
        game.startGame();
        assertEquals(2, playerOne.cardCount());
        assertEquals(2, dealer.cardCount());
    }

    @Test
    public void canCheckDraw() {
        playerOne.takeCard(highCard);
        playerOne.takeCard(lowCard);
        dealer.takeCard(highCard);
        dealer.takeCard(lowCard);
        assertTrue(game.checkDraw());
    }

    @Test
    public void canFindWinner() {
        playerOne.takeCard(highCard);
        dealer.takeCard(lowCard);
        assertEquals(playerOne, game.findWinner());
    }

    @Test
    public void canCheckIfPlayerIsBust() {
        playerOne.takeCard(highCard);
        playerOne.takeCard(highCard);
        playerOne.takeCard(highCard);
        assertTrue(game.playerIsBust());
    }

    @Test
    public void aceAndTenWithTwoCardsIsBlackjack() {
        playerOne.takeCard(highCard);
        playerOne.takeCard(lowCard);
        assertTrue(game.hasBlackjack());
    }

}
