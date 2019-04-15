import java.util.ArrayList;

public class PotBuilder {
    Pot mainPot;
    ArrayList<Pot> sidePots = new ArrayList<>();
    int totalPotSize = 0;
    int lastRaise = 0;
    int currentBetSize = 0;
    private boolean allInWasMade = false;
    public PotBuilder(){this.mainPot = new Pot();}
    public int getCurrentPlayerParticipationInPot(Player p){
        for(Bet b : mainPot.bets){
            if(b.bettingPlayer == p)
                return b.betSize;
        }
        return 0;
    }
    public int processBet(Bet b){
        if(b.betSize==Constants.BET_FOLD){
            playerFold(b);
        }
        else if(b.isAllIn){
            processAllInBet(b);
            b.bettingPlayer.Stack -= b.betSize;
        }
        else if(isBetValid(b)){
            processRegularBet(b);
            b.bettingPlayer.Stack -= b.betSize;
        }
        else{
            return Constants.ILLEGAL_BET;
        }
        return Constants.SUCCESS;
    }
    private void playerFold(Bet b){
        for(Bet bet : mainPot.bets){
            if(b.bettingPlayer == bet.bettingPlayer){
                mainPot.bets.remove(bet);
                return;
            }
        }
    }

    private void processAllInBet(Bet b){
        totalPotSize += b.betSize;
        if(b.betSize <= currentBetSize){
            mainPot.addBetToPot(b);
        }
        else if(b.betSize < currentBetSize+lastRaise){
            currentBetSize = b.betSize;
            mainPot.addBetToPot(b);
        }
        else{
            processRegularBet(b);
        }
        allInWasMade = true;
    }
    private void processRegularBet(Bet b){
        lastRaise += b.betSize - currentBetSize;
        currentBetSize = b.betSize;
        totalPotSize += b.betSize;
        mainPot.addBetToPot(b);
    }
    private boolean isBetValid(Bet b){
        if(b.betSize == currentBetSize)
            return true;
        if(b.betSize >= currentBetSize+lastRaise)
            return true;
        return false;
    }

    public void finishBetting(){
        if(allInWasMade){
            createSidePot();
        }
        lastRaise = 0;
        currentBetSize = 0;
        allInWasMade = false;
    }

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
