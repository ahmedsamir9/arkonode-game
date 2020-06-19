/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopproject;

/**
 *
 * @author Lenovo
 */
public class PowerUpsFactory {
    public static PowerUps CreatePowerUp(int id, int x, int y){
        PowerUps p =null;
        switch(id){
            case 1: 
                p = new Shrink(x, y);
                break;
            case 2: 
                p = new LoseLife(x, y);
                break;
            //case 3: 
               // p = new Enemy(x, y);
              //  break;
            case 3: //work
                p = new Expand(x, y);
                break;
            case 4: //not work
                p = new LIFES(x, y);
                break;
            case 5: 
                p = new Fast(x, y);
                break;
            case 6: //work
                p = new Slow(x, y);
                break;
            case 7: 
                p = new Break(x, y);
                break;
            case 8: 
                //p = new Player(x, y);
                p = new Disrupt(x, y);
                break;
            default:
                break;                
        }
        return p;
    }
}