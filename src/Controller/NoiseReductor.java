/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.ImageStorage;
import java.awt.image.BufferedImage;

import java.util.Arrays;

/**
 *
 * @author Nanda-PC
 */
public class NoiseReductor {
     BufferedImage img=null;
     BufferedImage gray=null;
    public NoiseReductor(){
        
    }
    
    public void setImage(BufferedImage originalImage){
        img=originalImage;
    }
       
    public void toGrayscale(){
         int width = img.getWidth();
         int height = img.getHeight();

        
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int p = img.getRGB(x,y);
                int a = (p>>24)&0xff;
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;
             
                int avg = (r+g+b)/3;
              
                p = (a<<24) | (avg<<16) | (avg<<8) | avg;
                img.setRGB(x, y, p);
            }              
        gray= img;
        }     
    }
    
    
    
    public void medianFilter(int kernelWidth, int kernelHeight)
    {
        toGrayscale();
        int[] kernel = new int [kernelWidth*kernelHeight];
        
        int r =0;
        for (int x = (kernelWidth / 2); x < img.getWidth()-(kernelWidth / 2); x++)
        {
            for (int y = (kernelWidth / 2); y < img.getHeight()-(kernelWidth / 2); y++)
            {              
                int k=0;
                for (int i = x - (kernelWidth / 2); i <= x + (kernelWidth / 2); i++)
                {
                    for (int j = y - (kernelHeight / 2); j <= y + (kernelHeight / 2); j++)
                    {
                        int gray = img.getRGB(i, j)& 0xFF;
                        kernel[k] = gray;
                        k++;
                    }
                }                
                Arrays.sort(kernel);         
                int medianIndex = ((kernelWidth * kernelHeight) )/2; 
                int selectedGray = (kernel[medianIndex] << 16) + (kernel[medianIndex] << 8) + kernel[medianIndex];                
                img.setRGB(x, y, selectedGray);                
                r++;
            }
            int process = r*100/((img.getWidth()-(kernelWidth / 2))*(img.getHeight()-(kernelWidth / 2)))+1;
        }
       ImageStorage noiseReduce = new ImageStorage();
       noiseReduce.setNoiseReduceImage(img);
    }
    

    public BufferedImage getImage(){
        return ImageStorage.getNoiseReduceImage();
    }
    
}
