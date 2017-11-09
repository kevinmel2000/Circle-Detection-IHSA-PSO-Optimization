/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Boundary.CircleDetectGui;
import Entity.StringStorage;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author eek
 */
public class Controller {
    BufferedImage OriginalImage;
    BufferedImage Edge;
    BufferedImage Square;
    
    BufferedImage SolidCircle;
    BufferedImage SolidCircle2;
    BufferedImage StandarCircle;
    BufferedImage SquareHoleCircle;
    
    BufferedImage FinalEdge;
    BufferedImage MultipleCircle;
    BufferedImage Circle=null;
    BufferedImage ClearImage=null;
    String SelectColor ;
  
    
    
    String[] HMbaru, HMlama;
   
    String[] Plama , Pbaru;
    
    private int width, height;

    int x0,y0,rad1;
    int kh,kw;
    boolean out=false;
    int sumEdge;
    int thick;
    float jC=0;
    int NS;
    int sumCircle;
    public Controller(){
        
    }
    
    public void setImage(BufferedImage originalImage){
        this.OriginalImage = originalImage;
        width = originalImage.getWidth();
        height = originalImage.getHeight();
    }
    
    public void MedianFilter(BufferedImage OriginalImage, int iteration){
        
        for (int i = 0; i < iteration; i++) {
            NoiseReduce reduce= new NoiseReduce();
            reduce.setImage(OriginalImage);
            reduce.medianFilter(kw, kh);
            ClearImage=reduce.getImage();
        }          
    }
    
     public void PSO(BufferedImage edge,int population){
       
        ParticleSwarmOptimization pso = new ParticleSwarmOptimization();
        pso.setEdge(edge);
        pso.Process();
        x0= pso.getx();
        y0= pso.gety();
        rad1=pso.getr();
        
        Plama=pso.getPlama();
        Pbaru=pso.getPbaru();
    }
     
    
     
     public void EdgeDetection1(int LowT,int ratio){
         CannyDetector canny = new CannyDetector();
         canny.setImage(OriginalImage, LowT, ratio);
         canny.processEdge();
         Edge=canny.getImage();
     }
    
    public void EdgeDetection(){
        //untuk deteksi tepi cany manual
        CannyEdge detector = new CannyEdge ();
        float Low = 0.5f;
        float High = 10f;
        detector.setLowThreshold(Low);
        detector.setHighThreshold(High);
        detector.setSourceImage(OriginalImage);
        detector.process();
        this.Edge = detector.getEdgesImage();  
    }
  
    public BufferedImage DrawCircle(int thick,String selectColor,int xcenter, int ycenter, int radius, BufferedImage original,int width,int height ){
        //untuk draw original+ circle image
        BufferedImage Circlex = null;
        BufferedImage t=null;
        BufferedImage circleoverlay=null; 
        MidCirclePointAlgorithm lingkaran= new MidCirclePointAlgorithm(width,height);
        lingkaran.drawCircle(xcenter, ycenter,radius, width, height,thick);
        Circlex = lingkaran.getImages();
        //Lingkaran + gambar aseli
        OverlayCircle overlay = new OverlayCircle(); 
        
        //untuk gabung antara gambar asli + lingkaran
        overlay.setImage(original, Circlex, selectColor);
        overlay.Process();
        circleoverlay= overlay.getImage();
        return circleoverlay;
    }
    
    public void HarmonySearch(boolean multiple, String algorithm,double parstatic,double bwstatic){
         ImprovedHarmonySearch ihs = new ImprovedHarmonySearch(Edge,OriginalImage,multiple,SelectColor,thick,algorithm);
         ihs.setStaticParam(parstatic, bwstatic); 
         x0 = ihs.getx0();
         y0= ihs.gety0();
         rad1= ihs.getRad();
         Circle = DrawCircle (thick,SelectColor,x0,y0,rad1,OriginalImage, OriginalImage.getWidth(), OriginalImage.getHeight());
         
        
         HMbaru= ihs.getHMbaru();
         HMlama = ihs.getHMlama();
    }

