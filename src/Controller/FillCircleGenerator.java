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
 * @author Nanda-PC
 */

//jangan lupa import MCA disini untuk membuat lingkaran baru
//note : jika radius < 5 . tidak usah di bolongi lingkaran 

public class FillCircleGenerator {
    int width,height;
    BufferedImage Circle;
    BufferedImage Circle2;
    int x0,y0,rad;
    // BufferedImage newc ;
    
    public FillCircleGenerator(int width , int height){
       this.width = width;
       this.height= height;
       
    }

    public void setParam(int x0,int y0, int rad){      
         this.x0= x0;
         this.y0= y0;
         this.rad= rad;
    }
    
    private boolean checkRadius(){
        boolean bigRadius;
        if (rad < 7 ) {
            bigRadius= false;
        }
        else {
            bigRadius= true;
        }
        return bigRadius;
        
    }
    
    public BufferedImage fillColor(int x01, int y01, int rad1, BufferedImage CircleTemp){
        BufferedImage circleTemp = CircleTemp;
          int x = 0;
          int y = rad1;
          int a= (x01-y);
          int b= (y01-y);
         
         int FlagS[][] = new int[width][height];
          Color[][] ArrayCircle = new Color[width][height];
          for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ArrayCircle[i][j]= new Color (circleTemp.getRGB(i, j));
                    }
            }
            int startX =-(x01-y)+(x01+y);
            int startY =-(y01-y)+(y01+y);       
            
            for (int i = 0; i < width; i++) {
                 for (int j = 0; j < height; j++) {
                   Color c = ArrayCircle[i][j];
                   int r = c.getRed();
                   int bl = c.getBlue();
                   int g =c.getGreen();
                   if (r==255 && g==255 && bl==255) {
                      FlagS[i][j]=1;
                      }   
                 }
            }

           int total=0;
            for (int i =1; i <= (startX-1); i++) {
                for (int j = 0; j <= (startY); j++) {                 
                        if (FlagS[a+j][b+i]==1) {                         
                               // int u= a+j;
                                //int t= b+i;
                                 total++;
                        }                         
                    }                 
                }
            
            int[] xtot = new int [total];
            int[] ytot = new int [total];
            int c=0;
             for (int i =1; i <= (startX-1); i++) {
                for (int j = 0; j <= (startY); j++) {                 
                        if (FlagS[a+j][b+i]==1) {          
                                int u= a+j;
                                int t= b+i;
                                 xtot[c]=u;
                                 ytot[c]=t;
                                 c++;
                        }
                    }                 
              }
             
             for (int i = 0; i < total; i++) {
             //System.out.print(i+".");
             //System.out.print(xtot[i]+",");
             //System.out.print(ytot[i]+"\n");
                 if (i<total-1) {
                     if ( ( (xtot[i+1]-xtot[i]) !=1)  && (ytot[i]-ytot[i+1]==0)) {
                     int start = xtot[i];
                     int stop = xtot[i+1];
                     int ytemp=ytot[i];
                     /* System.out.print("y,"+ytot[i]+"\n");
                      System.out.print("start,"+start+"\n");
                      System.out.print("stop,"+stop+"\n\n");*/
                         do {
                             circleTemp.setRGB(start, ytemp, Color.white.getRGB());
                             start++;
                         } while (start<=stop);     
                     }
                 }              
            }
            return circleTemp;
             
    }
        
   
   
    public void eliminate2(){
        boolean   radius;
        BufferedImage circle1;

//        MidCirclePointAlgorithm MCA = new  MidCirclePointAlgorithm(width,height);
//        MCA.drawCircle(x0, y0, rad, width, height, 1);
//        circle1 = MCA.getImages();
        
        MainController controller1 = new MainController();
        controller1.StandarCircle(width, height, x0, y0, rad);
        circle1= controller1.getStandarCircle();
        
        circle1 = fillColor(x0,y0,rad,circle1);
        
        
        int newrad = rad-6;
        BufferedImage circle2;
        
//        MidCirclePointAlgorithm MCA2= new MidCirclePointAlgorithm(width,height);
//        MCA2.drawCircle(x0, y0, newrad, width, height, 1);
//        circle2 = MCA2.getImages();
        MainController controller2 = new MainController();
        controller2.StandarCircle(width, height, x0, y0, newrad);
        circle2= controller2.getStandarCircle();
        
        circle2 = fillColor(x0,y0,newrad,circle2);
       // Circle= circle2;
        
        radius= checkRadius();
        
        if (radius) {
               int flagc2[][] = new int[width][height];
               Color[][] ArrayCircle2 = new Color[width][height];
        
               for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                      ArrayCircle2[i][j]= new Color (circle2.getRGB(i, j));
                           }
                  }
               for (int i = 0; i < width; i++) {
                                 for (int j = 0; j < height; j++) {
                                     Color c = ArrayCircle2[i][j];
                                     int r  = c.getRed();
                                     int bl = c.getBlue();
                                     int g  = c.getGreen();
                                     if (r==255 && g==255 && bl==255) {
                                        flagc2[i][j]=1; //menandai lingkaran dalam
                                        }   
                           }
                 }
               for (int i = 0; i < width; i++) {
                                 for (int j = 0; j < height; j++) {
                                     if (flagc2[i][j]==1) {
                                           circle1.setRGB(i, j, Color.black.getRGB());
                                     }
                                 }
                  }
               Circle= circle1;
        }
        
        else{
            Circle = circle1;
        }  
        
    }
    
     public void justCircle(){
         BufferedImage circleTemp1=null;
         MainController controller = new MainController();
         controller.StandarCircle(width, height, x0, y0, rad);
         Circle=controller.getStandarCircle();
         Circle=circleTemp1=fillColor(x0,y0,rad,Circle); //lingkaran pertama
    }
    
    
    
    public  BufferedImage getImage1(){
        return Circle;
    }
    
    public BufferedImage getImage2(){
        
        return Circle2;
    }
}
