public class Bet {
    int betSize;
    Player bettingPlayer;
    boolean isAllIn = false;
    public Bet(Player bettingPlayer, int betSize){
        if(betSize == Constants.BET_FOLD){
            bettingPlayer.isActive = false;
            return;
        }
        this.betSize = betSize;
        this.bettingPlayer = bettingPlayer;
        if(bettingPlayer.Stack <= betSize){
            isAllIn = true;
            this.betSize = bettingPlayer.Stack;
            bettingPlayer.isActive = false;
        }
    }
}
