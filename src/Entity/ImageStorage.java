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
    
    public ImageStorage(){
         
    }
    
    public void setEdgeImage(BufferedImage image){
        this.Edgeimage = image;
        staticEdge= image;
    }
    
    public void setNoiseReduceImage(BufferedImage nr){
        this.NoiseImage= nr;
        staticNoise= nr;
    }
   
    public static BufferedImage getStaticEdgeImage(){
        return staticEdge;
    }
    
     public static BufferedImage getNoiseReduceImage(){
        return staticNoise;
    }
    
 
}
