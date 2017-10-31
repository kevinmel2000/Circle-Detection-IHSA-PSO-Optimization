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
import Entity.BitmapStorage;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author eek
 */
public class CircleToSquare {
    public  BitmapStorage image;
    public BufferedImage square ;
    public int x0,y0,rad;
    public int width , height;
   
    public CircleToSquare(final int imageWidth, final int imageHeight){
       this.image = new BitmapStorage(imageWidth, imageHeight);
       width = imageWidth;
       height= imageHeight;
    }
    
    
    
    public void setParam(int x0,int y0, int rad){
       
         this.x0= x0;
         this.y0= y0;
         this.rad= rad;
    }
    
    public void processSquare(){
        
        
         Color circleColor = Color.white;
        
          int d = (5 - rad * 4)/rad;
          int x = 0;
          int y = rad;
          int a= (x0-y);
          int b= (y0-y);
         
         
         int n= (x0+y);
         int m= (y0+y);
         
         int startX =-(x0-y)+(x0+y);
         int startY =-(y0-y)+(y0+y);
          
         /*
                image.setPixel(x0-y, y0-y, Color.red);
                image.setPixel(x0+x, y0-y, Color.BLUE);
                image.setPixel(x0+y, y0-y, Color.CYAN);

                image.setPixel(x0-y, y0+x, Color.pink);
                //null
                image.setPixel(x0+y, y0+x, Color.orange);

                image.setPixel(x0-y, y0+y, Color.GREEN);
                image.setPixel(x0+x, y0+y, Color.darkGray);
                image.setPixel(x0+y, y0+y, Color.magenta);
          */
         //System.out.print(startX+","+startY);
         
         for (int i = 0; i <= (startX); i++) {
             for (int j = 0; j <= (startY); j++) {
               image.setPixel((a+j),(b+i), Color.white);              
             }    
          
        }
       
         
        
    }
    
     public BufferedImage getImages(){
       processSquare();
       square= image.getImage();
       return square;
        
    }
    
}