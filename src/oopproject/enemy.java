package oopproject;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JFrame;
import static oopproject.gammeplay.count;


public class enemy {
	private int xpos; 
	private int ypos ; 
	private int xdir ;  
	private int width ; 
	private int height ; 
	private int ver_pos=80;
	private int hor_pos=40;
	private int [][]enemymap ; 
        public static boolean gameend ; 
	 int Count ; 
	 Image enemyimg;

	public enemy()
	{
		width=200;
		height=100; 
	xpos =Oopproject.width/4;
	ypos=Oopproject.height/4;
	 xdir=2 ;
	 Count=15;
	        enemyimg = Toolkit.getDefaultToolkit().createImage("enemy5.jpg");
		  gameend=false ; 
		createmap(1,1);
	}
	
	
	public void createmap(int row ,int column)
	{
	enemymap=new int [row][column]; 
	
	for(int i=0; i<enemymap.length ; i++)
	{
	for(int j=0 ; j<enemymap[0].length;j++)
        {	
            enemymap[i][j]=15;

        }	 
	}

	}
	
	public void updatepos()
	{
	
	xpos+=xdir ; 
	if(xpos<0)
	{xdir=-xdir;}
	
	if(xpos>Oopproject.width-width)
	{xdir=-xdir;}
	
	}
	
	
	public void draw(Graphics2D g)
	{
	
 for(int i=0; i<enemymap.length ; i++)
	{ 
	for(int j=0 ; j<enemymap[0].length;j++)
	{
		if(enemymap[i][j]>0)
		{
			g.setColor(Color.red);
			g.fillRect(xpos, ypos, width, height);
			 g.drawImage(enemyimg,xpos,ypos, width, height, null);
			break ; 
		}


	}
	}
	}
	
	public int getcount(){return Count ;}
	public void setcount(int newcount){Count =newcount ;}
	

	
public int[][] getmaparray() {return enemymap ;}


public void setbrick(int row , int col ){
           enemymap[row][col]-- ;
            count+=5; 
            }
        

public  boolean checkbricks (){
        
        for (int i = 0; i < enemymap.length; i++) {
            for (int j = 0; j <enemymap[0].length; j++) {
                if(enemymap[i][j]>0)
                { 
                    return false ; 
                }
                }
            }
    return true  ; 
    }

	public int getXpos() {
		return xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}

