public class BettingManager {
    //TODO: Pot should store players in pot and pot size, move logic here, PotBuilder should manage different
    // pots only, PotBuilder should accept bet list and construct side pots from it
    CircularList<Player> bettingPlayers = new CircularList<>();
    PotBuilder pot;
    int callValue;
    int minRaiseValue;
    public BettingManager(PotBuilder pot){
        this.pot = pot;
    }
    public void addBettingPlayer(Player p){
        bettingPlayers.add(p);
    }




}
