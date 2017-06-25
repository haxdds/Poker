import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Lagrange on 4/21/2017.
 */
public class Engine {
    int[] maxHand = new int[5];

    boolean hasRoyalFlush(Card[] hand, List<Card> table){
        if(hasStraight(hand, table) && hasFlush(hand, table) && getStraightMax() == 14) return true;
        return false;
    }
    boolean hasStraightFlush(Card[] hand, List<Card> table){
        if(hasStraight(hand,table) && hasFlush(hand,table)) return true;
        return false;
    }

    boolean hasStraight(Card[] hand, List<Card> table){
        Arrays.fill(maxHand, 0);
        List<Integer> values = new ArrayList<>();
        for(Card c: table)
            values.add(c.getValue());
        values.add(hand[0].getValue());
        values.add(hand[1].getValue());
        Collections.sort(values);
        Set<Integer> hs = new HashSet<Integer>(values);
        int[] v = new int[hs.size()];
        int index = 0;
        for(Integer i: hs){
            v[index++] = i;
        }
        int maxCon = 0;

        for(int k = 0; k < v.length - 4; k++){
            if(v[0] == 2 && v[1] == 3 && v[2] == 4 && v[3] == 5 && v[v.length - 1] == 14) {
                if(5 > maxCon) {
                    maxCon = 5;
                    maxHand[0] = v[0];
                    maxHand[1] = v[1];
                    maxHand[2] = v[2];
                    maxHand[3] = v[3];
                    maxHand[4] = 5;
                }
            }
            if(v[k] == v[k+1] - 1 &&
                    v[k] == v[k+2] - 2 &&
                    v[k] == v[k+3] - 3 &&
                    v[k] == v[k+4] - 4 ) {
                if(v[k+4] > maxCon) {
                    maxCon = v[k + 4];
                    maxHand[0] = v[k];
                    maxHand[1] = v[k+1];
                    maxHand[2] = v[k+2];
                    maxHand[3] = v[k+3];
                    maxHand[4] = v[k+4];
                }
            }
        }
        if(maxCon != 0) return true;
        return false;
    }

    boolean hasFlush(Card[] hand, List<Card> table){
        Arrays.fill(maxHand, 0);
        List<Card> c = new ArrayList<>();
        c.add(hand[0]);
        c.add(hand[1]);
        c.addAll(table);
        int diamonds = 0;
        int clubs = 0;
        int hearts = 0;
        int spades = 0;
        for(Card ca: c){
            if(ca.getSuit().equals(SUIT.DIAMONDS)) diamonds++;
            if(ca.getSuit().equals(SUIT.CLUBS)) clubs++;
            if(ca.getSuit().equals(SUIT.HEARTS)) hearts++;
            if(ca.getSuit().equals(SUIT.SPADES)) spades++;
        }
        if(spades >= 5 || hearts >= 5 || clubs >= 5 || diamonds >= 5){
            return true;
        }

        return false;
    }
    boolean hasFourKind(Card[] hand, List<Card> table){
        Arrays.fill(maxHand, 0);
        List<Integer> val = new ArrayList<>();
        for(Card c: table)
            val.add(c.getValue());
        val.add(hand[0].getValue());
        val.add(hand[1].getValue());
        Collections.sort(val);

        for(int k = 0; k < val.size() - 3; k++){
            if(val.get(k) == val.get(k + 1) &&
                    val.get(k) == val.get(k + 2) &&
                    val.get(k) == val.get(k + 3)){
                maxHand[4] = val.get(k);
                return true;
            }
        }
        return false;
    }
    boolean hasThreeKind(Card[] hand, List<Card> table){
        Arrays.fill(maxHand, 0);
        List<Integer> val = new ArrayList<>();
        for(Card c: table)
            val.add(c.getValue());
        val.add(hand[0].getValue());
        val.add(hand[1].getValue());
        Collections.sort(val);

        int max = 0;
        for(int k = 0; k < val.size() - 2; k++){
            if(val.get(k) == val.get(k + 1) &&
                    val.get(k) == val.get(k + 2)
                    ){
                if(val.get(k) > max) {
                    max = val.get(k);
                    maxHand[4] = max;
                }

            }
        }
        if(max != 0) return true;
        return false;
    }

    boolean hasFullHouse(Card[] hand, List<Card> table){
        int[] v = new int[5];
        if(hasThreeKind(hand,table)){
            v[4] = maxHand[4];
            v[3] = maxHand[4];
            v[2] = maxHand[4];
        }else{
            return false;
        }
        if(hasPair(hand,table)){
            v[1] = maxHand[4];
            v[2] = maxHand[4];
        }else{
            return false;
        }
        maxHand = v;
        return true;
    }



