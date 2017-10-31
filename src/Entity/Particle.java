/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Nanda-PC
 */
public class Particle {
    int[] particleVector;
    double fitness;
    private double mVelocity = 0.0;
    int[][] pixelVector;
    
    public Particle(int nSolution){
        particleVector= new int[nSolution];
        this.mVelocity = 0.0;
        pixelVector= new int[2][3];
    }
    
    public void setParticle(int index, int solution){
        particleVector[index]= solution;
    }
    
    public void setFitness(double fitness){
        this.fitness= fitness;
    }
    
    public int getParticleVector(int index){
        return particleVector[index];
    }
    
    public double getFitness(){
        return fitness;
    }
    
     public double velocity()
     {
            return this.mVelocity;
     }
        
    public void velocity(double velocityScore)
     {
           this.mVelocity = velocityScore;
         
     }
    
    public void setX(int xi,int xj,int xk)
    {
        pixelVector[0][0]= xi;
        pixelVector[0][1]= xj;
        pixelVector[0][2]= xk;
    }
    
     public void setY(int yi,int yj,int yk)
    {
        pixelVector[1][0]= yi;
        pixelVector[1][1]= yj;
        pixelVector[1][2]= yk;
    }
     
     public void setXindex(int index,int x){
         pixelVector[0][index]=x;
     }
     
      public void setYindex(int index,int y){
         pixelVector[1][index]=y;
     }
    
    
    public int getSolutionVector(int index)
    {
        return particleVector[index];
    }
    
    public int getPixelVectorX(int index){
        return pixelVector[0][index];
    }
    
    
    public int getPixelVectorY(int index){
        return pixelVector[1][index];
    }
    
    
    
    
}
