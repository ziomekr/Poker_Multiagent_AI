import java.util.ArrayList;

public class HandValue {


    public PokerHands PokerHand;
    public CardRank HandHighestCard;
    public ArrayList<Card> Kickers;

    public HandValue(ArrayList<Card> Hand){
        HandValueEstimator estimator = new HandValueEstimator(Hand);
        PokerHand = estimator.PokerHand;
        HandHighestCard = estimator.HandHighestCard;
        Kickers = estimator.Kickers;
    }


}
