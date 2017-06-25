/**
 * Created by Lagrange on 4/10/2017.
 */
public class Player {



    int cash = 0;
    int bet = 0;

    boolean call;
    boolean fold;
    boolean check;
    boolean raise;

    Card[] hand = new Card[2];



    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getBet() {return bet;}

    public void setBet(int bet) {this.bet = bet;}

    public boolean isCall() {return call;}

    public void setCall() {
        check = false;
        call = true;
        fold = false;
        raise = false;
    }

    public boolean isFold() {return fold;}

    public void setFold() {
        fold = true;
        call = false;
        check = false;
        raise = false;
    }

    public boolean isRaise() {return raise;}

    public void setRaise() {
        check = false;
        raise = true;
        call = false;
        fold = false;
    }
    public boolean isCheck() {return check;}

    public void setCheck() {
        check = true;
        raise = false;
        call = false;
        fold = false;
    }
    public void reset(){
        check = false;
        raise = false;
        call = false;
        fold = false;
    }

    public Card[] getHand() {return hand;}

    public void setHand(Card[] hand) {this.hand = hand;}
}
