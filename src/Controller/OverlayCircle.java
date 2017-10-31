/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author eek
 */
public class OverlayCircle {
    BufferedImage OriginalImage= null;
    BufferedImage CircleImage=null;
    BufferedImage EdgeImage = null;
    BufferedImage newImage= null;
    Color white = Color.magenta;
    Color selected= null;
    String selectColor;
    int xi,yi,xj,yj,xk,yk;
    Color ci= Color.RED;
    Color newColor = Color.white;
    int width, height;
    
    public OverlayCircle(){
        
    }
    
    
    
    public void setImage(BufferedImage OriginalImage , BufferedImage CircleImage, String selectColor){
        this.OriginalImage= OriginalImage;
        this.CircleImage = CircleImage;
        this.selectColor = selectColor;
        width= OriginalImage.getWidth();
        height= OriginalImage.getHeight();
    }
    
    public void setThreeEdge(int xi, int yi, int xj,int yj, int xk, int yk){
        this.xi= xi;
        this.xk = xk;
        this.xj = xj;
        this.yi= yi;
        this.yj= yj;
        this.yk= yk;
        
    }
    
    
    public void Process(){
        
        Color[][] Original = new Color[width][height];
        if (selectColor=="RED") {
            selected =  Color.RED;
        }
        
        if (selectColor=="BLUE") {
            selected = Color.BLUE;
        }
        
        if (selectColor=="GREEN") {
            selected = Color.GREEN;
        }
       
        Color[][] Circle = new Color[width][height];
        int[][] temp = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Circle[i][j]= new Color(CircleImage.getRGB(i, j));
            }
        }
        
          for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color c = Circle [x][y];
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                if (r==255 && g==255 && b==255) { //bila tidak hitam 
                     OriginalImage.setRGB(x, y, selected.getRGB());
                }
                   
            }   
        }
          
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Original[i][j]= new Color(OriginalImage.getRGB(i, j));
            }
        }
        
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if((x==xi && y==yi) || (x==xj && y==yj)||(x==xk && y==yk) ){ 
                     OriginalImage.setRGB(x, y, white.getRGB());
                }
                   
            }   
        }
   
    }
   
  
    public BufferedImage getImage(){
        return OriginalImage;
    }
    
   
  
   
    
}
