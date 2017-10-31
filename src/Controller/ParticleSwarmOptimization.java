/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Parameter_PSO;
import Entity.Particle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Nanda-PC
 */
public class ParticleSwarmOptimization {
    int iteration,population, nSolution, vmax;
    int target=0;
    int sumEdge;
    int currentBestIndex;
    int width,height;
    int x0,y0,rad;
    Particle[] Particle;
    Particle[] aParticle;
    Particle[] bestParticle;
    PixelVector[] P;
    PixelVector[] Pijk;
    
    public String[] Plama;
    public String[] Pbaru;
    
    int bestIndex;
    
    BufferedImage Edge=null;
    
    public ParticleSwarmOptimization(){
        
    }
    
    public void setEdge(BufferedImage edge ){
        width= edge.getWidth();
        height= edge.getHeight();
        Edge= edge;
    }
    
    
  public void setParam( ){
        this.iteration  = Parameter_PSO.getIteration();
        this.population = Parameter_PSO.getPopulation();
        this.nSolution  = 3;
        this.vmax       = Parameter_PSO.getVelocityMax();
     
    }
  
  public void Process(){
     
      setParam();
      
      int gBest = 0;
      int gBestTest = 0;
      int epoch = 0;
   
      aParticle = new Particle[1];
      aParticle[0] = new Particle(nSolution);
        
        
      Initialization();
      EvaluateFitness();
      
      Plama = new String[population];
      Pbaru = new String [population];
      
      for (int i = 0; i < population; i++) {
                    int xi= Particle[i].getPixelVectorX(0);
                    int xj= Particle[i].getPixelVectorX(1);
                    int xk= Particle[i].getPixelVectorX(2);

                    int yi= Particle[i].getPixelVectorX(0);
                    int yj= Particle[i].getPixelVectorX(1);
                    int yk= Particle[i].getPixelVectorX(2);

                    int x0=  Particle[i].getSolutionVector(0);
                    int y0=  Particle[i].getSolutionVector(1);
                    int rad= Particle[i].getSolutionVector(2);
                    double fit= Particle[i].getFitness();
                    Plama[i]= "P lama ["+i+"] yi:"+yi+",yj: "+yj+", yk: "+yk+" x0: "+x0+",y0: "+y0+"  rad:"+rad+ " f(x): "+fit+"\n" ;
      }
      
      do{
           
             
            for (int j = 0; j < nSolution; j++) {
                 int fitness = Particle[gBest].getParticleVector(j);
                 aParticle[0].setParticle(j, fitness);  
                }
                gBestTest = minimum();
                aParticle[0]= Particle[gBest];

               System.out.print("gbest"+gBest+", gbesttest"+gBestTest);
            if(testProblem(gBestTest) < testProblem(gBest) ){
                 gBest = gBestTest;
                }
    
            System.out.print("ini adalah iterasi ke : "+epoch +"\n");
            for (int i = 0; i < population; i++) {
                  System.out.print("P["+i+"]");
                  for (int j = 0; j < nSolution; j++) {
//                    System.out.print(Particle[i].getParticleVector(j)+" ");
                  }
                  Particle[i].setFitness(testProblem(i));
                  double fit= Particle[i].getFitness(); 
                  System.out.print("f(x)= " + fit);
                  System.out.print('\n');
            }                       
          
            getVelocity(gBest);
            updateparticles(gBest);
            bestIndex = gBest;
         
             epoch++;
      }while(epoch<=iteration);
     
      for (int i = 0; i < population; i++) {
                    int xi= Particle[i].getPixelVectorX(0);
                    int xj= Particle[i].getPixelVectorX(1);
                    int xk= Particle[i].getPixelVectorX(2);

                    int yi= Particle[i].getPixelVectorX(0);
                    int yj= Particle[i].getPixelVectorX(1);
                    int yk= Particle[i].getPixelVectorX(2);

                    int x0=  Particle[i].getSolutionVector(0);
                    int y0=  Particle[i].getSolutionVector(1);
                    int rad= Particle[i].getSolutionVector(2);
                    double fit= Particle[i].getFitness();
                    Pbaru[i]= "P baru ["+i+"] yi:"+yi+",yj: "+yj+", yk: "+yk+" x0: "+x0+",y0: "+y0+"  rad:"+rad+ " f(x): "+fit+"\n" ;
      }
      
  }
  
  private  int minimum(){
    // Returns an array index.
        int winner = 0;
        boolean foundNewWinner = false;
        boolean done = false;

        while(!done)
        {
            foundNewWinner = false;
            for(int i = 0; i < population; i++)
            {
                if(i != winner){             // Avoid self-comparison.
                    // The minimum has to be in relation to the Target.
                    if( testProblem(i) < testProblem(winner) ){
                        winner = i;
                        foundNewWinner = true;
                    }
                }
            }

            if(foundNewWinner == false){
                done = true;
            }
        }

        return winner;
    }
   
