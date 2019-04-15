import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
//        CardsDeck d = new CardsDeck();
//        ArrayList<Card> str = new ArrayList<>();
//        str.add(new Card(CardColor.CLUBS, CardRank.ACE));
//        str.add(new Card(CardColor.CLUBS, CardRank.TWO));
//        str.add(new Card(CardColor.CLUBS, CardRank.TWO));
//        str.add(new Card(CardColor.DIAMONDS, CardRank.TWO));
//        str.add(new Card(CardColor.DIAMONDS, CardRank.THREE));
//        str.add(new Card(CardColor.SPADES, CardRank.FOUR));
//        str.add(new Card(CardColor.SPADES, CardRank.FIVE));
//        HandValue v = new HandValue(str);
//
//        ArrayList<Card> full = new ArrayList<>();
//        full.add(new Card(CardColor.CLUBS, CardRank.ACE));
//        full.add(new Card(CardColor.CLUBS, CardRank.TWO));
//        full.add(new Card(CardColor.CLUBS, CardRank.TWO));
//        full.add(new Card(CardColor.DIAMONDS, CardRank.FOUR));
//        full.add(new Card(CardColor.DIAMONDS, CardRank.ACE));
//        full.add(new Card(CardColor.SPADES, CardRank.FOUR));
//        full.add(new Card(CardColor.SPADES, CardRank.FOUR));
//        HandValue v1 = new HandValue(full);
//
//        ArrayList<Card> flush = new ArrayList<>();
//        flush.add(new Card(CardColor.CLUBS, CardRank.ACE));
//        flush.add(new Card(CardColor.CLUBS, CardRank.TWO));
//        flush.add(new Card(CardColor.CLUBS, CardRank.THREE));
//        flush.add(new Card(CardColor.DIAMONDS, CardRank.TWO));
//        flush.add(new Card(CardColor.DIAMONDS, CardRank.THREE));
//        flush.add(new Card(CardColor.CLUBS, CardRank.FOUR));
//        flush.add(new Card(CardColor.CLUBS, CardRank.SIX));
//        HandValue v2 = new HandValue(flush);
//        System.out.println(v.PokerHand + " " + v.HandHighestCard);
//        System.out.println(v1.PokerHand + " " + v1.HandHighestCard + " " + v1.Kickers.get(0).Rank);
//        System.out.println(v2.PokerHand + " " + v2.HandHighestCard + " " + v2.Kickers.get(0).Rank + " " + v2.Kickers.get(1).Rank+" " + v2.Kickers.get(2).Rank+ " " + v2.Kickers.get(3).Rank);
//        CircularList<Card> l = new CircularList<>();
//        l.add(new Card(null,CardRank.ACE));
//        l.add(new Card(null,CardRank.KING));
//        l.add(new Card(null,CardRank.QUEEN));
//        for(int i =2; i<l.size()+2;i++){
//            System.out.println(l.get(i).Rank);
//        }
        PokerTable t = new PokerTable();
        t.AddPlayer(new Player("JOHN", 2000, 0));
        t.AddPlayer(new Player("PAUL", 1000, 0));
        t.AddPlayer(new Player("SARTRE", 200, 0));
        t.PlayHand();

    }
}
