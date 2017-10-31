/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author eek
 */
public class Parameter {
    private static int cycles;
    private static int hmSize;
    private static double hmConsiderationRate;
    private static double pitchAdjustmentRateMin;
    private static double pitchAdjustmentRateMax;
    private static int bandwithMin;
    private static int bandwithMax;
    
    public Parameter(){
        
    }
    
      public Parameter(int cycles, int hmsize, double hmConsiderationRate, double pitchAdjustmentRateMin, double pitchAdjustmentRateMax, int bandwithMin, int bandwithMax){
        this.cycles                  = cycles;
        this.hmSize                  = hmsize;
        this.hmConsiderationRate     = hmConsiderationRate;
        this.pitchAdjustmentRateMin  = pitchAdjustmentRateMin;
        this.pitchAdjustmentRateMax  = pitchAdjustmentRateMax;
        this.bandwithMin             = bandwithMin;
        this.bandwithMax             = bandwithMax;
    }
    
      public static int getCycles(){
        return cycles;
    }
    
    public static int getHarmonyMemorySize(){
        return hmSize;
    }
    
    public static double getHMConsiderationRate(){
        return hmConsiderationRate;
    }

    public static double getPitchAdjustmentRateMin(){
        return pitchAdjustmentRateMin;
    }
    
    public static double getPitchAdjustmentRateMax(){
        return pitchAdjustmentRateMax;
    }
    
    public static int getBandwithMin(){
        return bandwithMin;
    }
    public static int getBandwithMax(){
        return bandwithMax;
    }
    
}
