import java.util.ArrayList;

public class Player {
    String Name;
    int Stack;
    int BonusDecisionTime;
    ArrayList<Card> Hand = new ArrayList<>(Constants.CARDS_PER_PLAYER_HAND);
    Bet currentBet;
    boolean isActive = false;
    public Player(String Name, int Stack, int BonusDecisionTime){
        this.Name = Name;
        this.Stack = Stack;
        this.BonusDecisionTime = BonusDecisionTime;
    }
    public void AddCardToHand(Card c){ Hand.add(c);}
}
