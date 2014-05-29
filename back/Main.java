/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle15.back;

class Main {

    public static void main(String[] args) {
        
        Estado first = new Estado(4);
        //1,2,3,4,5,6,7,8,9,10,11,15,14,13,12
        first.setActual(new String[][]{{"1","2","3","4"},
                                  {"5","6","7","8"},
                                  {"9","10","12","15"},
                                  {"13","14","11","-"}});
        
        Estado goal = new Estado(4);
        goal.setActual(new String[][]{{"1","2","3","4"},
                                  {"5","6","7","8"},
                                  {"9","10","11","12"},
                                  {"13","14","15","-"}});
        
        
        Problema P = new Problema(first, goal, 4);
        AlgoritmoA s = new AlgoritmoA(P);
        Estado[] solution = s.getSolucion();
        for (Estado st : solution) {
            System.out.println(st);
            System.out.println("Accion: "+st.ultimoEstado);
        }
        System.out.println("solution Length : " + solution.length + "\n\n");

    }
}