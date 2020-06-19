
package oopproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.File;
import java.util.Collections;


public class player implements Comparable<player>{
    String player_name;
    public  int score;//=0
    static int high_score;
    static String high_name;
    public final String filename = "player.text";
  public static ArrayList<player>players = new ArrayList<>();

filemanger fm = new filemanger();

    public player() {
       this.score= 0;
    }

    public player(String player_name, int score) {
        this.player_name = player_name;
        this.score = score;
        
    }
    public void  loader(){
     loadFromFile();
     
     Collections.sort(players);
 }
    public boolean add(){
    if (fm.write(getda(), filename, true)) {
            return true;
        } else {
            return false;
        }
    
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public void commitToFile() {
        fm.write(players.get(0).getda(), filename, false);
        for (int i = 1; i < players.size(); i++) {
            fm.write(players.get(i).getda(), filename, true);
        }
       
    }
 private void loadFromFile() {
         player.players =  fm.read(filename);
    }
public  String getda(){
return this.player_name+"@"+this.score+"@";
}
   
    public boolean update(String name,int nscore){
        loadFromFile();
      for(player x :players){
            
            if(x.player_name.equals(name)){
            
             x.score=nscore; 
            commitToFile();
            return true;
            }
        }
        return false;
    }

    /**
     *
     */
    
    public void highest(){
    File logic = new File(filename);
    if (logic.exists()){
        loader();
        System.out.println(players.size());
    }
    }

    public String getPlayer_name() {
        return player_name;
    }

    public  int getScore() {
        return score;
    }

    

    @Override
    public int compareTo(player o) {
        return o.score - this.score;
    }
    

}
