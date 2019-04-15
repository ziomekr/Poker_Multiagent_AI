import java.util.ArrayList;

public class Pot {
    ArrayList<Bet> bets = new ArrayList<>();
    int potSize = 0;
    public void addBetToPot(Bet bet){
        potSize += bet.betSize;
        for(Bet b : bets){
            if(b.bettingPlayer == bet.bettingPlayer){
                b.betSize += bet.betSize;
                return;
            }
        }
        bets.add(bet);
    }
}
