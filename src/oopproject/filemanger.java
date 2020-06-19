
package oopproject;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class filemanger implements Serializable{
   public boolean write(String Query, String FilePath, boolean appendType) {

        PrintWriter writter = null;
        try {
            System.out.print("\nwritting in ! " + FilePath);

            writter = new PrintWriter(new FileWriter(new File(FilePath), appendType));
            writter.println(Query);

            System.out.println(" ... Done ! ");
            return true;
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            writter.close();
        }
        return false;
    }

    public ArrayList<player> read(String FilePath) {
        // Scanner input = new Scanner(System.in);

        Scanner Reader = null;
        try {
            System.out.println("Reading ! From " + FilePath);

            Reader = new Scanner(new File(FilePath));

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        if (FilePath.equals("player.text")) {

            ArrayList<player> p = new ArrayList<>();
            player x;
          
            while (Reader.hasNext()) {

                x = new player();
                String Line = Reader.nextLine();
                String[] seprated = Line.split("@");
                x.setPlayer_name(seprated[0]);
               x.setScore(Integer.parseInt(seprated[1]));
                p.add(x);
               
                
            }

            return  p;

        } else{
       return null;
    }}

}
