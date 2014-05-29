/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle15.back;


public class Util {
    
    
    public static Estado[] inverso(Estado b[]){
        Estado[] temp=new Estado[b.length];
        int y=0;
        for(int x=b.length-1;x>=0;x--){
            temp[y++]=b[x];
        }
        return temp;
    }
    
    public static String toString(String[][] actual){
        String ret="";
        for(int x=0;x<actual.length;x++){
           for(int y=0;y<actual[0].length;y++){
               ret+=actual[x][y]+"\t";
           }
           ret+="\n";
        }
        return ret;
    }
    
   
    
}
