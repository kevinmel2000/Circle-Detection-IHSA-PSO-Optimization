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
public class BoundaryChecker {
     int centerX,centerY,rad,height,width;
     boolean out=false;
    public BoundaryChecker(){
        
    }
    
    public void setItem(int x0,int y0, int rad,int height, int width){
        this.centerX=x0;
        this.centerY=y0;
        this.rad=rad;
        this.height=height;
        this.width= width;
    }
    
    public void checking(){
        int x=0;   
        int y=rad;
        width= width-1;
        height=height-1;
        try {
                 if (centerX<=0) 
                 {out=true;}
                 else if (centerY<=0) 
                 {out=true;}
                 else if(rad<=0){out=true;}

                 else if(centerX+rad>width || centerX-rad <0 || centerY+rad>height || centerY-rad<0){out=true;}

                 /*
                 else if ((centerX+x>height)  || (centerY+y>height)) {out=true;}
                 else if ((centerX+x>height)  || (centerY-y>height)) {out=true;}
                 else if ((centerX+y>width )  || (centerY+x>width )) {out=true;}
                 else if ((centerX-y>width )  || (centerY-x>width )) {out=true;}
                 */    
                 
                 else if((centerX + x)>width || (centerY + y)>height){out=true;}
                 else if((centerX + x)>width || (centerY - y)>height){out=true;}
                 else if((centerX - x)>width || (centerY + y)>height){out=true;}  
                 else if((centerX - x)>width || (centerY - y)>height){out=true;}

                 else if((centerX + y)>width || (centerY + x)>height){out=true;}
                 else if((centerX + y)>width || (centerY - x)>height){out=true;}
                 else if((centerX - y)>width || (centerY + x)>height){out=true;}  
                 else if((centerX - y)>width || (centerY - x)>height){out=true;}

                 else if((centerX + x)<0 || (centerY + y)<0){out=true;}
                 else if((centerX + x)<0 || (centerY - y)<0){out=true;}
                 else if((centerX - x)<0 || (centerY + y)<0){out=true;}  
                 else if((centerX - x)<0 || (centerY - y)<0){out=true;}

                 else if((centerX + y)<0 || (centerY + x)<0){out=true;}
                 else if((centerX + y)<0 || (centerY - x)<0){out=true;}
                 else if((centerX - y)<0 || (centerY + x)<0){out=true;}  
                 else if((centerX - y)<0 || (centerY - x)<0){out=true;}
                 else{
                     out=false;
                 }
        } catch (Exception e){
            System.out.println(e);
        }
        
        
    }
    
    public boolean getBool(){
        return out;
    }
    
}
