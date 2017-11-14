/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.ImageStorage;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author eek
 */
public class Eliminate {
    
    BufferedImage Edge;
    BufferedImage Square;
    BufferedImage newImage;
    
    public Eliminate(){
        
    }
    
    public void setAllImage(BufferedImage Edge,BufferedImage Square){
        this.Edge= Edge;
        this.Square = Square;
    }
    
    
    
    public void processEliminate(){
        Color Black = Color.black;
        Color White = Color.white;
        int width = Edge.getWidth();
        int height= Edge.getHeight();
        
        newImage =  new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        int FlagS[][] = new int[width][height];
        int FlagE[][] = new int[width][height];
        
        Color ArraySquare[][] = new Color[width][height];
         Color ArrayEdge[][] = new Color[width][height];
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ArraySquare[i][j]= new Color (Square.getRGB(i, j));
            }
        }
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ArrayEdge[i][j]= new Color (Edge.getRGB(i, j));
            }
        }
        
       for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = ArraySquare[i][j];
                int r = c.getRed();
                int b = c.getBlue();
                int g =c.getGreen();
                if (r==255 && g==255 && b==255) {
                    FlagS[i][j]=1; 
                }
            }
        }
       
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = ArrayEdge[i][j];
                int r = c.getRed();
                int b = c.getBlue();
                int g =c.getGreen();
                if (r==255 && g==255 && b==255) {
                    FlagE[i][j]=1; 
                }
            }
        }
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (FlagS[i][j]==1) {
                    newImage.setRGB(i, j, Black.getRGB());
                }
                else if (FlagE[i][j]==1 && FlagS[i][j]==1) {
                      newImage.setRGB(i, j, Black.getRGB());
                }
                else if (FlagE[i][j]==1){
                    newImage.setRGB(i, j, White.getRGB());
                }
            }
        }

        
    }
    
    
    public BufferedImage getEliminateImage(){
        ImageStorage storage = new ImageStorage();
        storage.setEliminate(newImage);
        return storage.getStaticEliminate();
        //return newImage;
    }
    
}
