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
public class Parameter_PSO {
    private static int iteration;
    private static int v_max;
    private static int population;
    
    
    public Parameter_PSO(){
        
    }
    
      public Parameter_PSO(int iteration, int v_max, int  population){
        this.iteration               = iteration;
        this.v_max                   = v_max;
        this.population              = population;
        
    }
    
      public static int getIteration(){
        return iteration;
    }
    
    public static int getVelocityMax(){
        return v_max;
    }
    
    public static int getPopulation(){
        return population;
    }

    
}
