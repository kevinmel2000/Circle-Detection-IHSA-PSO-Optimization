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
 * @author Nanda-PC
 */
public class CircleAndSquareCombinator {
    int width,height,x0,y0,rad;
    BufferedImage Square;
    
    public CircleAndSquareCombinator(){
        
    }
    
    public void setSize(int width, int height){
       this.height = height;
       this.width = width;
    }
    
    public void setParam(int x0,int y0,int rad){
        this.x0  = x0;
        this.y0  = y0;
        this.rad = rad;
    }
     
    public void process(){
        MainController control = new MainController();
        control.setParam(x0, y0, rad);
        control.setSize(width, height);

        //bkin lingkaran solid untuk bolongi tengah dengan diameter lebih kecil dibanding kotak
        BufferedImage circle;
        int newrad= rad-5; 
        control.JustSolidCircle(width, height, x0, y0, newrad);
        circle=control.getSolidCircleWithoutHole();
        
        //buat kotak
        BufferedImage square;
        control.toSquare();
        square=control.getSquareImage();
        
        if (rad>6) {
                int[][] FlagC= new int[width][height];
                Color[][] ArrayCircle = new Color[width][height];

                for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                            ArrayCircle[i][j]= new Color (circle.getRGB(i, j));
                                }
                        }
                 for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                               Color c = ArrayCircle[i][j];
                               int r = c.getRed();
                               int b = c.getBlue();
                               int g =c.getGreen();
                               if (r==255 && g==255 && b==255) {
                                  FlagC[i][j]=1;
                                  }
                            }
                    }

                 for (int i = 0; i < width; i++) {
                     for (int j = 0; j < height; j++) {
                         if (FlagC[i][j]==1) {
                             square.setRGB(i, j, Color.black.getRGB());
                         }
                     }
                }

                 Square= square;
        }
        else{
            Square= square;
        }    
    }
    
    public BufferedImage getSquareImage(){
        ImageStorage storage = new ImageStorage();
        storage.setCircleAndSquare(Square);
        return storage.getCircleAndSquare();
       // return Square;
        
    }
    
}
