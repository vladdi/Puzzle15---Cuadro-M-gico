package puzzle15.back;


public class Problema {

    static public int tamanoTablero = 0;
    public Estado inicio;
    public Estado fin;
    String[][] f;
    public String[][] G;

    public Problema(Estado first, Estado goalstate, int getN) {
        inicio = first;
        fin = goalstate;
        tamanoTablero = getN;
    }

    public boolean probar(Estado actual) {
        boolean g = true;
        for (int i = 0; i < Problema.tamanoTablero; i++) {
            for (int j = 0; j < Problema.tamanoTablero; j++) {
                if (actual.getActual()[i][j] == null ? fin.getActual()[i][j] != null : !actual.getActual()[i][j].equals(fin.getActual()[i][j])) {
                    g = false;
                }
            }
        }
        return g;
    }

    public Estado[] siguiente(Estado actual) {
        Estado[] S = new Estado[4];
        S[0] = Acciones.izquierda(actual);
        S[1] = Acciones.Derecha(actual);
        S[2] = Acciones.Abajo(actual);
        S[3] = Acciones.Arriba(actual);
        return S;
    }

    public int costo(Estado estado) {
        return estado.g;
    }
}