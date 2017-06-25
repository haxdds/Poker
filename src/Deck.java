import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lagrange on 4/8/2017.
 */
public class Deck implements DeckIO{

    private List<Card> deck = new ArrayList<Card>();
    private Card back;
    CardImage images = new CardImage();

    Deck(){
        List<Integer> keys = new ArrayList<>();
        for(int i = 1; i <= 52; i++){
            keys.add(i);
        }
        Collections.shuffle(keys);

        for(int k = 0; k < keys.size(); k++){

            int i = keys.get(k);
            SUIT s = null;
            if(i >= 1 && i <= 13) s = SUIT.DIAMONDS;
            if(i >= 14 && i <= 26) s = SUIT.CLUBS;
            if(i >= 27 && i <= 39) s = SUIT.HEARTS;
            if(i >= 40 && i <= 52) s = SUIT.SPADES;

            int val = i % 13;
            if(val == 1) val = 14;
            if(val == 0) val = 13;
            Card c = new Card(s,val,images.cards.get(i));
            deck.add(c);
        }

        back = new Card(SUIT.DIAMONDS,0,images.getBack());
        //System.out.print("\n\n\ndeck created\n\n\n");
    }



    @Override
    public void shuffle() {
        Collections.shuffle(deck);
    }

    @Override
    public void addCard(Card card) {
        deck.add(card);
    }

    @Override
    public Card drawCard() {

        Card c = this.deck.get(0);
        deck.remove(0);
        //.out.println("\ncard drawn");
       return c;
    }

    public List<Card> getDeck(){
        return deck;
    }

    public Card getBack(){
        return back;
    }



}

