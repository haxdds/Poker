/**
 * Created by Lagrange on 4/8/2017.
 */
public interface DeckIO {
    void shuffle();

    void addCard(Card card);

    Card drawCard();
}
