import java.util.ArrayList;
import java.util.Collections;

public class HandValueEstimator {

    public PokerHands PokerHand;
    public Card PokerHandHighestCard = new Card();
    public ArrayList<Card> Kickers = new ArrayList<>();

    private int[] HandRanks = new int[Constants.RANKS + 1];
    private int[] HandColors = new int[Constants.COLORS];
    private int maxCardMultiplicity = 0;
    private boolean isFlush = false;
    private boolean isStraight;
    ArrayList<Card> Hand;

    public HandValueEstimator(ArrayList<Card> Hand){
        if(Hand == null)
            return;

        this.Hand = Hand;
        //One iteration over hand, checking for card multiplicities and flush
        for(Card c : Hand){
            //Ace can be lowest and highest in straight, hence added as strongest and weakest card
            if(c.Rank == CardRank.ACE)
                HandRanks[0] += 1;
            HandRanks[c.Rank.getRankValue()] += 1;
            if(maxCardMultiplicity < HandRanks[c.Rank.getRankValue()])
                maxCardMultiplicity = HandRanks[c.Rank.getRankValue()];
            HandColors[c.Color.getColorValue()] += 1;
            if(HandColors[c.Color.getColorValue()] == Constants.CARDS_PER_HAND){
                isFlush = true;
                PokerHandHighestCard.Color = c.Color;
            }
        }
        isStraight = checkForStraight();
        Collections.sort(Hand);
        //Looking for strongest hand
        if (hasPoker())
            return;
        if (hasFourOfAKind())
            return;
        if(hasFullHouse())
            return;
        if(hasFlush())
            return;
        if(hasStraight())
            return;
        if(hasThreeOfAKind())
            return;
        if(hasTwoPairs())
            return;
        if(hasPair())
            return;
        hasHighCard();

    }
    private boolean checkForStraight(){
        int cardsForStraight = 0;
        for(int rank = Constants.RANKS; rank>-1; rank--){
            if(HandRanks[rank]>0){
                if(cardsForStraight == 0)
                    PokerHandHighestCard.Rank = CardRank.getCardRankFromValue(rank);
                cardsForStraight += 1;
                if(cardsForStraight == Constants.CARDS_PER_HAND)
                    return true;
            }
            else{
                cardsForStraight = 0;
                PokerHandHighestCard.Rank = null;
            }
        }
        return false;
    }

    private boolean hasPoker(){
        if(isStraight && isFlush){
            PokerHand = PokerHands.STRAIGHTFLUSH;
            return true;
        }
        return false;
    }

    private boolean hasFourOfAKind(){
        for(int rank=Constants.RANKS; rank > 0; rank--){
            if(HandRanks[rank] == 4){
                PokerHand = PokerHands.FOUROFAKIND;
                PokerHandHighestCard.Rank = CardRank.getCardRankFromValue(rank);
                for(Card c : Hand){
                    if(c.Rank.getRankValue() != rank){
                        Kickers.add(c);
                        break;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private boolean hasFullHouse(){
        for(int three_rank=Constants.RANKS; three_rank > 0; three_rank--){
            if(HandRanks[three_rank] == 3){
                for(int pair_rank=Constants.RANKS; pair_rank > 0; pair_rank--){
                    if(HandRanks[pair_rank] == 2){
                        PokerHand = PokerHands.FULLHOUSE;
                        PokerHandHighestCard.Rank = CardRank.getCardRankFromValue(three_rank);
                        Kickers.add(new Card(null,CardRank.getCardRankFromValue(pair_rank)));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean hasFlush(){
        if(isFlush){
            PokerHand = PokerHands.FLUSH;
            boolean highestCardUpdated = false;
            for(Card c : Hand){
                if (c.Color == PokerHandHighestCard.Color){
                    if(!highestCardUpdated){
                        PokerHandHighestCard.Rank = c.Rank;
                        highestCardUpdated = true;
                    }
                    else{
                        Kickers.add(c);
                        if(Kickers.size() == Constants.CARDS_PER_HAND - 1)
                            return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean hasStraight(){
        if(isStraight){
            PokerHand = PokerHands.STRAIGHT;
            return true;
        }
        return false;
    }

    private boolean hasThreeOfAKind(){
        for(int rank=Constants.RANKS; rank > 0; rank--){
            if(HandRanks[rank] == 3){
                PokerHand = PokerHands.THREEOFAKIND;
                PokerHandHighestCard.Rank = CardRank.getCardRankFromValue(rank);
                for(Card c : Hand){
                    if(c.Rank != CardRank.getCardRankFromValue(rank)){
                        Kickers.add(c);
                    }
                    if(Kickers.size() == Constants.CARDS_PER_HAND - 3)
                        return true;
                }
            }
        }
        return false;
    }

    private boolean hasTwoPairs(){
        for(int higher_pair_rank = Constants.RANKS; higher_pair_rank > 0; higher_pair_rank--){
            if(HandRanks[higher_pair_rank] == 2){
                for(int lower_pair_rank = higher_pair_rank - 1; lower_pair_rank > 0; lower_pair_rank--){
                    if(HandRanks[lower_pair_rank] == 2){
                        PokerHand = PokerHands.TWOPAIRS;
                        PokerHandHighestCard.Rank = CardRank.getCardRankFromValue(higher_pair_rank);
                        Kickers.add(new Card(null, CardRank.getCardRankFromValue(lower_pair_rank)));
                        for(Card c : Hand){
                            if(c.Rank != CardRank.getCardRankFromValue(higher_pair_rank) && c.Rank!=CardRank.getCardRankFromValue(lower_pair_rank)){
                                Kickers.add(c);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean hasPair(){
        for(int rank = Constants.RANKS; rank > 0; rank--){
            if(HandRanks[rank] == 2){
                PokerHand = PokerHands.PAIR;
                PokerHandHighestCard.Rank = CardRank.getCardRankFromValue(rank);
                for(Card c : Hand){
                    if(c.Rank != PokerHandHighestCard.Rank){
                        Kickers.add(c);
                        if (Kickers.size() == Constants.CARDS_PER_HAND - 2){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void hasHighCard(){
        PokerHand = PokerHands.HIGHCARD;
        PokerHandHighestCard = Hand.get(0);
        for(int card_index = 1; card_index<Constants.CARDS_PER_HAND; card_index++){
            Kickers.add(Hand.get(card_index));
        }
    }
}
