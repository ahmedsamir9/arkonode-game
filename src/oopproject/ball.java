

package oopproject;

import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static oopproject.Oopproject.frAme;
import static oopproject.Oopproject.height;
import static oopproject.Oopproject.width;

public class ball {
    private Rectangle ballrect;
	public static double intialxpos;
	public static double intialypos;
    public double posx,posy,dirx,diry;
    private int ballsize = 20;//in draw image
    public static boolean isSlow=false;
    public static boolean isFast=false;
    
   // private  BufferedImage image;//store image in memory

    private Graphics2D g; // this graphics object is for drawing on the Buffered Image
    Image ballimg;
 
 
    public ball() 
    {
        //ballimg = Toolkit.getDefaultToolkit().createImage("ball3.png");
        posx = Oopproject.height/2;//in draw image
        posy =400;//in draw image
        dirx = -1;
		intialxpos = posx;
		intialypos= posy;
        diry= -2;
        //image = new BufferedImage(Oopproject.width, Oopproject.height,BufferedImage.TYPE_INT_RGB);
       // g =image.createGraphics(); //(Graphics2D)image.getGraphics();
    }

    public ball(double posx, double posy, double dirx, double diry) {
        this.posx = posx;
        this.posy = posy;
        this.dirx = dirx;
        this.diry = diry;
    }
    
    
    public  void update()
    {
            set_pos();
        
     // g.drawImage(ballimg,0,0, null);
    } 
    public void set_pos() {
     
        //check fast & slow power up
        if(isSlow || isFast){
        //check slow power up
            if(isSlow){
                posx+=dirx/2;
                posy+=diry/2;
            }
        
        //check fast power up
            if(isFast){
                posx+=dirx*2;
                posy+=diry*2;
            }
        }
        
        else{
        posx+=dirx;
        posy+=diry;
        }
        
        
        if(posx<0){
        dirx =-dirx;
        }
        if(posy<0){ 
            diry=-diry;        
        }
        if(posx>Oopproject.width - ballsize){
            dirx= -dirx;    
        }
        if (posy>Oopproject.height -ballsize){
         //diry =-diry;
            if(paddel.lifes<=0) {
                won_lose lose=new won_lose();
                
               lose.setVisible(true);
               lose.setSize(width,height);
               lose.setLocation(300, 300);
               lose.setResizable(false);
               lose.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
              
             
               posx =paddel.posx+35;
               posy =paddel.posy -30;
               dirx = 0;
               diry= 0;
            }
           else{
           posx =paddel.posx+35;
           posy =paddel.posy -30;
           dirx = 1;
           diry= -3;
           paddel.lifes--;
            }
        }
    }
       
                
    
    
    public void draw(Graphics2D g){
       
         g.setColor(Color.WHITE);
          // g.drawImage(ballimg,(int)posx, (int)posy, ballsize, ballsize, null);
         g.fillOval((int)posx, (int)posy,ballsize ,ballsize);
         g.drawOval((int)posx, (int)posy,ballsize ,ballsize);
      
    }
    
    
    
    
public Rectangle2D getpad(){
return (new Rectangle((int) posx,(int)posy,ballsize,ballsize));
}
public void setposy(double dy){

diry = dy;
}

    public double getPosx() {
       
        return posx;
    }

    public void setDirx(double dirx) {
        this.dirx = dirx;
    }

    public double getDirx() {
        
        return dirx;
    }

    public double getDiry() {
       
        return diry;
    }

    public void setDiry(double diry) {
        this.diry = diry;
    }
   
	
	public void setballpostion(double x , double y)
	{
	posx = x;
	posy = y;
	}

	public void setPosx(double posx) {
		this.posx = posx;
	}

	public void setPosy(double posy) {
		this.posy = posy;
	}
        
        
        
        
        public void checkcol(paddel pad, bricks map, int mousex, enemy enem){
        Rectangle padrect = pad.getpad();
        Rectangle ballrect = (Rectangle) this.getpad();
        
        if(ballrect.intersects(padrect)){
        this.setposy(-this.getDiry());
        if(this.getPosx()<mousex+pad.getWidth()/4){this.setDirx(this.getDirx()-0.6);}
        if(this.getPosx()<mousex+pad.getWidth()&&this.getPosx()>mousex+pad.getWidth()/4){this.setDirx(this.getDirx()+0.6);}
        } 
        
        stop:	for(int row=0;row<map.getmaparray().length;row++)
	{
	for(int col=0 ; col <map.getmaparray()[row].length;col++)
	{
		if(map.getmaparray()[row][col]>0)
		{
	int brickXpos= col*map.getwidth()+map.hor_pad ;
	int brickYpos= row*map.getheight()+map.ver_pad ; 
	int brickwidth=map.getwidth();
	int brickheight=map.getheight();
	

		Rectangle brickrect=new Rectangle(brickXpos,brickYpos,brickwidth,brickheight);
		if(ballrect.intersects(brickrect))
		{
                       
		map.setbrick(row, col);
	        this.setposy(-this.getDiry());
                
		
// after we find the collsion we have to stop checking 
		break stop;      //label
            
		}
		}
	}
	
	}


         if(gammeplay.levelcount == 5){

        stopenemy:	for(int row=0;row<enem.getmaparray().length;row++)
	{
	for(int col=0 ; col <enem.getmaparray()[0].length;col++)
	{
		if(enem.getmaparray()[row][col]>0)
		{
	int brickXpos= enem.getXpos() ;
	int brickYpos= enem.getYpos() ; 
	int brickwidth=enem.getWidth();
	int brickheight=enem.getHeight();
	

		Rectangle2D enemrect=new Rectangle(brickXpos,brickYpos,brickwidth,brickheight);
		if(ballrect.intersects(enemrect))
		{
                       
		enem.setbrick(row, col);
	        this.setposy(-this.getDiry());
                
		
// after we find the collsion we have to stop checking 
		break stopenemy;      //label
            
		}
		}
	}
	
	}}
    }
        
	  
}