    public void DrawCircleWithEdge(BufferedImage edge1,int xcenter, int ycenter, int radius){
        
        //untuk menggambar lingkaran dengan garis tepi
         BufferedImage Circlex = null;
         BufferedImage edgeoverlay = null;
         width = edge1.getWidth();
         height= edge1.getHeight();
         MidCirclePointAlgorithm lingkaran= new MidCirclePointAlgorithm(width,height);
         lingkaran.drawCircle(xcenter, ycenter,radius, width, height,thick);
         Circlex = lingkaran.getImages();
        
         EdgeWithCircle newOverlay = new EdgeWithCircle();
         newOverlay.setImage(edge1, Circlex);
         newOverlay.process();
        
         newOverlay.Fitness();
        
        jC = newOverlay.getFitness();
        NS= newOverlay.getNS();
        sumCircle = newOverlay.getSum();
        
        edgeoverlay = newOverlay.getImage();
        Circle= edgeoverlay;      
    }
    
    
    
    
    
    public void toSquare()
    {
        //mengubah gambar lingkaran menjadi bentuk kotak
         if (Edge!=null) {
            Color Black = Color.black;
            int w = Edge.getWidth();
            int h = Edge.getHeight();
            BufferedImage temp=null;
            CircleToSquare tosquare = new CircleToSquare(w,h);
            tosquare.setParam(x0, y0, rad1);
            tosquare.processSquare();
            temp= tosquare.getImages();
            Square= temp;          
         }
         else{
            int w= width;
            int h= height;
            BufferedImage temp=null;
            CircleToSquare tosquare = new CircleToSquare(w,h);
            tosquare.setParam(x0, y0, rad1);
            tosquare.processSquare();
            temp= tosquare.getImages();
            Square= temp;      
        }    
         
    }
    
    public void toSquareCircleHole(int x0, int y0,int rad,int width, int height){
        CircleAndSquare c = new CircleAndSquare();
        c.setParam(x0, y0, rad);
        c.setSize(width, height);
        c.process();
        SquareHoleCircle=c.getSquareImage();
            
    }
    
    public void EliminatoSquare(){
        //untuk mengeliminasi gambar lingkaran tepi menjadi segi empat
        Eliminate eliminate = new Eliminate();
        eliminate.setAllImage(Edge, Square);
        eliminate.processEliminate();
        Edge= eliminate.getEliminateImage();
    }
    
    
    public void  EliminateSquareHole(){
       
        Eliminate eliminate = new Eliminate();
        eliminate.setAllImage(Edge, SquareHoleCircle);
        eliminate.processEliminate();
        Edge= eliminate.getEliminateImage();
    }
    
    
    public void StandarCircle(int width,int height, int x0, int y0, int rad){
        //MCA standar htam putih tanpa background
        MidCirclePointAlgorithm MCA = new  MidCirclePointAlgorithm(width,height);
        MCA.drawCircle(x0, y0, rad, width, height, 1);
        BufferedImage tempCircle = MCA.getImages();
        StandarCircle= tempCircle;
    }
    
    public void CircleHole(int width, int height, int x0 , int y0, int rad){
        //Circle dengan lobang
        //harus masukan parameter w,h, x0,y0,rad.
        //panggil solidcircle
        FillCircle fill = new FillCircle(width,height);
        fill.setParam(x0, y0, rad);
        fill.eliminate2(); //biar berlubang
        SolidCircle= fill.getImage1();
        
    }
    
