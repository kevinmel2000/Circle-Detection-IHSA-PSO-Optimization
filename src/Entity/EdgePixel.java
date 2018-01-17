/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Nanda-PC
 */
public class EdgePixel {
    private BufferedImage sourceImage;
     int height;
     int width;
     int a,r,g,b;
    
    
    public void setImageSource(BufferedImage image){
        sourceImage= image;
    }

     public EdgePixel(){
        
    }

    public int[][] Process(int [][] edgev1){
         int sumEdge=0;
         width = sourceImage.getWidth();
         height = sourceImage.getHeight();
         int a =1;
        Color[][] colors2 = new Color[width][height];  //colors2 array simpan isi koordinat piksel tepi
 
         for (int i=0; i<width ; i++){
            for(int j=0;j<height; j++){
                colors2[i][j] = new Color(sourceImage.getRGB(i, j));
            }
        }
          for (int x=0; x < width; x++){
            for(int y=0; y < height; y++){
                Color c = colors2[x][y];
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
		if (r==255 && g==255 && b==255){
                        sumEdge = sumEdge+1;
                         edgev1[x][y]=1;//untuk menandai piksel tepi  = 1
                }
            }
        }      
        return edgev1;
    }//end
    

    public int ShowPixelEdge(int[][] edgev, int sum1,int width, int height) {
         //int sumEdge=0;
         
           for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
              if(edgev[i][j]==1){
                   sum1 = sum1+1;
                 // System.out.println(i+","+j+", piksel ke:"+sum1);
              }
            }
        }
     // System.out.println("Jumlah piksel ada ="+sum1);
     return sum1;
    }
    
  
 
}