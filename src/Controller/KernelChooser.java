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
public class KernelChooser {
    String Kernel=null;
    int height; int width;
    public KernelChooser(){
        
    }
    
    public void setKernel(String Kernel){
        this.Kernel = Kernel;
    }
    
    
    public void Choose(){
        if (Kernel == "3 x 3") {
            height =3;
            width=3;
        }
        else if (Kernel == "5 x 5") {
            height =5;
            width=5;
        }
        else if (Kernel == "7 x 7") {
            height =7;
            width=7;
        }
        else if (Kernel == "9 x 9") {
            height =9;
            width=9;
        }
        
        else if (Kernel == "11 x 11"){
            height=11;
            width=11;
        }
    }
    
    public int getKernelheight(){
        return height;
    }
    
    public int getKernelwidth(){
        return width;
    }
    
}
