public class Bet {
    int betSize;
    Player bettingPlayer;
    BetType type;
    public Bet(Player bettingPlayer, int betSize){
        this.bettingPlayer = bettingPlayer;
        this.betSize = betSize;
    }
    public Bet(int betSize, BetType type){
        this.betSize = betSize;
        this.type = type;
    }

}
