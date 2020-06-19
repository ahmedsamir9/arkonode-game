package oopproject;

import java.util.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.math.MathContext;
import javax.swing.JLabel;
import static oopproject.gammeplay.count;
import static oopproject.paddel.posx;
import static oopproject.paddel.posy;
public class bricks {
    




	private int [][] map ; 
	private int  brickwidth , brickheight; 
	public final int hor_pad = 80 , ver_pad=40 ;  
        Image bric_img;
        Image bric_img2;
        Image bric_img3;
        PowerUpsFactory p;
        int PUnum=2;
        PowerUps [] p1 = new PowerUps[PUnum];
        int [] PUraw = new int[PUnum];
        int [] PUcol = new int[PUnum];
        int p1row, p1col;
	public static boolean ckeck; 
        
	
	public bricks(int row ,int column)
	{
            
	 creatthemap(row,column); 
	 	brickheight=(Oopproject.height/2 - ver_pad)/row;
	brickwidth=(Oopproject.width-2* hor_pad)/column ; 
        
        bric_img = Toolkit.getDefaultToolkit().createImage("bric1.jpg");
        bric_img2 = Toolkit.getDefaultToolkit().createImage("bric2.jpg");
        bric_img3 = Toolkit.getDefaultToolkit().createImage("bric3.jpg");
       
	}
	
	
        
        /*  public bricks()
        {
        
        bric_img = Toolkit.getDefaultToolkit().createImage("milkyway.jpg");
        
        }*/
	
	public void creatthemap(int row ,int column)
	{
	map=new int [row][column]; 
	
	for(int i=0; i<map.length ; i++)
	{
	for(int j=0 ; j<map[0].length;j++)
        {	
            int x=(int)(Math.random()*3+1); 
            map[i][j]=x;
            
            //create power ups for the map
            PUraw[0] = (int)(Math.random()*2 + 0);
            PUcol[0] = (int)(Math.random()*3 + 1);
            p1[0] = p.CreatePowerUp((int)(Math.random()*7+1), PUcol[0]*(getwidth()+130)+hor_pad+50 , PUraw[0]*(getheight()+40)+ver_pad+15);
            
            PUraw[1] = (int)(Math.random()*2 + 0);
            PUcol[1] = (int)(Math.random()*3 + 1);
            p1[1] = p.CreatePowerUp((int)(Math.random()*7+1), PUcol[1]*(getwidth()+130)+hor_pad+50 , PUraw[1]*(getheight()+40)+ver_pad+15);
            
        }	 
	}

	}
	
	public void draw(Graphics2D g)
	{
		
         for(int i=0; i<map.length ; i++)
	{ 
	for(int j=0 ; j<map[0].length;j++)
	{
		if(map[i][j]>0)
		{	
                 switch(map[i][j]){
                     case 1 :
                     {  g.setColor(Color.red);
		        g.fillRect(j * brickwidth+hor_pad, i*brickheight+ver_pad, brickwidth, brickheight);
                        g.drawImage(bric_img,(int)(j * brickwidth+hor_pad),(int) (i*brickheight+ver_pad), brickwidth, brickheight, null);
                        
                     break ; 
                     }
                     case 2:
                               {  g.setColor(Color.ORANGE);
		g.fillRect(j * brickwidth+hor_pad, i*brickheight+ver_pad, brickwidth, brickheight);
                g.drawImage(bric_img2,(int)(j * brickwidth+hor_pad),(int) (i*brickheight+ver_pad), brickwidth, brickheight, null);
                 
                     break ; 
                     }
                     case 3 :
                               {  g.setColor(Color.yellow);
		g.fillRect(j * brickwidth+hor_pad, i*brickheight+ver_pad, brickwidth, brickheight);
                g.drawImage(bric_img3,(int)(j * brickwidth+hor_pad),(int) (i*brickheight+ver_pad), brickwidth, brickheight, null);
                
                     break ; 
                     }
		
	             }
                g.setStroke(new BasicStroke(2));
		g.setColor(Color.BLACK);
		g.drawRect(j * brickwidth+hor_pad,  i*brickheight+ver_pad, brickwidth, brickheight);
                //g.drawImage(bric_img,j * brickwidth+hor_pad, i*brickheight+ver_pad, brickwidth, brickheight, null);
                }
//                else 
//                {
//                if(i>=map.length)
//                {
//                    ckeck=true;
//                }
//                }
//                
	}
	}
       
        
       

	}
   
	
	public int[][] getmaparray() {return map ;}
	
	// we will send the position of the hit brick in the array to delete it from the screen 
	
	public void setbrick(int row , int col ){
            map[row][col]-- ;
            count+=5; 
            if (map[row][col]==0){
                for(int i=0; i<PUnum;i++){
                    if (row==PUraw[i] && col==PUcol[i]){
                        p1[i].isActive=true;
                        p1[i].running=true;
                    }
                }
            }
        }
        
        
        //update all power ups in map
        public void PUupdate() throws InterruptedException
	{
            for(int i=0; i<PUnum;i++){
                if (p1[i].running){
                    p1[i].PUpaddelcol();
                    p1[i].running();            
                }
            }
        }
        
        //disable all power ups before next level
        public void PUdisable()
	{
            for(int i=0; i<PUnum;i++){
                p1[i].Default();
            }
        }
        
        //paint all power ups in map
         public void PUdraw(Graphics2D g)
	{
            for(int i=0; i<PUnum;i++)
                p1[i].Draw(g);
        }
	
	public int getwidth(){return brickwidth;}
	public int getheight(){return brickheight;}
         void draw(JLabel background) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }
         

         //Break power up
         public void EndlevelPU(){
             for (int i = 0; i < map.length; i++)
                 for (int j = 0; j <map[0].length; j++)
                     map[i][j]=0;
         }
         
public boolean checkbricks (){
        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j <map[0].length; j++) {
                if(map[i][j]!=0)
                    return false ; 
            }
            
        }
    
    return true  ; 
    }

}

