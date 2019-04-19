import java.util.ArrayList;

public class BettingManager {
    //TODO: Pot should store players in pot and pot size, move logic here, PotBuilder should manage different
    // pots only, PotBuilder should accept bet list and construct side pots from it
    CircularList<Player> bettingPlayers = new CircularList<>();
    ArrayList<Bet> bets = new ArrayList<>();
    PotBuilder pot;
    int callValue;
    int minRaiseValue;
    int dealerPosition;
    int smallBlind;
    int bigBlind;
    public BettingManager(PotBuilder pot){
        this.pot = pot;
    }
    public void addBettingPlayer(Player p){
        bettingPlayers.add(p);
    }
    
    private void determineBetType(Bet b){

        if(b.betSize == callValue){
            b.type = BetType.CHECK;
        }
        else if(callValue == 0 && b.betSize >= minRaiseValue){
            b.type = BetType.BET;
        }
        else if(b.betSize >= callValue + minRaiseValue){
            b.type = BetType.RAISE;
        }
        else if(b.betSize == b.bettingPlayer.Stack){
            b.type = BetType.ALLIN;
        }
        else if(b.betSize == -1){
            b.type = BetType.FOLD;
        }
        else{
            b.type = BetType.ILLEGAL;
        }

    }
    private Bet getAccumulatedBet(Bet newBet){
        for(Bet oldBet : bets){
            if(oldBet.bettingPlayer == newBet.bettingPlayer){
                newBet.betSize += oldBet.betSize;
                break;
            }
        }
        return newBet;
    }
    public void processBet(Bet b){
        if(b.type == BetType.FOLD){
            playerFold(b);
        }
        else if(b.type == BetType.ALLIN){
            processAllInBet(b);
            b.bettingPlayer.Stack -= b.betSize;
        }
        else {
            processRegularBet(b);
            b.bettingPlayer.Stack -= b.betSize;
        }
    }
    private void playerFold(Bet b){
        bettingPlayers.remove(b.bettingPlayer);
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




}