  private void Initialization(){
        Particle = new Particle[population];
      
       
        for (int i = 0; i < population; i++) {
              Particle[i] = new Particle(nSolution);
          }
     
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
        for (int i = 0; i < nSolution; i++) {
                    Pijk[i]= new PixelVector(a);
                }
          for (int i = 0; i < population; i++) {
              Particle[i] = new Particle(nSolution);
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
        for (int i = 0; i < population; i++) {
              //RandomThreePixel(maxValue,minValue,zero,i);
              RandomThreePixel p = new RandomThreePixel(P,Pijk);
              p.Process(maxValue, minValue, zero, height, width);
              x0  =p.getx0();
              y0  =p.gety0();
              rad=p.getrad();
              int xi,xj,xk,yi,yj,yk;
              xi= p.getxi();
              xj= p.getxj();
              xk= p.getxk();
              
              yi= p.getyi();
              yj= p.getyj();
              yk= p.getyk();

              Particle[i].setParticle(0, x0);
              Particle[i].setParticle(1, y0);
              Particle[i].setParticle(2, rad);
                    
              Particle[i].setX(xi, xj, xk);
              Particle[i].setY(yi, yj, yk);
          } 
         
      
    }
  
  private void EvaluateFitness(){
          int xc,yc,rc;
          int thick =1; // THICKK
          
          for (int i = 0; i < population; i++) {
              int NS,sumCircle;
              float jC;
              xc= Particle[i].getSolutionVector(0);
              yc= Particle[i].getSolutionVector(1);
              rc= Particle[i].getSolutionVector(2);
              
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
              Particle[i].setFitness(jC);
          }    
          
       
      }
  
  private boolean checkFitness(int index){
      boolean out=false;
      int  x,y,r;
      x=Particle[index].getParticleVector(0);
      y=Particle[index].getParticleVector(1);
      r=Particle[index].getParticleVector(2);
      
      CheckBoundary check = new CheckBoundary();
      check.setItem(x, y, r, height, width);
      check.checking();
      out= check.getBool();

      return out;
  }
  
  private void resetParticle(int index){
    //Particle[index]=Particle[bestIndex] ;
    RandomThreePixel p = new RandomThreePixel(P,Pijk);
    int maxValue = sumEdge;
    int minValue=0;
    p.Process(maxValue, minValue, 0, height, width);
    int x  =p.getx0();
    int y  =p.gety0();
    int rad=p.getrad();
    
    int xi,xj,xk,yi,yj,yk;
    xi= p.getxi();
    xj= p.getxj();
    xk= p.getxk();
              
    yi= p.getyi();
    yj= p.getyj();
    yk= p.getyk();
              
    Particle[index].setParticle(0, x); //untuk masukin x0;
    Particle[index].setParticle(1, y); //untuk masukin y0;
    Particle[index].setParticle(2, rad);//masukin radius;
              
     Particle[index].setX(xi, xj, xk);
     Particle[index].setY(yi, yj, yk);
  }
  
  private  double testProblem(int index) {
       double value=0;
      
      int NS,sumCircle;
      float jC;
      
      int x,y,r;
      x= Particle[index].getSolutionVector(0);
      y= Particle[index].getSolutionVector(1);
      r= Particle[index].getSolutionVector(2);
      
      BufferedImage Circlex = null;
      BufferedImage edgeoverlay = null;

      MidCirclePointAlgorithm lingkaran= new MidCirclePointAlgorithm(width,height);

      lingkaran.drawCircle(x, y,r, width, height,1);
      Circlex = lingkaran.getImages();
      EdgeWithCircle newOverlay = new EdgeWithCircle();

      newOverlay.setImage(Edge, Circlex);
      newOverlay.process();         
      newOverlay.Fitness();
      jC = newOverlay.getFitness();            
      value=jC;   

      return value; 
    }
  
  private  void getVelocity(int gBestindex){
        //  refrensi dari Kennedy & Eberhart(1995).
        //    vx[][] = vx[][] + 2 * rand() * (pbestx[][] - presentx[][]) + 
        //                      2 * rand() * (pbestx[][gbest] - presentx[][])

        double testResults = 0;
        double bestResults = 0;
        double vValue = 0.0;

        bestResults = testProblem(gBestindex);

        for(int i = 0; i < population ; i++)
        {
            testResults = testProblem(i);
            aParticle[0] = Particle[i];
            vValue = aParticle[0].velocity() + 2 * new Random().nextDouble() * (aParticle[0].getFitness() - testResults) + 2 * new Random().nextDouble() * (bestResults - testResults);
            if(vValue > vmax){
                aParticle[0].velocity(vmax);
            }else if(vValue < -vmax){
                aParticle[0].velocity(-vmax);
            }else{
                aParticle[0].velocity(vValue);
            }
        }
    }
  
  private  void updateparticles(int gBestindex){
        boolean out =false;
        Particle[] gBParticle;
        gBParticle  = new Particle[1];
        gBParticle[0] = new Particle(nSolution);
        
        for (int i = 0; i < nSolution; i++) {
            gBParticle[0].setParticle(i, Particle[gBestindex].getParticleVector(i) );
        }
        
        for(int i = 0; i < population; i++)
        {
            for(int j = 0; j < nSolution; j++)
            {
               
                if (Particle[i].getParticleVector(j)!= gBParticle[0].getParticleVector(j) ) {
                    Particle[i].setParticle(j, Particle[i].getParticleVector(j) + (int)Math.round(Particle[i].velocity()));
                }
                
            }

            // Check Nilai pBest
            out=checkFitness(i);
            if (out || testProblem(i)==1) {
                System.out.print("OUT");
                resetParticle(i);
                System.out.print("Partikel ke " +i+"out of bound, di reset \n");
            }
            
            double total = testProblem(i);
            
            if (total < Particle[i].getFitness()) {
                Particle[i].setFitness(total);
            }

        } 
    }
  
  public int getx(){
      return Particle[bestIndex].getSolutionVector(0);
  }
  
  public int gety(){
      return Particle[bestIndex].getSolutionVector(1);
  }
  
  public int getr(){
      return Particle[bestIndex].getSolutionVector(2);
  }
  
  public String[] getPlama(){
      return Plama;
  }
  
 public String[] getPbaru(){
     return Pbaru;
 }
}
