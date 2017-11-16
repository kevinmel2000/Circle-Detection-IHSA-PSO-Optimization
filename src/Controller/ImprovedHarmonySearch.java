/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.EdgePixel;
import Entity.PixelVector;
import Entity.HarmonyMemory;
import Entity.Parameter;
import Entity.StringStorage;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author eek
 */
public final class ImprovedHarmonySearch {
    
    private int hmSize;
    private int cycles;
    private double hmConsiderationRate;
    private double bwMin;
    private double bwMaks;
    private double parMin;
    private double parMaks;
    
    int newx, newy, newrad;
    
    private double parstatic;
    private double bwstatic;
    
    
    private HarmonyMemory[] harmonyMemory;
  
    
    
    private int decTotal;
    
    BufferedImage newc= null;
    BufferedImage Edge=null;

    private int sumEdge;
    private int width;
    private int height;
    private PixelVector[] P;
    private PixelVector[] Pijk;
    private int x0;
    private int y0;
    private int rad1;
    private boolean out;
    private int x02;
    private int y02;
    private int rad21;
    private int bestx0,besty0,bestrad;
    private double par,bw;
    public BufferedImage e=null;
    public boolean cir;
    private String algo=null;
    
    public String[] HMlama;
    public String[] HMbaru;
    public String parameterAkhir;
    
    int xtemp, ytemp,rtemp;
    
    public ImprovedHarmonySearch(BufferedImage Edge,BufferedImage Original, boolean multiple,String CircleColor,int Thick, String Algo){
        if (multiple==false){ //detect single circle
                algo=Algo;
                this.Edge= Edge;
                this.width = Edge.getWidth();
                this.height = Edge.getHeight();
                initializeProblemAndParameter();
                initializeHarmonyMemory();
                EvaluateFitness();

                HMlama= new String[hmSize];
                HMbaru = new String [hmSize];
                for (int i = 0; i < hmSize; i++) {
                  
                    int xi= harmonyMemory[i].getPixelVectorX(0);
                    int xj= harmonyMemory[i].getPixelVectorX(1);
                    int xk= harmonyMemory[i].getPixelVectorX(2);

                    int yi= harmonyMemory[i].getPixelVectorX(0);
                    int yj= harmonyMemory[i].getPixelVectorX(1);
                    int yk= harmonyMemory[i].getPixelVectorX(2);

                    int x0=  harmonyMemory[i].getSolutionVector(0);
                    int y0=  harmonyMemory[i].getSolutionVector(1);
                    int rad= harmonyMemory[i].getSolutionVector(2);
                    double fit= harmonyMemory[i].getFitness();
                    
                    HMlama[i]= "HM lama ["+i+"] yi:"+yi+",yj: "+yj+", yk: "+yk+" x0: "+x0+",y0: "+y0+"  rad:"+rad+ " f(x): "+fit+"\n" ;
                   
               }
                
                
                improviseNewHarmony();
                
               
 
                for (int i = 0; i < hmSize; i++) {
                   
                    int xi= harmonyMemory[i].getPixelVectorX(0);
                    int xj= harmonyMemory[i].getPixelVectorX(1);
                    int xk= harmonyMemory[i].getPixelVectorX(2);

                    int yi= harmonyMemory[i].getPixelVectorX(0);
                    int yj= harmonyMemory[i].getPixelVectorX(1);
                    int yk= harmonyMemory[i].getPixelVectorX(2);

                    int x0=  harmonyMemory[i].getSolutionVector(0);
                    int y0=  harmonyMemory[i].getSolutionVector(1);
                    int rad= harmonyMemory[i].getSolutionVector(2);
                    double fit= harmonyMemory[i].getFitness();
                    
                    HMbaru[i]= "HM baru ["+i+"] yi:"+yi+",yj: "+yj+", yk: "+yk+" x0: "+x0+",y0: "+y0+"  rad:"+rad+ " f(x): "+fit+"\n" ;
                   
               }

                int best[]= new int [decTotal];
                best = getSolution();
                bestrad=best[2];
                besty0=best[1];
                bestx0=best[0]; 
                
                parameterAkhir="HM akhir; rad= "+bestrad+",x0="+bestx0+",y0="+besty0; 
                
                StringStorage stringstorage = new StringStorage();
                stringstorage.setStringIHS(HMlama, HMbaru, hmSize);
                
        }
        else{//detect Multiple Circle
              
                algo=Algo;
                this.Edge= Edge;
                this.width = Edge.getWidth();
                this.height = Edge.getHeight();
                
                    initializeProblemAndParameter();
                    initializeHarmonyMemory();
                    EvaluateFitness();
                    improviseNewHarmony();

                    int best[]= new int [decTotal];
                    best = getSolution();
                    bestrad=best[2];
                    besty0=best[1];
                    bestx0=best[0]; 
                    
                    
                    Controller controller = new Controller();
                    Controller fornewc = new Controller();
                    //System.out.print(Thick +","+CircleColor);
                    //untuk menggambar lingkaran 
                    newc= fornewc.DrawCircle(Thick, CircleColor, bestx0, besty0, bestrad, Original, width, height);
                    //untuk eliminasi 
                    controller.setEdge(Edge);     
                    controller.setParam(bestx0, besty0, bestrad);
                    
//                    controller.toSquare(); //untuk kotak
//                    controller.EliminatoSquare();

                    //untuk eliminasi kotak lingkaran didalam
                    controller.toSquareCircleHole(bestx0, besty0, bestrad, width, height);
                    controller.EliminateSquareHole();
                  
                    Edge= controller.getEdgeImage();      
                    e=Edge;
            
        }
 
    }
    
   
   
   

    
    public void setStaticParam(double parstatic, double bwstatic){
        this.parstatic = parstatic;
        this.bwstatic = bwstatic;
    }
    
