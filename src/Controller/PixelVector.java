/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author eek
 */
public class PixelVector {
    int [][]coor;
    
    public PixelVector(int a){
        coor = new int [a][a+1];
    }
    
    public void setCoor(int x,int y, int zero)
    {
        coor[zero][zero]=x;
        coor[zero][zero+1]=y;
    }
    
    public int getCoorx(int zero){
        return coor[zero][zero];
    }
    
   public int getCoory(int zero){
       return coor[zero][zero+1];
       
   }
    
}
