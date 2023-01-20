package blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Card;
import model.Player;

public class Blackjack {

    public static int calculateBetValue(List<Card> bet) {
        int aces = 0;
        int value = 0;
        for (Card card : bet) {
            if (card.isAce()) aces++;
            value += card.value();
        }
        while (value > 21 && aces-- > 0) value -= 10;
        return value;
    } 

    public static boolean isBlackjack(List<Card> bet) {
        return calculateBetValue(bet) == 21 && bet.size() == 2;
    }

    public static void croupierTakeFromDeck(Player croupier, List<Card> deck) {
        
        while (calculateBetValue(croupier.bet()) < 17) croupier.addCard(deck.remove(0));
    }

    public static List<Player> getWinners(Player p1, Player p2, Player p3, Player crpr, List<Card> deck) {
        
        List<Player> players = new ArrayList<>(Arrays.asList(p1,p2,p3));
        List<Player> winners = new ArrayList<>();
        croupierTakeFromDeck(crpr, deck);
        int croupierValue = calculateBetValue(crpr.bet());
        
        if (isBlackjack(crpr.bet())) return winners;
        
        
        for (Player player : players) {
            if (isBlackjack(player.bet())){
                    winners.add(player);
                    continue;
                }
            if (calculateBetValue(player.bet()) > croupierValue && calculateBetValue(player.bet()) <= 21) winners.add(player);
        }
        
        return winners;
    
    }

}
