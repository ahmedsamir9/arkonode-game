package oopproject;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.crypto.NullCipher;
import javax.imageio.ImageIO;
import javax.swing.*;
import oopproject.player;
import oopproject.bricks;
//import static oopproject.gammeplay.music;
public class Oopproject {
    public  static gammeplay play  ;    
	public  static  JFrame frAme = new JFrame("arkonode");
      //     bricks bric;
// int[][] mapvar = bric.getmaparray();
 public static final int height=600,width = 800;
    public static void main(String[] args)throws IOException, InterruptedException {
        
        play = new gammeplay();
        play.validate();
        frAme.add(play);
        first_form_jframe f=new first_form_jframe();
        f.setSize(width,height);
        f.setLocation(300, 300);
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
       play.levels();
        frAme.validate();        
        play.run();
        frAme.validate();
        
        
        
        
    }
}
