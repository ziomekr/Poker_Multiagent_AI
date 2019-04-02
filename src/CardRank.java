import java.util.HashMap;
import java.util.Map;

public enum CardRank {
    TWO(1), THREE(2), FOUR(3), FIVE(4), SIX(5), SEVEN(6), EIGHT(7), NINE(8), TEN(9),
    JACK(10), QUEEN(11), KING(12), ACE(13);
    private int Value;
    private static final Map<Integer, CardRank> lookup = new HashMap<Integer, CardRank>();
    static {
        for (CardRank r : CardRank.values()) {
            lookup.put(r.getRankValue(), r);
        }
    }

    CardRank(int Value) {this.Value = Value;}
    public int getRankValue() {return this.Value;}
    public static CardRank getCardRankFromValue(int value) {return lookup.get(value);}
}