    public void setAlgorithm(String Algorithm){
        algo = Algorithm;
    }

    
    public boolean getBool(){
        return cir;
    }
    
    public BufferedImage geti(){
        return e;
    }
    
    public BufferedImage getMultipleCircle(){
        return newc;
    }
    
    public int getRad(){
        return bestrad;
    }
    
    public int getx0(){
        return bestx0;
    }
    
    public int gety0(){
        return besty0;
    }
    
    public HarmonyMemory[] gethm(){
        return harmonyMemory;
    }
    
    public String lastparam(){
        return parameterAkhir;
    }
    
  private void initializeProblemAndParameter(){
        decTotal =3; 
        hmSize = Parameter.getHarmonyMemorySize();
        cycles = Parameter.getCycles();
        hmConsiderationRate = Parameter.getHMConsiderationRate();
        bwMin = Parameter.getBandwithMin();
        bwMaks = Parameter.getBandwithMax();
        parMin = Parameter.getPitchAdjustmentRateMin();
        parMaks = Parameter.getPitchAdjustmentRateMax();
         
     }
     
  private void initializeHarmonyMemory(){
        harmonyMemory = new HarmonyMemory[hmSize]; //INI Ca
        
        int[][] edgev2; 
        sumEdge=0;
        int a=1;
        int zero=0;
        int coorx = 0,coory = 0;
        edgev2 = new int [width][height];
        int h=0;
        EdgePixel ep = new EdgePixel();
        ep.setImageSource(Edge);
        ep.Process(edgev2);
        sumEdge= ep.ShowPixelEdge(edgev2, sumEdge, width, height); 
        
          for (int i = 0; i < sumEdge; i++) {
              
          }
        
        
        P = new PixelVector[sumEdge]; //vector P 

        Pijk = new PixelVector[3];
        
        int minValue=0; //0 
        int maxValue= sumEdge;
        
        for (int i=0;i<sumEdge;i++){
                    P[i]= new PixelVector(a);
                }
        for (int i = 0; i < decTotal; i++) {
                    Pijk[i]= new PixelVector(a);
                }
          for (int i = 0; i < hmSize; i++) {
              harmonyMemory[i] = new HarmonyMemory(decTotal);
          }
        
        //MEMINDAHKAN NILAI ARRAY edgev2[][] ke dalam vector koordinat P[]->coor[x][y]
        for (int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                if(edgev2[x][y]==1){
                    if (h<sumEdge){
                        P[h].setCoor(x, y, zero);
                         coorx=P[h].getCoorx(zero);
                         coory=P[h].getCoory(zero);      
                    }
                    h++;                    
                } 
            }   
        }  
        
        
          for (int i = 0; i < hmSize; i++) {
              //RandomThreePixel(maxValue,minValue,zero,i);
              RandomThreePixel p = new RandomThreePixel(P,Pijk);
              p.Process(maxValue, minValue, zero, height, width);
              x0  =p.getx0();
              y0  =p.gety0();
              rad1=p.getrad();
              int xi,xj,xk,yi,yj,yk;
              xi= p.getxi();
              xj= p.getxj();
              xk= p.getxk();
              
              yi= p.getyi();
              yj= p.getyj();
              yk= p.getyk();
              
              harmonyMemory[i].setSolutionVector(0, x0); //untuk masukin x0;
              harmonyMemory[i].setSolutionVector(1, y0); //untuk masukin y0;
              harmonyMemory[i].setSolutionVector(2, rad1);//masukin radius;
              
              harmonyMemory[i].setX(xi, xj, xk);
              harmonyMemory[i].setY(yi, yj, yk);
          } 
      }
      
      
  private void EvaluateFitness(){
          int xc,yc,rc;
          int thick =1; // THICKK
          
          for (int i = 0; i < hmSize; i++) {
              int NS,sumCircle;
              float jC;
              xc= harmonyMemory[i].getSolutionVector(0);
              yc= harmonyMemory[i].getSolutionVector(1);
              rc= harmonyMemory[i].getSolutionVector(2);
              
              BufferedImage Circlex = null;
              BufferedImage edgeoverlay = null;
           
              MidCirclePointAlgorithm lingkaran= new MidCirclePointAlgorithm(width,height);
              
              lingkaran.drawCircle(xc, yc,rc, width, height,thick);
              Circlex = lingkaran.getImages();
              EdgeWithCircle newOverlay = new EdgeWithCircle();
              
              newOverlay.setImage(Edge, Circlex);
              newOverlay.process();         
              newOverlay.Fitness();
              jC = newOverlay.getFitness();
              harmonyMemory[i].setFitness(jC);
          }    
          
       
      }
      
    
      
      
  private void improviseNewHarmony(){
        int generation = 0;
        double random;
        int D1; //index pemilihan lokasi pada harmoni memori secara random
        int D2X;
        int D2Y;//nilai variabel keputusan dari vektor solusi
        int D3X;
        int D3Y;//nilai variable keputusan berdasarkan PAR dan BW
        int randomDecisionVariableX;
        int randomDecisionVariableY;
        
        
        
        do{
            HarmonyMemory newHarmonyMemoryVector = new HarmonyMemory(decTotal);
            Randomizer rand= new Randomizer();
            //random dec variable
            rand.SetRandom(0, hmSize-1);
            int randomNumb= rand.GetRandom();
            int xcenterRand = harmonyMemory[randomNumb].getSolutionVector(0);
            int ycenterRand = harmonyMemory[randomNumb].getSolutionVector(1);
            int radiusRand  = harmonyMemory[randomNumb].getSolutionVector(2);
            double fitnessRand = harmonyMemory[randomNumb].getFitness();
            
            newHarmonyMemoryVector.setSolutionVector(0, xcenterRand);
            newHarmonyMemoryVector.setSolutionVector(1, ycenterRand);
            newHarmonyMemoryVector.setSolutionVector(2, radiusRand);
            newHarmonyMemoryVector.setFitness(fitnessRand);
            
            //set par dan bw
            if (algo=="HSA") { //untuk HSA
                par = parstatic;
                bw = bwstatic;    
            }
            
            
            else if (algo=="IHS") { //untuk IHS
                 par = getPAR(generation);
                 bw =  getBW(generation);
            }
           
           
            
             boolean out        =false;
             
            //akan diulang selama nilai fitness out of bound
    
                    do{
                            for(int i=0; i<decTotal; i++)
                            {
                                random = randProbability(0,1);

                                if (random < hmConsiderationRate)
                                {

                                    D1 = (int) random * hmSize; //pemilihan index pada HM berdasarkan hmcr

                                    random = randProbability(0,1);

                                    if(random < par){ 

                                                   random = randProbability(0,1);
                                                   D2X = newHarmonyMemoryVector.getPixelVectorX(i);
                                                   D2Y = newHarmonyMemoryVector.getPixelVectorY(i);

                                                   D3X = adjustedDecisionVariable(random,D2X,i);
                                                   D3Y = adjustedDecisionVariable(random,D2Y,i);

                                                   newHarmonyMemoryVector.setXindex(i, D3X);
                                                   newHarmonyMemoryVector.setYindex(i, D3Y);
                                       }
                                    else{

                                          D2Y = harmonyMemory[D1].getPixelVectorY(i);
                                          D2X = harmonyMemory[D1].getPixelVectorX(i);
                                           //simpan ke harmony baru (sementara) utk dibandingkan    
                                          newHarmonyMemoryVector.setXindex(i, D2X);
                                          newHarmonyMemoryVector.setYindex(i, D2Y);
                                        }      
                                }
                                 else {   

                                         randomDecisionVariableX = randNewDecisionVariable(0); 
                                         randomDecisionVariableY = randNewDecisionVariable(1);       
                                         newHarmonyMemoryVector.setXindex(i, randomDecisionVariableX);
                                         newHarmonyMemoryVector.setYindex(i, randomDecisionVariableY);
                                }  
                            }
                            int xi,xj,xk,yi,yj,yk;
                            int x0,y0,rad;
                            xi= newHarmonyMemoryVector.getPixelVectorX(0);
                            xj= newHarmonyMemoryVector.getPixelVectorX(1);
                            xk= newHarmonyMemoryVector.getPixelVectorX(2);

                            //y
                            yi= newHarmonyMemoryVector.getPixelVectorY(0);
                            yj= newHarmonyMemoryVector.getPixelVectorY(1);
                            yk= newHarmonyMemoryVector.getPixelVectorY(2);

                            Determinan det = new Determinan();
                            det.setDeterminanx(xi, xj, xk);
                            det.setDeterminany(yi, yj, yk);
                            det.prosesDeterminan();
                            xtemp = (int) det.getRad1();
                            ytemp = (int) det.getx0();
                            rtemp =(int) det.gety0();


                            CheckBoundary check = new CheckBoundary();
                            check.setItem(xtemp, ytemp, rtemp, height, width);
                            check.checking();
                            out= check.getBool();
                    }while(out==true); 
                    
                    
            int x0,y0,rad;
            int xi,xj,xk,yi,yj,yk;
            xi= newHarmonyMemoryVector.getPixelVectorX(0);
            xj= newHarmonyMemoryVector.getPixelVectorX(1);
            xk= newHarmonyMemoryVector.getPixelVectorX(2);

                    //y
            yi= newHarmonyMemoryVector.getPixelVectorY(0);
            yj= newHarmonyMemoryVector.getPixelVectorY(1);
            yk= newHarmonyMemoryVector.getPixelVectorY(2);
               
            double fit;
            x0  = newHarmonyMemoryVector.getSolutionVector(0);
            y0  = newHarmonyMemoryVector.getSolutionVector(1);
            rad = newHarmonyMemoryVector.getSolutionVector(2);
            
            //hitung fobj . pad new HM
            fit= calculateFitness(x0,y0,rad);
            newHarmonyMemoryVector.setFitness(fit);
            
//               System.out.print("iterasi ke= "+generation+"\n");
//               System.out.print("NHV xi: "+xi+",xj: "+xj+", xk: "+xk+"\n");
//               System.out.print("    yi: "+yi+",yj: "+yj+", yk: "+yk+"\n");
//               System.out.print("    x0: "+x0+",y0: "+y0+"  rad:"+rad+"\n");
//               System.out.print("f(x):"+fit+"\n");
//               System.out.print("=========================================\n");
            
            
            int indexOfWorstHarmony = getIndexOfWorstHarmony(harmonyMemory);
            double newHarmonySolution = newHarmonyMemoryVector.getFitness();
            double worstHarmonySolution = harmonyMemory[indexOfWorstHarmony].getFitness();
            
//            System.out.print("new : "+newHarmonySolution+", worst : "+worstHarmonySolution+"\n");
            
            
            if(newHarmonySolution < worstHarmonySolution)
            {
//                System.out.print("HM baru lebih baik dari pada yang buruk\n");
//                System.out.print("hm  buruk lama   dengan fx = "+worstHarmonySolution+"\n");
//                System.out.print("hm  baru  dengan fx        = "+newHarmonySolution+"\n");
                harmonyMemory[indexOfWorstHarmony] = newHarmonyMemoryVector;    
            }
            
            
            generation++;
        }while(generation < cycles);

        
      }
      
 
      
  private double calculateFitness(int x0, int y0, int rad){
      double value=0;
      
      int NS,sumCircle;
      float jC;

      BufferedImage Circlex = null;
      BufferedImage edgeoverlay = null;

      MidCirclePointAlgorithm lingkaran= new MidCirclePointAlgorithm(width,height);

      lingkaran.drawCircle(x0, y0,rad, width, height,1);
      Circlex = lingkaran.getImages();
      EdgeWithCircle newOverlay = new EdgeWithCircle();

      newOverlay.setImage(Edge, Circlex);
      newOverlay.process();         
      newOverlay.Fitness();
      jC = newOverlay.getFitness();            
      value=jC;   

      return value;
      
  }
  
  private int randNewDecisionVariable(int index){
     //ngerandom nilai piksel tepi.
     int value=0;
     int maxValue=sumEdge;
     int minValue=0;
     Randomizer rand = new Randomizer();
     int x,y;
     int zero=0;
     rand.SetRandom(maxValue, minValue);
     int ran = rand.GetRandom();
     x=P[ran].getCoorx(zero);
     y=P[ran].getCoory(zero);       
          if (index==0) {
              value=x;
          }
          else{
              value=y;
          }            
        return value;
  }
  
  public int[] getSolution()
    {
        int[] solution = new int[decTotal];
        for(int i=0; i<decTotal; i++)
        {
            solution[i] = harmonyMemory[getIndexOfBestHarmony(harmonyMemory)].getSolutionVector(i);
        }
        return solution;
    }
  
  public double getFitness(){
      double fitness;
        fitness = harmonyMemory[getIndexOfBestHarmony(harmonyMemory)].getFitness();
      return fitness;
  }
      
  private double randProbability(int min, int max)
    {
        Random random = new Random();
        double value = min + (max - min) * random.nextDouble();    
        
        return value;
    }
      
  private int getIndexOfWorstHarmony(HarmonyMemory[] harmonyMemory)
      {
        int index;
        double worst,possibleWorst;
        
        worst = harmonyMemory[0].getFitness();
        index = 0;
        for(int i=1; i<hmSize; i++)
        {
            possibleWorst = harmonyMemory[i].getFitness();
            
            if(possibleWorst >= worst)
            {
                worst = possibleWorst;
                index = i;
            }
        }
                
        return index;
      }
       
  public int getIndexOfBestHarmony(HarmonyMemory[] harmonyMemory)
      {
        int index;
        double best,possibleBest;
        
        best = harmonyMemory[0].getFitness();
        index = 0;
        for(int i=1; i<hmSize; i++)
        {
            possibleBest = harmonyMemory[i].getFitness();
            
            if(possibleBest <= best)
            {
                best = possibleBest;
                index = i;
                
            }
        }
                
        return index;
       
    }
       
  private double getPAR(int generation)
    {
        double PAR = parMin + ((parMaks - parMin)/cycles) * generation;
        
        return PAR;
    }
    
  private double getBW(int generation)
    {
        double c,BW; 
        
        c = Math.log(bwMin/bwMaks) / cycles;
        BW = bwMaks*Math.exp(c*generation);
        
        return BW;
        
    }
    
    
  private int adjustedDecisionVariable(double random, int D2, int i)
    {
        int min= 1; // nilai mininim piksel edge
        int max= sumEdge; //nilai maksimum piksel ede
        int D3;
        if(random < 0.5d){
            D3 = D2 + (int)(random * bw);
        }
        else{
            D3 = D2 - (int)(random * bw);
        }
        
        
        
        if (D3 < min){
            int maxValue=sumEdge;
            int minValue=0;
            Randomizer rand = new Randomizer();
            int x,y;
            int zero=0;
            rand.SetRandom(maxValue, minValue);
            int ran = rand.GetRandom();
            x=P[ran].getCoorx(zero);
            y=P[ran].getCoory(zero);       
                 if (i==0) {
                     D3=x;
                 }
                 else{
                     D3=y;
                 }            
               return D3;
        }
        
        
        if (D3 > sumEdge ){
            int maxValue=sumEdge;
            int minValue=0;
            Randomizer rand = new Randomizer();
            int x,y;
            int zero=0;
            rand.SetRandom(maxValue, minValue);
            int ran = rand.GetRandom();
            x=P[ran].getCoorx(zero);
            y=P[ran].getCoory(zero);       
                 if (i==0) {
                     D3=x;
                 }
                 else{
                     D3=y;
                 }            
               return D3;
        }
        
      
        return D3;
    }
    
   

}


