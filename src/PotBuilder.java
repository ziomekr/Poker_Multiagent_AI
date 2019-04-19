import java.util.ArrayList;

public class PotBuilder {
    Pot mainPot;
    ArrayList<Pot> sidePots = new ArrayList<>();
    int totalPotSize = 0;
    int lastRaise = 0;
    int currentBetSize = 0;
    private boolean allInWasMade = false;
    public PotBuilder(){this.mainPot = new Pot();}

    private void createSidePot(){
        int minBet = getMinBet();
        Pot newMainPot = new Pot();
        boolean isNewSidePotNeeded = false;
        int lastBetDifference = -1;
        for(Bet b : mainPot.bets){
            if(b.betSize > minBet){
                int betDifference = b.betSize - minBet;
                if(lastBetDifference == -1){
                    lastBetDifference = betDifference;
                }
                else{
                    if(lastBetDifference != betDifference){
                        isNewSidePotNeeded = true;
                    }
                }
                mainPot.potSize -= betDifference;
                newMainPot.addBetToPot(new Bet(b.bettingPlayer, betDifference));
                b.betSize = minBet;
            }
        }
        sidePots.add(mainPot);
        mainPot = newMainPot;
        if(isNewSidePotNeeded){
            createSidePot();
        }
    }

    private int getMinBet(){
        int minBet = Constants.INFINITY;
        for(Bet b : mainPot.bets){
            if(b.betSize < minBet){
                minBet = b.betSize;
            }
        }
        return minBet;
    }


}
