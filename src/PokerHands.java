public enum PokerHands {
    HIGHCARD(0), PAIR(1), TWOPAIRS(2), THREEOFAKIND(3), STRAIGHT(4),
    FLUSH(5), FULLHOUSE(6), FOUROFAKIND(7), STRAIGHTFLUSH(8);
    private int Value;
    PokerHands(int Value) {this.Value = Value;}
    public int getPokerHandValue() {return this.Value;}
}
