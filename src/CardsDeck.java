import java.util.ArrayList;
import java.util.Collections;

public class CardsDeck {
    private ArrayList<Card> Cards;
    public CardsDeck(){
        Cards = new ArrayList<Card>(Constants.RANKS * Constants.COLORS);
        for (CardColor Color : CardColor.values()){
            for(CardRank Rank : CardRank.values()){
                Cards.add(new Card(Color,Rank));
            }
        }
    }
    public void Shuffle(){
        Collections.shuffle(Cards);
    }

    public Card getTopCard(){
        return Cards.remove(0);
    }
}