    boolean hasTwoPair(Card[] hand, List<Card> table){
        if(!hasPair(hand, table)) return false;

        hasPair(hand,table);
        List<Integer> val = new ArrayList<>();
        for(Card c: table)
            val.add(c.getValue());
        val.add(hand[0].getValue());
        val.add(hand[1].getValue());
        Collections.sort(val);
        int max = 0;
        for(int k = 0; k < val.size(); k++){
            for(int j = k + 1; j < val.size(); j++){
                if(val.get(k) == val.get(j) && val.get(k) != maxHand[4]){
                    if(val.get(k) > max){
                        max = val.get(k);
                    }
                }
            }
        }
        if(max != 0) {
            maxHand[2] = max;
            maxHand[1] = max;
            return true;
        }

        return false;

    }


    boolean hasPair(Card[] hand, List<Card> table){
        maxHand = new int[5];
        Arrays.fill(maxHand, 0);
        List<Integer> val = new ArrayList<>();
        for(Card c: table)
            val.add(c.getValue());
        val.add(hand[0].getValue());
        val.add(hand[1].getValue());
        Collections.sort(val);
        int max = 0;
        Map<Integer,Integer> pairmap = new HashMap<>();
        for(Integer i: val){
            pairmap.put(i,1);
        }
        for(int k = 0; k < val.size(); k++){
            for(int j = k + 1; j < val.size() - 1; j++){
                if(val.get(k) == val.get(j)){
                    pairmap.put(val.get(k),pairmap.get(val.get(k)) + 1);
                }
            }
        }
        for(Integer key: pairmap.keySet()){
            if(pairmap.get(key) == 2){
                if(key > max)
                    max = key;
            }
        }


        if(max != 0) {
            maxHand[4] = max;
            maxHand[3] = max;
            return true;
        }

        return false;
    }

    int getHighCard(Card[] hand, List<Card> table){
        Arrays.fill(maxHand, 0);
        List<Integer> val = new ArrayList<>();
        for(Card c: table)
            val.add(c.getValue());
        val.add(hand[0].getValue());
        val.add(hand[1].getValue());
        Collections.sort(val);
        if(val.size() == 6) val.remove(0);
        if(val.size() == 7) {
            val.remove(0);
            val.remove(1);
        }
        int[] v = new int[val.size()];
        int index = 0;
        for(Integer i: val){
            v[index++] = i;
        }
        maxHand = v;

        return maxHand[maxHand.length - 1];
    }

    int getMaxPair(){
        return maxHand[4];
    }
    int getTwoPairMax(){
        return 5*maxHand[4] + 2*maxHand[2];
    }

    int getFullHouseMax(){
        return maxHand[4];
    }


    int getThreeKindMax(){
        return maxHand[4];
    }

    int getStraightMax(){
        return maxHand[4];
    }

    int getFlushMax(){
        return maxHand[4];
    }

    Player getWinner(Player p1, Player p2, List<Card> table){
        Card[] hand1 = p1.getHand();
        Card[] hand2 = p1.getHand();



    return p1;
    }

    int gethandValue(Card[] hand, List<Card> table){
        int val = 0;
        if(hasRoyalFlush(hand,table)){
            System.out.println("Royal Flush");
            val += 10000;
            return val;
        }
        if(hasStraightFlush(hand,table)){
            System.out.println("Straight Flush");
            val += 9000;
            return val;
        }
        if(hasFourKind(hand,table)){
            System.out.println("Four of a Kind");
            val += (8000 + getHighCard(hand,table));
            return val;
        }
        if(hasFullHouse(hand,table)){
            System.out.println("Full House");
            val += (7000 + getFullHouseMax());
            return val;
        }
        if(hasFlush(hand,table)) {
            System.out.println("Flush");
            val += (6000 + getFlushMax());
            return val;
        }
        if(hasStraight(hand,table)){
            System.out.println("Straight");
            val += (5000 + getStraightMax());
            return val;
        }
        if(hasThreeKind(hand,table)) {
            System.out.println("Three of a Kind");
            val += (4000 + getThreeKindMax());
            return val;
        }
        if(hasTwoPair(hand,table)) {
            System.out.println("Two Pair");
            val += (3000 + getTwoPairMax());
            val += getHighCard(hand,table);
            return val;
        }
        if(hasPair(hand,table)) {
            System.out.println("One Pair");
            val += (2000 + getMaxPair());
            val += getHighCard(hand,table);
            return val;
        }else{
            System.out.println("High Card");
        }
        val += getHighCard(hand,table);

        return val;
    }


}


