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
public class MidCirclePointAlgorithm {
	public  BitmapStorage image;
        public   BufferedImage temp = null;
 
	public MidCirclePointAlgorithm(final int imageWidth, final int imageHeight) {
		this.image = new BitmapStorage(imageWidth, imageHeight);
	}

    MidCirclePointAlgorithm() {
        throw new UnsupportedOperationException("Tidak di Support"); 
    }
 
    public void drawCircle(int centerX, int centerY, int radius ,int imageWidth, int imageHeight,int thick) {
        
        
        
          for (int i = 0; i < thick; i++) {
		int d = (5 - radius * 4)/4;
		int x = 0;
		int y = radius;
		Color circleColor = Color.WHITE;
                
                /*int tempX= centerX + (imageWidth/2);
                centerX= tempX;
                int tempY = centerY +(imageHeight/2);
                centerY= tempY;*/
 
		do {
			image.setPixel(centerX + x, centerY + y, circleColor);
			image.setPixel(centerX + x, centerY - y, circleColor);
			image.setPixel(centerX - x, centerY + y, circleColor);
			image.setPixel(centerX - x, centerY - y, circleColor);
			image.setPixel(centerX + y, centerY + x, circleColor);
			image.setPixel(centerX + y, centerY - x, circleColor);
			image.setPixel(centerX - y, centerY + x, circleColor);
			image.setPixel(centerX - y, centerY - x, circleColor);
			if (d < 0) {
				d += 2 * x + 3;
			} else {
				d += 2 * (x - y) + 5;
				y--;
			}
			x++;
		} while (x <= y);
                radius --;
          }
 
	}
    
    
    public BufferedImage getImages(){
       temp= image.getImage();
       return temp;
        
    }
}