/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

 
 
import java.util.Random ;

/**
 *
 * @author Nanda-PC
 */
public class Randomizer {
    int max;
    int min;
    int value;
    public Randomizer(){
        
    }
    
    public void SetRandom(int max,int min){
        this.max = max;
        this.min = min;
    }
    
    public int GetRandom(){
        max = --max;
         value= (int) Math.floor(Math.random()*(max-min+1)) + min;
        return value;
    }
    
    
}
