import java.util.ArrayList;
import java.util.Scanner;

public class PokerTable {
    CircularList<Player> Players = new CircularList<>();
    PokerDealer Dealer = new PokerDealer();
    PotBuilder Pot = new PotBuilder();
    ArrayList<Card> CommunityCards = new ArrayList<>(Constants.TABLE_CARDS);
    int dealerPosition = 0;
    int StartingStack= 2000;
    int LastRaise = 0;
    int HandsDealt = 0;
    int blindsLevel = 0;
    private boolean[] OccupiedSeats = new boolean[Constants.MAX_PLAYERS_PER_TABLE];
    int[] bigBlinds = {10, 20, 30, 40, 50, 60, 80, 100, 120, 140, 160, 200, 250, 300, 400, 600, 800, 1000 };
    public void AddPlayer(Player p){
        if(Players.size()<Constants.MAX_PLAYERS_PER_TABLE){
            int emptySeat = getEmptySeat();
            Players.add(emptySeat, p);
            OccupiedSeats[emptySeat] = true;
        }
    }
    //TODO: player may leave the table with sitting order preserved
    private int getEmptySeat(){
        for (int emptySeatIndex =0; emptySeatIndex<Constants.MAX_PLAYERS_PER_TABLE; emptySeatIndex++){
            if(!OccupiedSeats[emptySeatIndex])
                return emptySeatIndex;
        }
        return -1;
    }
    public void PlayHand(){
        activatePlayers();
        Dealer.DealHands(Players, dealerPosition);
        getBlindsFromPlayers();
        startPreFlopBettingPhase();
        endBettingPhase();
        Dealer.DealFlop(CommunityCards);
        startBettingPhase();
        endBettingPhase();
        Dealer.DealTurn(CommunityCards);
        startBettingPhase();
        endBettingPhase();
        Dealer.DealRiver(CommunityCards);
        startBettingPhase();
        endBettingPhase();
        //testing
        System.out.println("MAIN: " + Pot.mainPot.potSize);
        for(Pot side : Pot.sidePots){
            System.out.println(side.potSize);
        }
    }

    private void activatePlayers(){
        for (Player p : Players){
            p.isActive = true;
        }
    }

    private void startPreFlopBettingPhase(){
        for(int i=0; i<Players.size(); i++){
            Player active = Players.get(i + dealerPosition + 3);
            //TODO: AGENT LOGIC HERE
            if(active.isActive)
                getBEtFromConsole(active);
        }
    }
    private void startBettingPhase(){
        int inactiveCount = 0;
        for(int i=0; i<Players.size(); i++){
            Player active = Players.get(i + dealerPosition + 1);
            //TODO: AGENT LOGIC HERE
            if(active.isActive)
                getBEtFromConsole(active);
            else
                inactiveCount += 1;
        }
        if(inactiveCount<Players.size()){
            startBettingPhase();
        }
    }
    private void endBettingPhase(){ Pot.finishBetting();}

    private void getBEtFromConsole(Player active){
        //TESTING FUNCTION DELETE IN FUTURE
        System.out.println(active.Name + " Stack:" + active.Stack);
        Scanner reader = new Scanner(System.in);
        int bet = reader.nextInt();
        while(Pot.processBet(new Bet(active, bet)) != Constants.SUCCESS){
            bet = reader.nextInt();
        }
    }
    private void getBlindsFromPlayers(){
        getSmallBlind();
        getBigBlind();
    }
    //TODO: add heads up mode to blinds
    private void getSmallBlind(){
        Player p = Players.get(dealerPosition+1);
        Pot.processBet(new Bet(p, bigBlinds[blindsLevel]/2));
    }

    private void getBigBlind(){
        Player p = Players.get(dealerPosition+2);
        Pot.processBet(new Bet(p, bigBlinds[blindsLevel]));
    }
}
