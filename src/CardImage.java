import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lagrange on 4/10/2017.
 */
public class CardImage {


   Map<Integer, BufferedImage> images = new HashMap<>();
   Map<Integer,BufferedImage> cards = new HashMap<>();



    BufferedImage back;

   CardImage(){

       try{

           images.put(1,ImageIO.read(new File("Images/ace_of_diamonds.png")));
           images.put(2,ImageIO.read(new File("Images/2_of_diamonds.png")));
           images.put(3,ImageIO.read(new File("Images/3_of_diamonds.png")));
           images.put(4,ImageIO.read(new File("Images/4_of_diamonds.png")));
           images.put(5,ImageIO.read(new File("Images/5_of_diamonds.png")));
           images.put(6,ImageIO.read(new File("Images/6_of_diamonds.png")));
           images.put(7,ImageIO.read(new File("Images/7_of_diamonds.png")));
           images.put(8,ImageIO.read(new File("Images/8_of_diamonds.png")));
           images.put(9,ImageIO.read(new File("Images/9_of_diamonds.png")));
           images.put(10,ImageIO.read(new File("Images/10_of_diamonds.png")));
           images.put(11,ImageIO.read(new File("Images/jack_of_diamonds2.png")));
           images.put(12,ImageIO.read(new File("Images/queen_of_diamonds2.png")));
           images.put(13,ImageIO.read(new File("Images/king_of_diamonds2.png")));

           images.put(14,ImageIO.read(new File("Images/ace_of_clubs.png")));
           images.put(15,ImageIO.read(new File("Images/2_of_clubs.png")));
           images.put(16,ImageIO.read(new File("Images/3_of_clubs.png")));
           images.put(17,ImageIO.read(new File("Images/4_of_clubs.png")));
           images.put(18,ImageIO.read(new File("Images/5_of_clubs.png")));
           images.put(19,ImageIO.read(new File("Images/6_of_clubs.png")));
           images.put(20,ImageIO.read(new File("Images/7_of_clubs.png")));
           images.put(21,ImageIO.read(new File("Images/8_of_clubs.png")));
           images.put(22,ImageIO.read(new File("Images/9_of_clubs.png")));
           images.put(23,ImageIO.read(new File("Images/10_of_clubs.png")));
           images.put(24,ImageIO.read(new File("Images/jack_of_clubs2.png")));
           images.put(25,ImageIO.read(new File("Images/queen_of_clubs2.png")));
           images.put(26,ImageIO.read(new File("Images/king_of_clubs2.png")));

           images.put(27,ImageIO.read(new File("Images/ace_of_hearts.png")));
           images.put(28,ImageIO.read(new File("Images/2_of_hearts.png")));
           images.put(29,ImageIO.read(new File("Images/3_of_hearts.png")));
           images.put(30,ImageIO.read(new File("Images/4_of_hearts.png")));
           images.put(31,ImageIO.read(new File("Images/5_of_hearts.png")));
           images.put(32,ImageIO.read(new File("Images/6_of_hearts.png")));
           images.put(33,ImageIO.read(new File("Images/7_of_hearts.png")));
           images.put(34,ImageIO.read(new File("Images/8_of_hearts.png")));
           images.put(35,ImageIO.read(new File("Images/9_of_hearts.png")));
           images.put(36,ImageIO.read(new File("Images/10_of_hearts.png")));
           images.put(37,ImageIO.read(new File("Images/jack_of_hearts2.png")));
           images.put(38,ImageIO.read(new File("Images/queen_of_hearts2.png")));
           images.put(39,ImageIO.read(new File("Images/king_of_hearts2.png")));

           images.put(40,ImageIO.read(new File("Images/ace_of_spades.png")));
           images.put(41,ImageIO.read(new File("Images/2_of_spades.png")));
           images.put(42,ImageIO.read(new File("Images/3_of_spades.png")));
           images.put(43,ImageIO.read(new File("Images/4_of_spades.png")));
           images.put(44,ImageIO.read(new File("Images/5_of_spades.png")));
           images.put(45,ImageIO.read(new File("Images/6_of_spades.png")));
           images.put(46,ImageIO.read(new File("Images/7_of_spades.png")));
           images.put(47,ImageIO.read(new File("Images/8_of_spades.png")));
           images.put(48,ImageIO.read(new File("Images/9_of_spades.png")));
           images.put(49,ImageIO.read(new File("Images/10_of_spades.png")));
           images.put(50,ImageIO.read(new File("Images/jack_of_spades2.png")));
           images.put(51,ImageIO.read(new File("Images/queen_of_spades2.png")));
           images.put(52,ImageIO.read(new File("Images/king_of_spades2.png")));

           back = ImageIO.read(new File("Images/back.png"));

           System.out.println("Images_Successfully_read.");
       }catch(IOException e){
           System.err.print("Image Reading Error");
           e.printStackTrace();
       }
       double widthToHeight = 500 / 726;

       int height = 160;
       int width = height * 500 / 726;

       double xfact = (double)width / (double)500;
       double yfact = (double)height / (double)726;


       back = scale(back,BufferedImage.TYPE_INT_ARGB,width,height,xfact,yfact);
       //System.out.print("x: " + xfact + "y " + yfact) ;

       //System.out.print(width);
       for(Integer key: images.keySet()){
           cards.put(key,scale(images.get(key),BufferedImage.TYPE_INT_ARGB,width,height,xfact,yfact));
       }

   }


    public BufferedImage getBack() {
        return back;
    }






    public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
        BufferedImage dbi = null;
        if(sbi != null) {
            dbi = new BufferedImage(dWidth, dHeight, imageType);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
            g.drawRenderedImage(sbi, at);
        }
        return dbi;
    }





}
