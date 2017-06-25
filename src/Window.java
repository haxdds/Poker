import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;


/**
 * Created by Lagrange on 4/10/2017.
 */
public class Window extends JFrame {

   static int height;
   static int width;
   static Game game;


    public Window(int width, int height, String title, Color color, ImageCanvas canvas, Game g){
        this.height = height;
        this.width = width;
        this.game = g;

        JFrame frame = new JFrame(title);
        JPanel panel = new JPanel();

        JTextField field = new JTextField();
        field.setSize(100,30);
        field.setLocation(width - 150, height - 120);
        field.setText("0");
        field.setVisible(true);

        frame.add(field);


        JButton call = new JButton();
        call.setSize(100,75);
        call.setLocation(width - 450,height - 200);
        call.setVisible(true);
        call.setText("CALL");

        call.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.call(game.players.get(0));
                game.drawState();
            }
        });

        frame.add(call);

        JButton fold = new JButton();
        fold.setSize(100,75);
        fold.setLocation(width - 350,height - 200);
        fold.setVisible(true);
        fold.setText("FOLD");

        fold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.players.get(0).setFold();
                game.reveal();
                game.drawState();
            }
        });

        frame.add(fold);


        JButton raise = new JButton();
        raise.setSize(100,75);
        raise.setLocation(width -150,height - 200);
        raise.setVisible(true);
        raise.setText("RAISE");

        raise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!field.getText().equals("0")){
                    game.players.get(0).setRaise();
                    game.drawState();
                    game.setBet(game.players.get(0),Integer.parseInt(field.getText()));
                    field.setText(Integer.toString(game.players.get(0).getBet()));
                    game.setPot();
                }
            }
        });

        frame.add(raise);

        JButton check = new JButton();
        check.setSize(100,75);
        check.setLocation(width - 250, height - 200);
        check.setVisible(true);
        check.setText("CHECK");

        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.players.get(0).setCheck();
                game.drawState();
            }
        });

        frame.add(check);

        frame.add(panel);
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(color);
        frame.add(canvas);


        frame.setVisible(true);

    }
    public static class ImageCanvas extends Canvas {

        private BufferedImage hand1;
        private BufferedImage hand2;

        private BufferedImage compHand1;
        private BufferedImage compHand2;

        private String hudCash;
        private String compCash;

        private String bet;
        private String compBet;

        private String state;
        private String compState;

        private String pot;

        private String gameTurn;

        public void setFlop1(BufferedImage flop1) {
            this.flop1 = flop1;
        }

        public void setFlop2(BufferedImage flop2) {
            this.flop2 = flop2;
        }

        public void setFlop3(BufferedImage flop3) {
            this.flop3 = flop3;
        }

        private BufferedImage flop1;
        private BufferedImage flop2;
        private BufferedImage flop3;

        public void setTurn(BufferedImage turn) {
            this.turn = turn;
        }

        public void setRiver(BufferedImage river) {
            this.river = river;
        }

        private BufferedImage turn;
        private BufferedImage river;

        public ImageCanvas(BufferedImage hand1, BufferedImage hand2) {
            this.hand1 = hand1;
            this.hand2 = hand2;

        }

        public void setCompHand1(BufferedImage ch1){this.compHand1 = ch1;}
        public void setCompHand2(BufferedImage ch2){this.compHand2 = ch2;}

        public void setHand1(BufferedImage hand1){this.hand1 = hand1;}

        public void setHand2(BufferedImage hand2){
            this.hand2 = hand2;
        }

        public void clearTable(){
            hand1 = null;
            hand2 = null;
            compHand1 = null;
            compHand2 = null;
            flop1 = null;
            flop2 = null;
            flop3 = null;
            turn = null;
            river = null;
        }
        public void setHud(String cash){
            hudCash = "Cash: ";
            hudCash += cash;
        }
        public void setCompCash(String cash){
            compCash = "Cash: ";
            compCash += cash;
        }
        public void setState(String state){
            this.state = "State: ";
            this.state += state;
        }
        public void setCompState(String state){
            this.compState = "State: ";
            this.compState += state;
        }
        public void setBet(String bet){
            this.bet = "Bet: ";
            this.bet += bet;
        }
        public void setCompBet(String bet){
            compBet = "Bet: ";
            compBet += bet;
        }
        public void setPot(String pot){
            this.pot = "Pot: ";
            this.pot += pot;
        }
        public void setGameTurn(String player){
            gameTurn = "";
            gameTurn += player;
            gameTurn += " Turn";
        }



        @Override
        public Dimension getPreferredSize() {
            return hand1 == null ? new Dimension(200, 200) : new Dimension(hand1.getWidth(), hand1.getHeight());
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            g.setFont(new Font("TimesRoman",Font.PLAIN, 30));
            g.drawString(hudCash,50,height - 100);
            g.drawString(state,50,height - 150);
            g.drawString(bet,50, height - 50);
            g.drawString(compState,50, 50);
            g.drawString(compCash,50,100);
            g.drawString(compBet,50,150);
            g.drawString(pot, width/2 + hand1.getWidth() - 180, height / 2 - 100);
            g.drawString(gameTurn, width - 300, 50);


            if (hand1 != null && hand2 != null) {
                g.drawImage(hand1, width/2 - hand1.getWidth() - 50 , height - hand1.getHeight() - 50, this);
                g.drawImage(hand2, width/2 - 40 , height - hand2.getHeight() - 50, this);
            }
            if(compHand1 != null && compHand2 != null){
                g.drawImage(compHand1, width/2 - compHand1.getWidth() - 50, 30, this);
                g.drawImage(compHand2, width/2 - 40, 30, this);
            }

            if(flop1 != null && flop2 != null && flop3 != null){
                g.drawImage(flop1, width/2 - hand1.getWidth() - 180, height / 2 - 75, this);
                g.drawImage(flop2, width/2 - 170, height / 2 - 75, this);
                g.drawImage(flop3, width/2 + hand1.getWidth() - 160, height / 2 - 75, this);
            }

            if(turn != null) {
                g.drawImage(turn, width / 2 + 2*hand1.getWidth() - 150, height / 2 - 75, this);
            }
            if(river != null){
                g.drawImage(river, width/2 + 3*hand1.getWidth() - 140, height/2 - 75, this);
            }
        }

        BufferedImage rotate(BufferedImage bf, int angle){
            double rotationRequired = Math.toRadians (angle);
            double locationX = bf.getWidth() / 2;
            double locationY = bf.getHeight() / 2;
            AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            return op.filter(bf,null);
        }

    }


}
