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
public class HarmonyMemory {
    int[] solutionVector;
    int[][] pixelVector;
    double fitness;
    
    public HarmonyMemory(int nSolution)
    {
        solutionVector = new int[nSolution];
        pixelVector= new int[2][3];
    }
    public void setSolutionVector(int index, int solution)
    {
        solutionVector[index] = solution;
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
        return solutionVector[index];
    }
    
    public int getPixelVectorX(int index){
        return pixelVector[0][index];
    }
    
    
    public int getPixelVectorY(int index){
        return pixelVector[1][index];
    }
    
    
    public void setFitness(double fitness)
    {
        this.fitness = fitness;
    }
    
    public double getFitness()
    {
        return fitness;
    }
    
}