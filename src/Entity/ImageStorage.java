/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.awt.image.BufferedImage;

/**
 *
 * @author Nanda-PC
 */
public class ImageStorage {
    BufferedImage Edgeimage;
    static BufferedImage staticEdge;
    
    BufferedImage NoiseImage;
    static BufferedImage staticNoise;
    
    BufferedImage EdgeWithCircle;
    static BufferedImage staticEdgeWithCircle;
    
    BufferedImage Eliminate;
    static BufferedImage staticEliminate;
    
    BufferedImage CircleAndSquare;
    static BufferedImage staticCircleAndSquare;
    
    BufferedImage CircleToSquare;
    static BufferedImage staticCircleToSquare;
    
    public ImageStorage(){
         
    }
    
    //setter
    public void setCircleToSquare(BufferedImage image){
        this.CircleToSquare=image;
        staticCircleToSquare = image;
    }
    
    public void setCircleAndSquare(BufferedImage image){
        this.CircleAndSquare= image;
        staticCircleAndSquare = image;
    }
    
    public void setEliminate(BufferedImage image){
        this.Eliminate = image;
        staticEliminate = image;
    }
    
    public void setEdgeImage(BufferedImage image){
        this.Edgeimage = image;
        staticEdge= image;
    }
    
    public void setNoiseReduceImage(BufferedImage image){
        this.NoiseImage= image;
        staticNoise= image;
    }
    
    public void setEdgeWithCircle(BufferedImage image){
        this.EdgeWithCircle= image;
        staticEdgeWithCircle= image;
    }
    
    //getter
    
    public  BufferedImage getStaticEliminate(){
        return staticEliminate;
    }
    
    public  BufferedImage getEdgeWithCircle(){
        return staticEdgeWithCircle;
    }
    
    public BufferedImage getCircleToSquare(){
        return staticCircleToSquare;
    }
    
    public BufferedImage getCircleAndSquare(){
        return staticCircleAndSquare;
    }
    
    public static BufferedImage getStaticEdgeImage(){
        return staticEdge;
    }
    
     public static BufferedImage getNoiseReduceImage(){
        return staticNoise;
    }
    
     
      
     
   
      
    
 
}
