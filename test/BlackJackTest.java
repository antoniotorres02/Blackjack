import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import blackjack.Blackjack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Card;
import model.Player;

/**
 *
 * @author anton
 */
public class BlackJackTest {
    
    public BlackJackTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void givenFacesAndNormalCardsCheckIfTheyAreFaces() {
        assertEquals(Card.Ace.isFace(),false);
        assertEquals(Card._3.isFace(),false);
        assertEquals(Card.King.isFace(),true);
        assertEquals(Card.Queen.isFace(),true);
    }
    
    @Test
    public void givenCardsCheckIfTheyAreAces() {
        assertEquals(Card.Ace.isAce(),true);
        assertEquals(Card._3.isAce(),false);
        assertEquals(Card.King.isAce(),false);
        assertEquals(Card.Queen.isAce(),false);
    }
    
    @Test
    public void givenCardsCalculatesItsValue() {
        assertEquals(Card.Ace.value(),11);
        assertEquals(Card._3.value(),3);
        assertEquals(Card.King.value(),10);
        assertEquals(Card.Queen.value(),10);
        assertEquals(Card._8.value(),8);
        assertEquals(Card._2.value(),2);
    }
    
    @Test
    public void givenAPlayerReturnsItsBet() {
        Player player1 = new Player(Arrays.asList(Card.Ace,Card._7,Card._10));
        assertEquals(player1.bet(),Arrays.asList(Card.Ace,Card._7,Card._10));
    }
    
    @Test
    public void givenAPlayerAddsACard() {
        Player player1 = new Player(Arrays.asList(Card.Ace,Card._7,Card._10));
        player1.addCard(Card.Ace);
        assertEquals(player1.bet(),Arrays.asList(Card.Ace,Card._7,Card._10,Card.Ace));
    }
    
    @Test
    public void givenABetShouldCalcualteItsValue() {
        List<Card> bet1 = new ArrayList<>(Arrays.asList(Card._10,Card.Ace,Card.Ace,Card._7));
        List<Card> bet2 = new ArrayList<>(Arrays.asList(Card._7,Card.King,Card._9));
        List<Card> bet3 = new ArrayList<>(Arrays.asList(Card._10,Card.Ace));
        List<Card> bet4 = new ArrayList<>(Arrays.asList(Card._9,Card.Ace,Card.Ace,Card.Ace,Card.Ace));
        assertEquals(19, Blackjack.calculateBetValue(bet1));
        assertEquals(26, Blackjack.calculateBetValue(bet2));
        assertEquals(21, Blackjack.calculateBetValue(bet3));
        assertEquals(13, Blackjack.calculateBetValue(bet4));
    }
    
    @Test
    public void givenABetCheckIfItsBlackjack() {
        List<Card> bet1 = new ArrayList<>(Arrays.asList(Card._10,Card.Ace,Card.Ace,Card._7));
        List<Card> bet2 = new ArrayList<>(Arrays.asList(Card._7,Card.King,Card._9));
        List<Card> bet3 = new ArrayList<>(Arrays.asList(Card._10,Card.Ace));
        List<Card> bet4 = new ArrayList<>(Arrays.asList(Card._9,Card.Ace,Card.Ace,Card.Ace,Card.Ace));
        assertEquals(false, Blackjack.isBlackjack(bet1));
        assertEquals(false, Blackjack.isBlackjack(bet2));
        assertEquals(true, Blackjack.isBlackjack(bet3));
        assertEquals(false, Blackjack.isBlackjack(bet4));
    }
    
    @Test
    public void givenACroupierAndADeckTakesCardsUntil17() {
        Player croupier1 = new Player(Arrays.asList());
        Player croupier2 = new Player(Arrays.asList(Card._10));
        Player croupier3 = new Player(Arrays.asList(Card._7));
        List<Card> deck1 = new ArrayList<>(Arrays.asList(Card._10,Card._6,Card._7));
        List<Card> deck2 = new ArrayList<>(Arrays.asList(Card.Ace,Card.Ace));
        List<Card> deck3 = new ArrayList<>(Arrays.asList(Card._10,Card.Ace));
        Blackjack.croupierTakeFromDeck(croupier1,deck1);
        Blackjack.croupierTakeFromDeck(croupier2,deck2);
        Blackjack.croupierTakeFromDeck(croupier3,deck3);
        
        assertEquals(Arrays.asList(Card._10,Card._6,Card._7), croupier1.bet());
        assertEquals(Arrays.asList(), deck1);
        assertEquals(Arrays.asList(Card._10,Card.Ace), croupier2.bet());
        assertEquals(Arrays.asList(Card.Ace), deck2);
        assertEquals(Arrays.asList(Card._7,Card._10), croupier3.bet());
        assertEquals(Arrays.asList(Card.Ace), deck3);
    }
    
    @Test
    public void givenAGameWinsPlayer1() {
        Player p1 = new Player(Arrays.asList(Card.Jack, Card.Ace));
        Player p2 = new Player(Arrays.asList(Card._10, Card._5, Card._6));
        Player p3 = new Player(Arrays.asList(Card._3, Card._6, Card.Ace, Card._3, Card.Ace, Card.King));
        Player crpr = new Player(Arrays.asList(Card._9, Card._7));
        List<Card> deck = new ArrayList<>(Arrays.asList(Card._5, Card._4, Card.King, Card._2));
        
        assertEquals(Arrays.asList(p1), Blackjack.getWinners(p1,p2,p3,crpr,deck));
    } 
    
    @Test
    public void givenAGameWinsPlayer1Player3() {
        Player p1 = new Player(Arrays.asList(Card._10, Card.King));
        Player p2 = new Player(Arrays.asList(Card._10, Card._2, Card._6));
        Player p3 = new Player(Arrays.asList(Card._8, Card._8, Card._5));
        Player crpr = new Player(Arrays.asList(Card._5, Card._10));
        List<Card> deck = new ArrayList<>(Arrays.asList(Card.Ace, Card._3, Card.King, Card._2));
        
        assertEquals(Arrays.asList(p1,p3), Blackjack.getWinners(p1,p2,p3,crpr,deck));
    } 
}
