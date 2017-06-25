import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Lagrange on 4/7/2017.
 */
public class Card {

    private SUIT suit;
    private int value;
    private BufferedImage image;

    Card(SUIT suit, int value,BufferedImage img){
        this.suit = suit;
        this.value= value;
        this.image = img;
    }


    public SUIT getSuit() {
        return suit;
    }

    public void setSuit(SUIT suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }


}


