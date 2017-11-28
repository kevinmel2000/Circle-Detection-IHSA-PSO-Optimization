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
public class DeterminanGenerator {
    double xi,xj,xk;
    double yi,yj,yk;
    double A=0;
    double B=0;
    double x0=0;
    double y0=0;
    double rad1,rad2,rad3;
    
    public DeterminanGenerator(){
        
    }
    
    public void setDeterminanx(int xi,int xj,int xk){
        this.xi=xi;
        this.xj= xj;
        this.xk=xk;
        
    }
    
     public void setDeterminany(int yi,int yj,int yk){
        this.yi=yi;
        this.yj= yj;
        this.yk=yk;
    }
    
    public void prosesDeterminan(){
        
        double xi2,xj2,xk2;
        double yi2,yj2,yk2;
        
        xi2=xi*xi;
        xj2=xj*xj;
        xk2=xk*xk;
        
        yi2=yi*yi;
        yj2=yj*yj;
        yk2=yk*yk;
        
        double Aa= xj2+yj2-(xi2+yi2);
        double Ac=2*(yj-yi);
        double Ab=xk2+yk2-(xi2+yi2);
        double Ad= 2*(yk-yi);
        
        double Ba= 2*(xj-xi);
        double Bb= 2*(xk-xi);
        double Bc= xj2+yj2-(xi2+yi2);
        double Bd= xk2+yk2-(xi2+yi2);
        
        
        A= (Aa*Ad)-(Ab*Ac);
        B= (Ba*Bd)-(Bb*Bc);
        
        double pembagi = 4*((xj-xi)*(yk-yi)-(xk-xi)*(yj-yi));

        x0= A/pembagi;
        y0= B/pembagi;
        
       /* double tempxi= (x0-xi)*(x0-xi);
        double tempyi= (y0-yi)*(y0-yi);
        
        double tempxj= (x0-xj)*(x0-xj);
        double tempyj= (y0-yj)*(y0-yj);
        
        double tempxk= (x0-xk)*(x0-xk);
        double tempyk= (y0-yk)*(y0-yk);
        */
       
        double tempxi= Math.pow(x0-xi,2);
        double tempxj= Math.pow(x0-xj,2);
        double tempxk= Math.pow(x0-xk,2);
        
        double tempyi= Math.pow(y0-yi,2);
        double tempyj= Math.pow(y0-yj,2);
        double tempyk= Math.pow(y0-yk,2);
        
        rad1=  Math.sqrt(tempxi+tempyi);
        rad2=  Math.sqrt(tempxj+tempyj);
        rad3=  Math.sqrt(tempxk+tempyk);
    
    }
    
    public double getA(){
        return A;
    }
    
    public double getB(){
        return B;
    }
    
    public double getx0(){
        return x0;
    }
    
    public double gety0(){
        return y0;
    }
    
    public double getRad1(){
        return rad1;
    }
    public double getRad2(){
        return rad2;
    }
    public double getRad3(){
        return rad3;
    }
}