     public void JustSolidCircle(int width, int height, int x0 , int y0, int rad){
         //Circle solid tanpa lubang
        //harus masukan parameter w,h, x0,y0,rad.
        //panggil solidcircle
        FillCircle fill = new FillCircle(width,height);
        fill.setParam(x0, y0, rad);
        fill.justCircle();
        SolidCircle2= fill.getImage1();
        
    }
    
     
    public void MultipleDetection(boolean multiple, String algorithm,double parstatic,double bwstatic){
        //ini untuk multiple detection
        
         ImprovedHarmonySearch ihs = new ImprovedHarmonySearch(Edge,OriginalImage,multiple,SelectColor,thick,algorithm);
         ihs.setStaticParam(parstatic, bwstatic); 
         x0=ihs.getx0();
         y0=ihs.gety0();
         rad1=ihs.getRad();
         MultipleCircle=ihs.getMultipleCircle();
         FinalEdge= ihs.geti();
        
    }
    
    
    public BufferedImage getFinalEdge(){
        return FinalEdge;
    }
    
    
    public BufferedImage getMultipleCircle(){
    return MultipleCircle;
    }
    
    public BufferedImage getSquareImage(){
        return Square;
    }
    
    public void setParam(int x0, int y0, int rad){
        this.x0 = x0;
        this.y0 = y0;
        this.rad1 = rad;
        
    }
   
    public float getFitness(){
       return jC;
    }
    
    public int getNS(){
        return NS;
    }
    
    public int getSum(){
        return sumCircle;
    }
    
    public BufferedImage getEdgeImage(){
        return Edge;          
    }
    
    
    public BufferedImage getClearImage(){
        return ClearImage;
    }
    
    public BufferedImage getCircleImage(){
        return Circle;
    }
    
     public BufferedImage getStandarCircle(){
         return StandarCircle;
     }
     
     public BufferedImage getHoleCircle(){
         return SolidCircle;
     }
     
     public BufferedImage getSolidCircleWithoutHole(){
         return SolidCircle2;
     }
     
     public BufferedImage getSquarewithHole(){
         return SquareHoleCircle;
     }
    
    public void setEdge(BufferedImage Edge){
        this.Edge = Edge;
    }
    
    public void setSize(int width,int height){
        this.width= width;
        this.height= height;
    }
    
    public void setOriginalImage(BufferedImage OriginalImage){
        this.OriginalImage = OriginalImage;
    }
 
    public void SetKernel(String Kernel){ 
        //set kernel
         KernelChooser k= new KernelChooser();
         k.setKernel(Kernel);
         k.Choose();
         kh=k.getKernelheight();
         kw=k.getKernelwidth();
    }
    
     public void setColor(String color){
        SelectColor = color;
    }
    
    
    public void setThick(String Thick){
        if (Thick=="1") {
            thick=1;
        }
        else if (Thick=="2") {
            thick=2;
        }
        else if (Thick=="3") {
            thick=3;
        }
        else if (Thick=="4") {
            thick=4;
        }
        else if (Thick=="5") {
            thick=5;
        }
       
    }
    
    public int getThick(){
        return thick;
    }
    
    
    public int getKh(){
        return kh;
    }
    
    public int getKw(){
        return kw;
    }
    
    public int getx0(){
        return x0;
    }
    
    public int gety0(){
        return y0;
    }
    
    public int getrad(){
        return rad1;
    }
    
    public boolean getBool(){
        return out;
    }
    
    
    public int getSumEdge(){
        return sumEdge;
    }
    
     public void setThickStatic(int Thick){
         thick= Thick;
    }
     
     public void setStaticSize(int width, int height){
         this.width= width;
         this.height= height;
     }
     
 
    
     public String[] getHMlama(){
        return StringStorage.getoldHM();
    }
    
    public String[] getHMbaru(){
        return StringStorage.getnewHM();
    }
    
    
    public String[] getNewP(){
        return StringStorage.getnewP();
    }
    
    public String[] getOldP(){
        return StringStorage.getoldP();
    }
     
    
     public static void main(String[] args) {
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CircleDetectGui.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(CircleDetectGui.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(CircleDetectGui.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(CircleDetectGui.class.getName()).log(Level.SEVERE, null, ex);
                }
                CircleDetectGui x= new CircleDetectGui();
                x.setLocationRelativeTo(null);
                x.setVisible(true);
                // gui.setLocationRelativeTo(null);
                //gui.setVisible(true);
            }
        });
    }
    
}
