public class Card implements Comparable {

    public CardColor Color;
    public CardRank Rank;
    public Card(CardColor Color, CardRank Rank){
        this.Color = Color;
        this.Rank = Rank;
    }
    public Card(){
        this.Color = null;
        this.Rank = null;
    }

    @Override
    public int compareTo(Object o) {
        Card toCompare = (Card)o;
        return toCompare.Rank.getRankValue() - this.Rank.getRankValue();
    }
    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Card toCompare = (Card)o;
        return this.Rank == toCompare.Rank;
    }
}
