


import java.awt.*;

import java.awt.image.BufferedImage;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Created by Lagrange on 4/7/2017.
 */
public class Poker{
    static int WIDTH = 1200;
    static int HEIGHT = WIDTH * 10 / 16;
    static Map<Integer,BufferedImage> cardImages = new HashMap<>();




    public static void main(String[] args){

        new Game(10000);

//        Engine e = new Engine();
//        Deck d = new Deck();
//        Card[] h = new Card[2];
//        Card[] h2 = new Card[2];
//        List<Card> t = new ArrayList<>();
//
//        h[0] = d.drawCard();
//        h[1] = d.drawCard();
//        h2[0] = d.drawCard();
//        h2[1] = d.drawCard();
//
//        t.add(d.drawCard());
//        t.add(d.drawCard());
//        t.add(d.drawCard());
//        t.add(d.drawCard());
//        t.add(d.drawCard());
//        System.out.print("\nHAND1: \n");
//        System.out.print(h[0].getValue() + "\t" + h[0].getSuit() + "\n" + h[1].getValue() + "\t" + h[1].getSuit());
//
//        System.out.print("\n\nHAND2: \n");
//        System.out.print(h2[0].getValue() + "\t" + h2[0].getSuit() + "\n" + h2[1].getValue() + "\t" + h2[1].getSuit() + "\n\n table \n");
//
//        for(Card c : t){
//            System.out.println(c.getValue() + "\t" + c.getSuit());
//        }
//        int val1 = e.gethandValue(h,t);
//        int val2 = e.gethandValue(h2,t);
//        System.out.println("\n value1: " + val1);
//        System.out.println("\n value2: " + val2);
//
//        if(val1 > val2) System.out.println("\n Player 1 wins");
//        else System.out.println("\nPlayer 2 wins" );





    }




}
