import java.util.HashMap;
import java.util.Map;

public enum CardColor {
    HEARTS(0), DIAMONDS(1), CLUBS(2), SPADES(3);
    private int Color;
    private static final Map<Integer, CardColor> lookup = new HashMap<Integer, CardColor>();
    static {
        for (CardColor c : CardColor.values()) {
            lookup.put(c.getColorValue(), c);
        }
    }

    CardColor(int Color) {this.Color = Color;}
    public int getColorValue() {return this.Color;}
    public static CardColor getCardColorFromValue(int value) {return lookup.get(value);}
}
