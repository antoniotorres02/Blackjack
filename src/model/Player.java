package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    
    final List<Card> bet;

    public Player(List<Card> bet) {
        this.bet= new ArrayList<>(bet);
    }

    public List<Card> bet() {
        return bet;
    }

    public void addCard(Card card) {
        bet.add(card);
    }
    
}
