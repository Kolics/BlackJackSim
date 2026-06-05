import controllers.GameController;
import models.Card;
import models.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BlackJackTest {

    private Hand testHand;
    private GameController testGameController;

    @BeforeEach
    void setUp() {
        testHand = new Hand();
        testGameController = new GameController();
    }

    @Test
    void totalWithoutAces() {
        testHand.deal(new Card(Card.Color.HEARTS, Card.Number.FIVE));
        testHand.deal(new Card(Card.Color.DIAMONDS, Card.Number.EIGHT));
        assertEquals(13, testHand.getTotal());
    }

    @Test
    void totalWithOneAce() {
        testHand.deal(new Card(Card.Color.HEARTS, Card.Number.ACE));
        testHand.deal(new Card(Card.Color.DIAMONDS, Card.Number.SIX));
        assertEquals(7, testHand.getTotal());
    }

    @Test
    void totalWithTwoAces() {
        testHand.deal(new Card(Card.Color.HEARTS, Card.Number.ACE));
        testHand.deal(new Card(Card.Color.DIAMONDS, Card.Number.ACE));
        assertEquals(2, testHand.getTotal());
    }

    @Test
    void isCorrectAnswerTrulyCorrect() {
        testHand.deal(new Card(Card.Color.HEARTS, Card.Number.ACE));
        testHand.deal(new Card(Card.Color.DIAMONDS, Card.Number.SIX));
        testHand.getTotal();
        assertTrue(testHand.isCorrectAnswer(7));
        assertTrue(testHand.isCorrectAnswer(17));
        assertFalse(testHand.isCorrectAnswer(16));
    }

    @Test
    void isLessThen17Correct() {
        testHand.deal(new Card(Card.Color.HEARTS, Card.Number.EIGHT));
        testHand.deal(new Card(Card.Color.HEARTS, Card.Number.EIGHT));
        testGameController = new GameController(testHand);
        assertTrue(testGameController.isLessThan17());
    }
}