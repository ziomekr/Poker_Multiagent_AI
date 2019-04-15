public enum CardColor {
    HEARTS(0), DIAMONDS(1), CLUBS(2), SPADES(3);
    private int Color;
    CardColor(int Color) {this.Color = Color;}
    public int getColorValue() {return this.Color;}
}
