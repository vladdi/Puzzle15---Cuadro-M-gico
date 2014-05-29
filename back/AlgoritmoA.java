/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle15.back;

public class AlgoritmoA {

    
    private Problema problema;
    
    static public int[] buscar(String b, String[][] C) {
        int[] a = new int[2];
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[0].length; j++) {
                if (C[i][j] == b) {
                    a[0] = i;
                    a[1] = j;
                }
            }
        }
        return a;
    }

    public AlgoritmoA(Problema currentP) {
        problema = currentP;
    }

    public int heuristica(Estado current) {
        int total = 0;
        for (int i = 1; i < Problema.tamanoTablero * Problema.tamanoTablero; i++) {
            total += (int) Math.abs(
                    buscar(
                    i + "", current.getActual())[0]
                    - buscar(
                    i + "", problema.fin.getActual())[0]) + Math.abs(
                    buscar(i + "", current.getActual())[1]
                    - buscar(i + "", problema.fin.getActual())[1]);
        }
        return total;
    }

    public static int inversion(int num, int inicial[][]) {
        int res = 0;
        int x = 0, y = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (num == inicial[i][j]) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        for (int i = x; i < 4; i++) {
            for (int j = y; j < 4; j++) {
                if (num > inicial[i][j] && inicial[i][j] != 0) {
                    res++;
                }
                y = 0;
            }
            x = 0;
        }
        return res;
    }

    public Estado[] getSolucion() {
        
        Pila pila = new Pila(100000000);
        problema.inicio.f = heuristica(problema.inicio);
        pila.insertar(problema.inicio);
        while (true) {
            if (pila.isVacio()) {
                Estado[] solucion = new Estado[1];
                solucion[0].ultimoEstado = "fallido";
                return solucion;
            }
            Estado a = pila.remover();
            if (problema.probar(a)) {
                //thinkform.Hide();
                Estado[] solucion = new Estado[a.g];
                int i = 0;
                Estado s = a;
                while (s.getPadre() != null) {
                    solucion[i] = s;
                    i++;
                    s = s.getPadre();
                }
                solucion = Util.inverso(solucion);
                return solucion;
            } else {
                for (Estado s : problema.siguiente(a)) {
                    if (!s.bandera) {
                        continue;
                    }
                    s.setPadre(a);
                    s.f = heuristica(s) + s.g;
                    if (!pila.insertar(s)) {
                        System.out.println("fuera de memoria");
                        Estado[] solucion = new Estado[1];
                        solucion[0].ultimoEstado = "fuera de memoria";
                        return solucion;
                    }
                }
            }
        }
    }
}