
package oopproject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JLabel;

public class paddel {
    private Rectangle padrct;
 public static double posx;
public static int width,height;
public static int defwidth=100;
public static int  posy=Oopproject.height-100;
public static int lifes = 4;
 Image padimg;
 
//public static double posy = Oopproject.height -100;  ask magdy about it

    public paddel() {
        padimg = Toolkit.getDefaultToolkit().createImage("icon1.png");
        width = 100;
        height = 20;
        posx = Oopproject.width/2 - width / 2;
      
    }

    public static int getLifes() {
        return lifes;
    }

    public static void setLifes(int lifes) {
        paddel.lifes = lifes;
    }
    
    public void update (){
    
    }
public  void draw (Graphics2D g ){
g.setColor(Color.black);


g.fillRect((int)posx, posy, width, height);
 //g.drawImage(padimg,(int)posx, posy,null);
g.drawImage(padimg, (int)posx, posy, width, height, null);
}
public void mousemove(int mousepos){
posx=mousepos;
if(posx >Oopproject.width -width)
    posx= Oopproject.width -width;
}

    public int getWidth() {
        return width;
    }
public static Rectangle getpad(){
return (new Rectangle((int) posx,(int)posy,width,height));
}

    void draw(JLabel background) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
