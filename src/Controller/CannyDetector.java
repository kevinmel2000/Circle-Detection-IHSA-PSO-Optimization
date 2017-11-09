/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.ImageStorage;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Nanda-PC
 */
public class CannyDetector {
    BufferedImage original=null;
    int LowT,ratio;
    public CannyDetector(){
        
    }
    
    public void setImage(BufferedImage originalImage ,int LowT, int ratio){
        this.original = originalImage;
        this.LowT = LowT;
        this.ratio = ratio;
    }
    
    public void processEdge(){
         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
         //File input = new File("digital_image_processing.jpg
         byte[] data = ((DataBufferByte) original.getRaster().getDataBuffer()).getData();
         Mat mat = new Mat(original.getHeight(), original.getWidth(), CvType.CV_8UC3);
         mat.put(0, 0, data);
         
         Mat mat1 = new Mat(original.getHeight(),original.getWidth(),CvType.CV_8UC1);
         Mat mat2= new Mat(original.getHeight(),original.getWidth(),CvType.CV_8UC1);
         Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);
         Imgproc.Canny(mat1, mat2, LowT, LowT*ratio, 5, true);
         

         byte[] data1 = new byte[mat2.rows() * mat2.cols() * (int)(mat2.elemSize())];
         mat2.get(0, 0, data1);
         BufferedImage image1 = new BufferedImage(mat2.cols(),mat2.rows(), BufferedImage.TYPE_BYTE_GRAY);
         image1.getRaster().setDataElements(0, 0, mat2.cols(), mat2.rows(), data1);
         ImageStorage image= new ImageStorage();
         image.setEdgeImage(image1);
    }
    
    public BufferedImage getImage(){
        return ImageStorage.getStaticEdgeImage();
    }
}
