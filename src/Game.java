import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lagrange on 4/19/2017.
 */
public class Game {

    List<Player> players = new ArrayList<>();
    List<Card> table = new ArrayList<>();
    Map<Player, Integer> bets = new HashMap<>();
    Deck deck = new Deck();
    Window.ImageCanvas canvas;
    int pot = 0;
    boolean gameOn = false;
    Engine e = new Engine();

    Game(int initialCash){
        canvas = new Window.ImageCanvas(deck.drawCard().getImage(),deck.drawCard().getImage());
        new Window(Poker.WIDTH,Poker.HEIGHT,"Poker",new Color(60,160,60),canvas, this);

        Player p1 = new Player();
        Player p2 = new Player();

        players.add(p1);
        players.add(p2);

        setInitialCash(initialCash);

        drawState();
        drawCash();
        drawBets();
        setPot();

        deal();

        gameOn = true;

        players.get(0).reset();
        players.get(1).reset();

        setTurn(players.get(0));
        bigBlind(players.get(1));

        int turn = 0;
        while(gameOn){


            playerTurn();
            compDecision();

            System.out.print("\n\n");


            resetStates();
            delay();
            flop();

            System.out.print("\n\n");

            playerTurn();

            compDecision();
            delay();


            resetStates();
            turn();

            System.out.print("\n\n");

            playerTurn();

            compDecision();
            delay();


            resetStates();

            river();

            System.out.print("\n\n");

            playerTurn();

            compDecision();

            getWinner();
            delay();

            System.out.print("\n\n");

            reveal();
            System.out.print("Player Hand:\n");
            System.out.println(players.get(0).getHand()[0].getValue() +"\tSuit: " +  players.get(0).getHand()[0].getSuit());
            System.out.println(players.get(0).getHand()[1].getValue() +"\tSuit: " +  players.get(0).getHand()[1].getSuit());
            System.out.print("\n\nComputer Hand:\n");
            System.out.println(players.get(1).getHand()[0].getValue() +"\tSuit: " +  players.get(1).getHand()[0].getSuit());
            System.out.println(players.get(1).getHand()[1].getValue() +"\tSuit: " +  players.get(1).getHand()[1].getSuit());

            System.out.print("\n\nTable:\n");
            for(Card c: table){
                System.out.println(c.getValue() + "\tSuit: " + c.getSuit());
            }

            resetStates();

            while(!players.get(0).isCheck()){
                delay();
            }

            resetStates();
            clearTable();
            deal();



        }


    }
    void playerTurn(){

        while(waitFor(players.get(0))){
            delay();
        }

    }

    void delay(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
        }
    }




    void setInitialCash(int cash){
        for(Player p: players)
            p.setCash(cash);
    }
    void drawCash(){
        canvas.setHud(Integer.toString(players.get(0).getCash()));
        canvas.setCompCash(Integer.toString(players.get(1).getCash()));
        canvas.repaint();
    }
    boolean waitFor(Player p){
        return !(p.isCall() || p.isFold() || p.isRaise() || p.isCheck());
    }
    void setTurn(Player p){
        if(p.equals(players.get(0)))
            canvas.setGameTurn("Your");
        if(p.equals(players.get(1)))
            canvas.setGameTurn("Computer's");
    }

    void drawState(){
        if(players.get(0).isCall())
            canvas.setState("Called");
        if(players.get(0).isFold())
            canvas.setState("Folded");
        if(players.get(0).isRaise())
            canvas.setState("Raised");
        if(players.get(0).isCheck())
            canvas.setState("Checked");

        if(players.get(1).isCall())
            canvas.setCompState("Called");
        if(players.get(1).isFold())
            canvas.setCompState("Folded");
        if(players.get(1).isRaise())
            canvas.setCompState("Raised");
        if(players.get(1).isCheck())
            canvas.setCompState("Checked");

        if(waitFor(players.get(0)))
            canvas.setState("None");
        if(waitFor(players.get(1)))
            canvas.setCompState("None");


        canvas.repaint();
    }
    void setBet(Player p, int bet){
        p.setBet(p.getBet() + bet);
        p.setCash(p.getCash() - bet);
        setPot();
        drawBets();
        drawCash();
    }
    void call(Player p){

        int requiredBet = requiredBet(p);
        if(requiredBet == 0) return;
        p.setCall();

        setBet(p,requiredBet);
    }

    void drawBets(){
        canvas.setBet(Integer.toString(players.get(0).getBet()));
        canvas.setCompBet(Integer.toString(players.get(1).getBet()));
        canvas.repaint();
    }

    void setPot(){
        pot = players.get(0).getBet() + players.get(1).getBet();
        canvas.setPot(Integer.toString(pot));
        canvas.repaint();
    }

    void deal(){
        Card h1 = deck.drawCard();
        Card h2 = deck.drawCard();
        Card ch1 = deck.drawCard();
        Card ch2 = deck.drawCard();
        Card[] hand = {h1,h2};
        Card[] compHand = {ch1,ch2};
        players.get(0).setHand(hand);
        players.get(1).setHand(compHand);

        canvas.setHand1(h1.getImage());
        canvas.setHand2(h2.getImage());
        canvas.setCompHand1(deck.getBack().getImage());
        canvas.setCompHand2(deck.getBack().getImage());
        canvas.repaint();
    }
    void reveal(){
        canvas.setCompHand1(players.get(1).getHand()[0].getImage());
        canvas.setCompHand2(players.get(1).getHand()[1].getImage());
        canvas.repaint();
    }

    void clearTable(){
        for(Card c: table)
            deck.addCard(c);
        table.clear();
        canvas.clearTable();
        deck.shuffle();
        canvas.repaint();
    }

    void flop(){
        Card c1 = deck.drawCard();
        Card c2 = deck.drawCard();
        Card c3 = deck.drawCard();
        table.add(c1);
        table.add(c2);
        table.add(c3);
        canvas.setFlop1(c1.getImage());
        canvas.setFlop2(c2.getImage());
        canvas.setFlop3(c3.getImage());
        canvas.repaint();
    }
    void turn(){
        Card c1 = deck.drawCard();
        table.add(c1);
        canvas.setTurn(c1.getImage());
        canvas.repaint();
    }
    void river(){
        Card c1 = deck.drawCard();
        table.add(c1);
        canvas.setRiver(c1.getImage());
        canvas.repaint();
    }

    int handVal(Player p){
       return e.gethandValue(p.getHand(),table);
    }
    void bigBlind(Player p){
        setBet(p,200);
        for(Player pa: players)
            if(!pa.equals(p))
                setBet(pa,100);
    }
    Player getWinner() {
        if (handVal(players.get(0)) > handVal(players.get(1))) {
            System.out.println("player wins");
            return players.get(0);
        }
        else {
            System.out.println("comp wins");
            return players.get(1);
        }
    }

    int requiredBet(Player p){
        return pot - (2*p.getBet());
    }

    void resetStates(){
        players.get(0).reset();
        players.get(1).reset();
    }

    void compDecision(){
        int val = e.gethandValue(players.get(1).getHand(),table);
        int reqBet = requiredBet(players.get(1));

        System.out.println("requiredBet: " + reqBet);
        if(reqBet == 0){
            if(val <= 1000){
                System.out.println("check");
              players.get(1).setCheck();
            }else {
                System.out.println("raise");
                players.get(1).setRaise();
                setBet(players.get(1),200);
            }
        }else{
            if(val > 10){
                System.out.println("call");
                call(players.get(1));
            }else{
                System.out.println("fold");
                players.get(1).setFold();
            }
        }

    }







}

