package puzzle15.back;

public class Estado {

    public String ultimoEstado = "";
    public boolean bandera = false;
    String[][] actual;
    public int g = 0;
    
    Estado padre;
    public int f;
    public String ficha="";

    public Estado(int n) {
        actual = new String[n][n];
    }

    public void setPadre(Estado P) {
        padre = P;
        g = P.g + 1;
    }

    public Estado getPadre() {
        return padre;
    }

    public void setActual(String[][] l) {
        bandera = true;
        for (int i = 0; i < l.length; i++) {
            for (int j = 0; j < l[0].length; j++) {
                actual[i][j] = l[i][j];
            }
        }
    }

    public String[][] getActual() {
        return actual;
    }
    
    public String toString(){
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