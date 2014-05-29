package puzzle15.back;

public class Acciones {

    static public int[] buscarBlanco(String[][] matriz) {
        int[] a = new int[2];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] == "0") {
                    a[0] = i;
                    a[1] = j;
                    return a;
                }
            }
        }
        return a;
    }

    static public Estado izquierda(Estado currentstate) {
        Estado S = new Estado(Problema.tamanoTablero);
        if (buscarBlanco(currentstate.getActual())[1] == 0) {
        } else {
            String[][] next = new String[Problema.tamanoTablero][Problema.tamanoTablero];
            for (int i = 0; i < currentstate.getActual().length; i++) {
                for (int j = 0; j < currentstate.getActual()[0].length; j++) {
                    next[i][j] = currentstate.getActual()[i][j];
                }
            }
            int x = buscarBlanco(next)[0];
            int y = buscarBlanco(next)[1];
            next[x][y] = next[x][y - 1];
            next[x][y - 1] = "0";
            S.setActual(next);
            S.ultimoEstado = next[x][y]+"-DERECHA";
        }
        return S;
    }

    static public Estado Abajo(Estado currentstate) {
        Estado S = new Estado(Problema.tamanoTablero);
        if (buscarBlanco(currentstate.getActual())[0] == Problema.tamanoTablero - 1) {
        } else {
            String[][] next = new String[Problema.tamanoTablero][Problema.tamanoTablero];
            for (int i = 0; i < currentstate.getActual().length; i++) {
                for (int j = 0; j < currentstate.getActual()[0].length; j++) {
                    next[i][j] = currentstate.getActual()[i][j];
                }
            }
            int x = buscarBlanco(next)[0];
            int y = buscarBlanco(next)[1];
            next[x][y] = next[x + 1][y];
            next[x + 1][ y] = "0";
            S.setActual(next);
            S.ultimoEstado = next[x][y]+"-ARRIBA";
        }
        return S;
    }

    static public Estado Derecha(Estado currentstate) {
        Estado S = new Estado(Problema.tamanoTablero);
        if (buscarBlanco(currentstate.getActual())[1] == Problema.tamanoTablero - 1) {
        } else {
            String[][] next = new String[Problema.tamanoTablero][Problema.tamanoTablero];
            for (int i = 0; i < currentstate.getActual().length; i++) {
                for (int j = 0; j < currentstate.getActual()[0].length; j++) {
                    next[i][j] = currentstate.getActual()[i][j];
                }
            }
            int x = buscarBlanco(next)[0];
            int y = buscarBlanco(next)[1];
            next[x][y] = next[x][y + 1];
            next[x][ y + 1] = "0";
            S.setActual(next);
            S.ultimoEstado = next[x][y]+"-IZQUIERDA";
        }
        return S;
    }

    static public Estado Arriba(Estado currentstate) {
        Estado S = new Estado(Problema.tamanoTablero);
        if (buscarBlanco(currentstate.getActual())[0] == 0) {
        } else {
            String[][] next = new String[Problema.tamanoTablero][Problema.tamanoTablero];
            for (int i = 0; i < currentstate.getActual().length; i++) {
                for (int j = 0; j < currentstate.getActual()[0].length; j++) {
                    next[i][j] = currentstate.getActual()[i][j];
                }
            }
            int x = buscarBlanco(next)[0];
            int y = buscarBlanco(next)[1];
            next[x][y] = next[x - 1][y];
            next[x - 1][y] = "0";
            S.setActual(next);
            S.ultimoEstado = next[x][y]+"-ABAJO";
        }
        return S;
    }
}