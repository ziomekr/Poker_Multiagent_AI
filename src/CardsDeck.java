import java.util.ArrayList;
import java.util.Collections;

public class CardsDeck {
    private ArrayList<Card> Cards = new ArrayList<Card>(Constants.RANKS * Constants.COLORS);;
    public CardsDeck(){
        PrepareDeck();
        Shuffle();
    }
    private void PrepareDeck(){
        Cards.clear();
        for (CardColor Color : CardColor.values()){
            for(CardRank Rank : CardRank.values()){
                Cards.add(new Card(Color,Rank));
            }
        }
    }
    private void Shuffle(){
        Collections.shuffle(Cards);
    }

    public Card getTopCard(){
        return Cards.remove(0);
    }

    public void ResetDeck(){
        PrepareDeck();
        Shuffle();
    }
}
