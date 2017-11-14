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
public class EdgeWithCircle {
    BufferedImage Edge= null;
    BufferedImage circle = null;
    BufferedImage newImage = null;
    
    Color White = Color.white; 
    Color Black = Color.black;
    Color Red= Color.red;
    Color Blue = Color.blue;
    
    Color[][] ArrayCircle= null;
    Color[][] ArrayEdge  = null;
    
    int [][] FlagE = null;
    int [][] FlagC = null;
    
    int width, height;
    int NS1=0;
    int sumEdgeCircle=0;
    float jC, anotherJc;
    float sumC,ns;
    
    
    public EdgeWithCircle(){
        
    }
    
    public void setImage(BufferedImage Edge , BufferedImage circle ){
        this.Edge = Edge;
        this.circle = circle;
    }
    
    
    public void process(){
         width = circle.getWidth();
         height = circle.getHeight();
        
        newImage =  new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
         FlagE = new int[width][height];
         FlagC= new int[width][height];
         ArrayCircle = new Color[width][height];
         ArrayEdge  = new Color[width][height];
        
        
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ArrayCircle[i][j] = new Color (circle.getRGB(i, j));
            }
        }
        
         for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ArrayEdge[i][j] = new Color (Edge.getRGB(i, j));
            }
        }
        
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = ArrayEdge[i][j];
                int r = c.getRed();
                int b = c.getBlue();
                int g =c.getGreen();
                if (r==255 && g==255 && b==255) {
                    FlagE[i][j]=1; // menandai koordinat piksel tepi dengan angka 1  , Flag[x][y] =1;
                }
            }
        }
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = ArrayCircle[i][j];
                int r = c.getRed();
                int b = c.getBlue();
                int g = c.getGreen();
                if (r==255 && g==255 && b==255) {
                    FlagC[i][j]=1; // menandai koordinat lingkaran dengan angka 1  , Flag[x][y] =1;
                }
            }
        }
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if ( FlagE[i][j]==1) {
                    newImage.setRGB(i, j, White.getRGB());
                }
                else if(FlagC[i][j]==1){
                    newImage.setRGB(i, j, Blue.getRGB());
                }
                else{
                    newImage.setRGB(i, j, Black.getRGB());
                }
            }
        }
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (FlagC[i][j]==1 && FlagE[i][j]==1) {
                    newImage.setRGB(i, j, Red.getRGB());
                }
            }
        }   
    }
    
    public void Eliminate (){
        //process();
        
        width = circle.getWidth();
        height = circle.getHeight();
        
        newImage =  Edge;
        
         FlagE = new int[width][height];
         FlagC= new int[width][height];
         ArrayCircle = new Color[width][height];
         ArrayEdge  = new Color[width][height];
        
        
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ArrayCircle[i][j] = new Color (circle.getRGB(i, j));
            }
        }
        
         for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ArrayEdge[i][j] = new Color (Edge.getRGB(i, j));
            }
        }
        
         for (int i = 0; i < width; i++) {
             for (int j = 0; j < height; j++) {
                Color c = ArrayCircle[i][j];
                int r = c.getRed();
                int b = c.getBlue();
                int g = c.getGreen();
                if (r==255 && g==255 && b==255) {
                    FlagC[i][j]=1; 
                }
             }
        }
         
           for (int i = 0; i < width; i++) {
             for (int j = 0; j < height; j++) {
                Color c = ArrayEdge[i][j];
                int r = c.getRed();
                int b = c.getBlue();
                int g = c.getGreen();
                if (r==255 && g==255 && b==255) {
                    FlagE[i][j]=1; 
                }
             }
        }
           
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (FlagC[i][j]==1 && FlagE[i][j]==1) {
                    newImage.setRGB(i, j, Black.getRGB());
                }
            }
        }
        
       
         
    }
    
    
    
    public void Fitness(){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (FlagC[i][j]==1) {
                    NS1++;
                }
            }
        }
        
         for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (FlagC[i][j]==1 && FlagE[i][j]==1) {
                    sumEdgeCircle++;
                }
            }
        }
        sumC = sumEdgeCircle;
        ns= NS1;
       
       jC= 1-(sumC/ns);
       anotherJc= (sumC/ns);
    
    }
    
   
    
    public BufferedImage getImage()
    {
        ImageStorage storage = new ImageStorage();
        storage.setEdgeWithCircle(newImage);
        return storage.getEdgeWithCircle();
    }
    
    public float getFitness(){
        return jC;
    }
    
    public float getF_objforGA(){
        return anotherJc;
    }
    
    public int getSum(){
        return sumEdgeCircle;
    }
    
    public int getNS(){
        return NS1;
    }
    
}
