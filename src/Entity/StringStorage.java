/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Nanda-PC
 */
public class StringStorage {
    public static String[] HMlama;
    public static String[] HMbaru;
    public static int HMS;
    
    
    public static String[] Plama;
    public static String[] Pbaru;
    public static int population;
    
    public StringStorage(){
        
    }
    
    public void setStringIHS (String[] Hmlama , String[] Hmbaru, int HMS){
        HMlama = new String[HMS];
        HMbaru = new String [HMS];
        
        this.HMlama = Hmlama;
        this.HMbaru = Hmbaru;
        this.HMS = HMS;
    }
    
    
    public void setStringPSO(String[] Plama, String[] Pbaru, int population){
        this.Plama = new String[population];
        this.Pbaru = new String[population];
        
        this.Plama= Plama;
        this.Pbaru = Pbaru;
    }
    
    public static String[] getoldHM(){
        return HMlama;
    }
    
    public static String[] getnewHM(){
        return HMbaru;
    }
    
    public static String[] getoldP(){
        return Plama;
    }
    
    public static String[] getnewP(){
        return Pbaru;
    }
   
    
   
}
