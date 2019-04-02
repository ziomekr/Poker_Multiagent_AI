import java.util.ArrayList;

public class HandValue {


    public PokerHands PokerHand;
    public Card PokerHandCard;
    public ArrayList<Card> Kickers;

    public HandValue(ArrayList<Card> Hand){
        HandValueEstimator estimator = new HandValueEstimator(Hand);
        PokerHand = estimator.PokerHand;
        PokerHandCard = estimator.PokerHandHighestCard;
        Kickers = estimator.Kickers;
    }


}
