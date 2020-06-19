/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopproject;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

import javax.swing.JPanel;
import static oopproject.Oopproject.frAme;
import static oopproject.Oopproject.height;
import static oopproject.Oopproject.width;





public class gammeplay extends JPanel {

	static boolean ballcontrol = false;
   private int mousex;
   boolean running ;
   private  BufferedImage image;//store image in memory
    // private  BufferedImage img;
   Image img;
   Image ballimg;
   Image img2;
   Image img3;
   Image img4;
   Image img5;
   private Graphics2D g; // this graphics object is for drawing on the Buffered Image
   private ball theball;
   private paddel pad;
   public bricks map ; 
   public static int count;//to test the levels 
     
  player player;
   public static int levelcount=1 ; 
   private int mousexdir;
   private mouselis mouse;
   private  Sound s;
   boolean checker = true;
   public  enemy enem;
   public static boolean endlevelPU=false;
    
      private boolean enemyend;
   public gammeplay() throws IOException {
       this.setVisible(true);
       img = Toolkit.getDefaultToolkit().createImage("level1.jpg");
       img2 = Toolkit.getDefaultToolkit().createImage("level2.jpg");
       img3 = Toolkit.getDefaultToolkit().createImage("level3.jpg");
       img4 = Toolkit.getDefaultToolkit().createImage("level4.jpg");
       img5 = Toolkit.getDefaultToolkit().createImage("level5.jpg");
      // ballimg = Toolkit.getDefaultToolkit().createImage("ball5.jpg");
       initail();
       s=new Sound("sound\\bensound-slowmotion_2.wav");
       s.playSound();
       count=0; 
    }
   
    public void initail() throws IOException
    {
      running= true;
   // BufferedImage image = ImageIO.read(new File("C:\\Users\\LENOVO\\Desktop\\screen shut\\project3.jpg"));
      image = new BufferedImage(Oopproject.width, Oopproject.height,BufferedImage.TYPE_INT_RGB);
      g =image.createGraphics(); //(Graphics2D)image.getGraphics();
   // map=new bricks(6,7);//////
    //map =new bricks(7,8);
       theball = new ball();
       pad = new paddel();
       mouse = new mouselis();
       addMouseMotionListener(mouse);//intialize motion of mouse
       g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       enem = new enemy ();

	}
    

		 
   
    public void run() throws IOException, InterruptedException {
      
// game main 
Thread thread =new Thread();
boolean enemystart= true;
while(running){
	   if(levelcount >=6){running = false;
           break;
        }		
        
        // music();
        s.playSound();
        updata();
        draw();
        repaint();//subcalss in jpanel
        if(enem.checkbricks())
            enemystart=true ; 
        try{
            Thread.sleep(10);
        }   catch (InterruptedException ex) {
                Logger.getLogger(gammeplay.class.getName()).log(Level.SEVERE, null, ex);
            }
       
          
	if(map.checkbricks()&&enemystart)
        {
                    if(levelcount == 1){
                    player = new player(adddata.pname, count);
                    player.add();
                    }
                    else{
                    player.update(adddata.pname, count);
                    }
			for (int i=0;i<1;i++)
			{
			  thread.sleep(500);
			}
        	levelcount++;
		levels();
               
                if (levelcount==5){
                enemystart=false;
              
                if(enemystart){
                player.update(adddata.pname, count);
               
                enemy.gameend=true ; 
               
                
               }
            }
        } 
       
        
        
  }
if(!running){
    System.out.println("eee");
  Oopproject.frAme.dispose();
            won_lose neww= new  won_lose();
           neww.setVisible(true);
}
    
       
    }
    
   
    public  void updata() throws InterruptedException
    {
		
        theball.checkcol(pad, map, mousex, enem);
		if (ballcontrol){
        theball.update();}
          map.PUupdate();
          if(endlevelPU){
              map.EndlevelPU();
              endlevelPU=false;
          }
          if(levelcount == 5){
          enem.updatepos();}
          
    }
   
    
    
    public void draw(){
        
       
         
         g.setColor(Color.DARK_GRAY);
         
         g.fillRect(0, 0, Oopproject.width, Oopproject.height);
        switch(levelcount)
	 {
		 case 1:
		 {
	        g.drawImage(img,0,0,null);
                break; 
		 }
		 
		 case 2:
		 { 
                 g.drawImage(img2,0,0,null);
		 break; 
		 }
                 case 3:
                 {
                 g.drawImage(img3,0,0,null);
		 break; 
                 }
                  case 4:
                 {
                 g.drawImage(img4,0,0,null);
		 break; 
                 }
                   case 5:
                 {
                 g.drawImage(img5,0,0,null);
		 break; 
                 }
                
                 
        }
         theball.draw(g);
         pad.draw(g); 
         map.PUdraw(g);
         map.draw(g);
        g.setColor(Color.WHITE);
		g.setFont(new Font("serif",Font.ITALIC,25));
		g.drawString("score  : "+count, 650, 30);
		  g.setColor(Color.WHITE);
		g.setFont(new Font("serif",Font.ITALIC,25));
		g.drawString("level : "+levelcount, 30, 30);
                 g.setColor(Color.WHITE);
		g.setFont(new Font("serif",Font.ITALIC,25));
		g.drawString("lifes : "+paddel.lifes, 350, 30);
  
	
         if(levelcount ==5){
             
         enem.draw(g);
         }
    
       
    }
    
    
    
    
    
    
    //this methd is for painint on Jpanel
     @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
 //g: is a graphics component for drawinng in Jpanel
         //g.fillRect(0, 0, Oopproject.width, Oopproject.height);
         
        draw();
      
       g.drawImage(image, 0, 0, this);
  }
 
    private class mouselis implements MouseMotionListener{

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseMoved(MouseEvent e) {
             mousex= e.getX();
             pad.mousemove(e.getX());
                  }

      
    }
  
	
	 public void levels()
	 {
		 theball.setballpostion(theball.intialxpos, theball.intialypos);
		 int level=levelcount ; 
	 switch(level)
	 {
		 case 1:
		 {
		  map =new bricks(3,3);
       map.draw(g);
      break; 
		 }
		 
		 case 2:
		 {  
                     map.PUdisable();
			map=new bricks(4, 4);
			map.draw(g);
                      
                         
		 break; 
		 }
		 case 3:
                    
		 {  map.PUdisable();
                     map =new bricks(4,5);
       map.draw(g);
		 break; 
		 }
		 case 4:
		 {
                     map.PUdisable();
		map =new bricks(6,6);
       map.draw(g);
		
		 break ;
		 }
		 case 5:
		 {
                     map.PUdisable();
		  enem.createmap(1, 1);
		 break; 
		 }
                 default:
                 {
                 
                 break;
                  
                 
	 }
	 
	 
	 }}
         
//    public boolean winthegame()
//    {
//    if(enemyend)
//        return true ; 
//    else 
//        return false ; 
//    
//    }
}

