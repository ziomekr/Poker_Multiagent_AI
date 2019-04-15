import java.util.ArrayList;

public class PokerDealer {
    CardsDeck Deck;
    public PokerDealer(){
        Deck = new CardsDeck();
    }

    public void DealHands(CircularList<Player> Players, int DealerPosition){
        for(int card = 0; card < Constants.CARDS_PER_PLAYER_HAND; card++){
            for(int player=DealerPosition + 1; player<Players.size()+ DealerPosition + 1; player++){
                Player p = Players.get(player);
                if(p.isActive)
                    p.AddCardToHand(Deck.getTopCard());
            }
        }
    }
    public void DealFlop(ArrayList<Card> TableCards){
        Deck.getTopCard();
        for(int i=0; i<Constants.CARDS_PER_FLOP; i++){
            TableCards.add(Deck.getTopCard());
        }
    }
    public void DealTurn(ArrayList<Card> TableCards){
        Deck.getTopCard();
        TableCards.add(Deck.getTopCard());
    }
    public void DealRiver(ArrayList<Card> TableCards){
        Deck.getTopCard();
        TableCards.add(Deck.getTopCard());
    }
}
