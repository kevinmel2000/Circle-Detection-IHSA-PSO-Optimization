/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.PixelVector;

/**
 *
 * @author eek
 */
public class RandomThreePixelGenerator {
    int SumEdge;
    private PixelVector[] P;
    private PixelVector[] Pijk;
    int x0,y0,rad;
    int xi,xj,xk;
    int yi,yj,yk;
    
    public RandomThreePixelGenerator(PixelVector[] P1,PixelVector[] Pijk1){
        P=P1;
        Pijk=Pijk1;
        
    }
    
    public void Process(int maxValue,int minValue,int zero, int height,int width){
         Randomizer rand = new Randomizer();
         BoundaryChecker check = new BoundaryChecker(); 
                    int x1=0;
                    int y1=0;  
                    boolean out= false;
       //int cekheight, cekwidth;
       do {
                for (int i = 0; i < 3; i++) {
                        rand.SetRandom(maxValue, minValue);
                        int x = rand.GetRandom();
                        x1=P[x].getCoorx(zero);
                        y1=P[x].getCoory(zero);
                        Pijk[i].setCoor(x1, y1, zero);
                        x1=Pijk[i].getCoorx(zero);
                        y1=Pijk[i].getCoory(zero);                       
                    }         
                    // ini bakal di pindahin ke vector Ca;
                    int X1,X2,X3,Y1,Y2,Y3;
                    
                    X1=Pijk[0].getCoorx(zero);
                    X2=Pijk[1].getCoorx(zero);
                    X3=Pijk[2].getCoorx(zero);

                   
                    Y1=Pijk[0].getCoory(zero);
                    Y2=Pijk[1].getCoory(zero);
                    Y3=Pijk[2].getCoory(zero);
                    
                    // ini bakal di pindahin ke vector Ca;
                    
                    DeterminanGenerator det = new DeterminanGenerator();
                    det.setDeterminanx(X1, X2, X3);
                    det.setDeterminany(Y1, Y2, Y3);
                    det.prosesDeterminan();

                    double x01  = det.getx0();
                    double y01  = det.gety0();
                    double rad11= det.getRad1();
                    double rad2 = det.getRad2();
                    double rad3 = det.getRad3();

                     x0=(int) Math.round(x01);
                     y0= (int) Math.round(y01);
                     rad=(int) Math.round(rad11);
                    check.setItem(x0, y0, rad, height, width);
                    check.checking();
                    out=check.getBool();
                    
                    xi=X1;
                    xj=X2;
                    xk=X3;
                    yi=Y1;
                    yj=Y2;
                    yk=Y3;
          }while(out==true); 
    }
    
    public int getx0(){
        return x0;
    }
    public int gety0(){
        return y0;
    }
    public int getrad(){
        return rad;
    }
    
    public int getxi(){
        return xi;
    }
    
    public int getxj(){
         return xj;
    }
    
    public int getxk(){
         return xk;
    }
    
    public int getyi(){
         return yi;
    }
    
    public int getyj(){
         return yj;
    }
    
    public int getyk(){
        return yk;
    }
    
    
}
