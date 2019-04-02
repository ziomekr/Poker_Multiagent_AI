import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        CardsDeck d = new CardsDeck();
        d.Shuffle();
        ArrayList<Card> str = new ArrayList<>();
        str.add(new Card(CardColor.CLUBS, CardRank.ACE));
        str.add(new Card(CardColor.CLUBS, CardRank.TWO));
        str.add(new Card(CardColor.CLUBS, CardRank.TWO));
        str.add(new Card(CardColor.DIAMONDS, CardRank.TWO));
        str.add(new Card(CardColor.DIAMONDS, CardRank.THREE));
        str.add(new Card(CardColor.SPADES, CardRank.FOUR));
        str.add(new Card(CardColor.SPADES, CardRank.FIVE));
        HandValue v = new HandValue(str);

        ArrayList<Card> full = new ArrayList<>();
        full.add(new Card(CardColor.CLUBS, CardRank.ACE));
        full.add(new Card(CardColor.CLUBS, CardRank.TWO));
        full.add(new Card(CardColor.CLUBS, CardRank.TWO));
        full.add(new Card(CardColor.DIAMONDS, CardRank.TWO));
        full.add(new Card(CardColor.DIAMONDS, CardRank.ACE));
        full.add(new Card(CardColor.SPADES, CardRank.FOUR));
        full.add(new Card(CardColor.SPADES, CardRank.FIVE));
        HandValue v1 = new HandValue(full);

        ArrayList<Card> flush = new ArrayList<>();
        flush.add(new Card(CardColor.CLUBS, CardRank.ACE));
        flush.add(new Card(CardColor.CLUBS, CardRank.TWO));
        flush.add(new Card(CardColor.CLUBS, CardRank.THREE));
        flush.add(new Card(CardColor.DIAMONDS, CardRank.TWO));
        flush.add(new Card(CardColor.DIAMONDS, CardRank.THREE));
        flush.add(new Card(CardColor.CLUBS, CardRank.FOUR));
        flush.add(new Card(CardColor.CLUBS, CardRank.SIX));
        HandValue v2 = new HandValue(flush);
        System.out.println(v.PokerHand + " " + v.PokerHandCard.Rank);
        System.out.println(v1.PokerHand + " " + v1.PokerHandCard.Rank);
        System.out.println(v2.PokerHand + " " + v2.PokerHandCard.Rank + " " + v2.Kickers.get(0).Rank + " " + v2.Kickers.get(1).Rank+" " + v2.Kickers.get(2).Rank+ " " + v2.Kickers.get(3).Rank);


    }
}
