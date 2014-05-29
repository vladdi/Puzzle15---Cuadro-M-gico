package puzzle15.back;

public class Pila {

    private Estado[] array;
    private int max;
    private int tope;

    public Pila(int tam) {
        max = tam;
        tope = 0;
        array = new Estado[max];
    }

    public boolean isVacio() {
        return tope == 0;
    }

    public boolean insertar(Estado S) {
        if (tope == max) {
            return false;
        }
        array[tope] = S;
        subir(tope++);
        return true;
    }

    public void subir(int i) {
        int padre = (i - 1) / 2;
        Estado abajo = array[i];
        while (i > 0 && array[padre].f > abajo.f) {
            array[i] = array[padre];
            i = padre;
            padre = (padre - 1) / 2;
        }
        array[i] = abajo;
    }

    public Estado remover() 
    {
        Estado raiz = array[0];
        array[0] = array[--tope];
        bajar(0);
        return raiz;
    }

    public void bajar(int i) {
        int tem;
        Estado top = array[i];
        while (i < tope / 2) {
            int izq = 2 * i + 1;
            int der = izq + 1;
            if (der < tope && array[izq].f > array[der].f) {
                tem = der;
            } else {
                tem = izq;
            }
            if (top.f <= array[tem].f) {
                break;
            }
            array[i] = array[tem];
            i = tem;
        }
        array[i] = top;
    }

    public void verPila() {
        for (int i = 0; i < tope; i++) {
            System.out.println("");
            System.out.println(i + " : ");
        }
        System.out.println();
        System.out.println("==================================================");
    }
}
