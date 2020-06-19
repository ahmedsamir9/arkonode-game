/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopproject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class PowerUps {
    int id, x, y, duration;
    Color color;
    boolean isActive=false, catched= false, changes = false, running=false, defaulfiscalled=false;
    final int speed = 3;
    Image PUimg;
    long time;
    

    public PowerUps(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    abstract void Action();
    abstract void Default();
    public void running(){
        
        if (isActive){
            y+=speed;
            if (catched && !changes){
                isActive=false;
                changes=true;
                Action();
                time = System.nanoTime();    
            }
        }
        if (y>Oopproject.height)
            isActive=false;
        
        if(((System.nanoTime()-time)/1000000) > duration*1000 && catched){
            if (!defaulfiscalled){
                Default();
                defaulfiscalled=true;
            }
            running=false;
            }
    }
    
    public void Draw(Graphics2D g){
        if(isActive){
        g.setColor(Color.black);
        g.fillRect(x, y, 30, 30);
        g.drawImage(PUimg, x, y, 30, 30, null);
        }
    }
    public Rectangle getPU(){
        return (new Rectangle(x, y, 30, 30));
    }
    
    public void PUpaddelcol(){
        Rectangle padrect = paddel.getpad();
        Rectangle PUrect = getPU();
        if(PUrect.intersects(padrect))
            catched=true;
    }

}

class Shrink extends PowerUps {
   
    public Shrink(int x, int y) {
        super(x, y);
        id = 1;
        duration=10;
        PUimg = Toolkit.getDefaultToolkit().createImage("shrink.png");
    }

   @Override
    void Action() {
        paddel.width=paddel.defwidth/2;
    }

    @Override
    void Default() {
        paddel.width=paddel.defwidth;
    }
   
   
}

class LoseLife extends PowerUps {

    public LoseLife(int x, int y) {
        super(x, y);
        id = 2;
        PUimg = Toolkit.getDefaultToolkit().createImage("player2.png");
    }
   

    @Override
    void Action() {
        paddel.lifes--;
    }

    @Override
    void Default() {
        
    }
}


class Expand extends PowerUps {
    
    public Expand(int x, int y) {
        super(x, y);
        id = 3;
        duration=10;
        PUimg = Toolkit.getDefaultToolkit().createImage("paddel.png");
    }

   
    @Override
    void Action() {
        paddel.width=paddel.defwidth*2;
    }

    @Override
    void Default() {
        paddel.width=paddel.defwidth;
    }
}


class Disrupt extends PowerUps {
    
    public Disrupt(int x, int y) {
        super(x, y);
        id = 8;
        //duration=10;
        PUimg = Toolkit.getDefaultToolkit().createImage("3balls.png");
    }

   
    @Override
    void Action() {
        ball b1 = new ball(paddel.posx +45, paddel.posy-30, -1, -2);
        ball b2 = new ball(paddel.posx+25, paddel.posy-30, 1, -2);
    }

    @Override
    void Default() {
        
    }
}


class Fast extends PowerUps {

    public Fast(int x, int y) {
        super(x, y);
        id = 5;
        duration=10;
        PUimg = Toolkit.getDefaultToolkit().createImage("fast.png");
    }

    @Override
    void Action() {
        ball.isFast=true;
    }

    @Override
    void Default() {
        ball.isFast=false;
    }
}

class Slow extends PowerUps {

    public Slow(int x, int y) {
        super(x, y);
        id = 6;
        duration=10;
        PUimg = Toolkit.getDefaultToolkit().createImage("slow.png");
    }

    @Override
    void Action() {
        ball.isSlow=true;
    }

    @Override
    void Default() {
        ball.isSlow=false;
    }
}

class Break extends PowerUps {    

    public Break(int x, int y) {
        super(x, y);
        id = 7;
        PUimg = Toolkit.getDefaultToolkit().createImage("break.png");
    }

    @Override
    void Action() {
        gammeplay.endlevelPU=true;
    }

    @Override
    void Default() {
        
    }
}

class LIFES extends PowerUps{

    public LIFES(int x, int y) {
        super(x, y);
        id = 4;
        PUimg = Toolkit.getDefaultToolkit().createImage("player.png");
    }

    @Override
    void Action() {
        paddel.lifes++;
    }

    @Override
    void Default() {
        
    }
    
    
}
