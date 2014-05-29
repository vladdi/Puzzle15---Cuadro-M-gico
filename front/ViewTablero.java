package puzzle15.front;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class ViewTablero extends JPanel implements ActionListener {

    private JButton[][] celdas;
    private JTextField[][] labels;
    private javax.swing.JToggleButton ingresar;
    private int ancho = 50;
    private int alto = 50;

    public ViewTablero() {
        celdas = new JButton[4][4];
        labels = new JTextField[4][4];
        ingresar = new JToggleButton("Ingresar");
        ingresar.addActionListener(this);
        int num = 1;
        this.setLayout(null);
        for (int j = 0; j < celdas[0].length; j++) {
            for (int i = 0; i < celdas.length; i++) {
                celdas[i][j] = new JButton((num++) + "");
                labels[i][j] = new JTextField(2);
                this.add(celdas[i][j]).setBounds(i * alto, j * ancho + 30, ancho, alto);
                this.add(labels[i][j]).setBounds(i * alto, j * ancho + 30, ancho, alto);
                labels[i][j].setVisible(false);
                celdas[i][j].addActionListener(this);
            }
        }
        celdas[celdas.length - 1][celdas[0].length - 1].setText("0");
        this.add(ingresar).setBounds(0, 0, 100, 20);
    }

    public int[] buscar(JButton b) {
        for (int j = 0; j < celdas[0].length; j++) {
            for (int i = 0; i < celdas.length; i++) {
                if (b == celdas[j][i]) {
                    return new int[]{j, i};
                }
            }
        }
        return null;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public void mover(int x, int y) {
        int[] pos = this.buscarBlanco();
        System.out.println(Arrays.toString(pos));
        int[][] mov = this.getPosiblesMovimientos(pos);
        boolean posible = false;
        for (int i = 0; i < mov.length; i++) {
            if (mov[i][0] == x && mov[i][1] == y) {
                posible = true;
            }
        }
        if (pos != null && posible) {
            Movimiento m = new Movimiento(celdas[x][y], celdas[pos[0]][pos[1]], this);
            m.start();
            Movimiento m2 = new Movimiento(celdas[pos[0]][pos[1]], celdas[x][y], this);
            m2.start();
            JButton c = celdas[x][y];
            celdas[x][y] = celdas[pos[0]][pos[1]];
            celdas[pos[0]][pos[1]] = c;
            this.repaint();
        }

    }

    public void mover(String num) {
        for (int i = 0; i < celdas.length; i++) {
            for (int j = 0; j < celdas[0].length; j++) {
                if(celdas[i][j].getText() == null ? num == null : celdas[i][j].getText().equals(num)){
                    mover(i,j);
                }
            }
        }
    }

public int posiblesMovimientos(int vacio[]) {
        int numero = 4;
        if ((vacio[0] == 0) || (vacio[0] == celdas.length - 1)) {
            numero--;
        }
        if ((vacio[1] == 0) || (vacio[1] == celdas[0].length - 1)) {
            numero--;
        }
        return numero;
    }

    public int[][] getPosiblesMovimientos(int[] hueco) {
        int nu = this.posiblesMovimientos(hueco);
        System.out.println("posibles " + nu);
        int[][] aux = new int[nu][2];
        int i = 0;
        if (hueco[0] > 0) {
            aux[i][0] = hueco[0] - 1;
            aux[i][1] = hueco[1];
            i++;
        }
        if (hueco[0] < nu) {
            aux[i][0] = hueco[0] + 1;
            aux[i][1] = hueco[1];
            i++;
        }
        if (hueco[1] > 0) {
            aux[i][0] = hueco[0];
            aux[i][1] = hueco[1] - 1;
            i++;
        }
        if (hueco[1] < nu) {
            aux[i][0] = hueco[0];
            aux[i][1] = hueco[1] + 1;
            i++;
        }
        return aux;
    }

    public int[] buscarBlanco() {
        int[] a = new int[2];
        for (int i = 0; i < celdas.length; i++) {
            for (int j = 0; j < celdas[0].length; j++) {
                if ("0".equals(celdas[i][j].getText())) {
                    a[0] = i;
                    a[1] = j;
                    return a;
                }
            }
        }
        return null;
    }

    public String[][] getDatos() {
        String [][] datos=new String[celdas.length][celdas[0].length];
        for (int j = 0; j < celdas[0].length; j++) {
            for (int i = 0; i < celdas.length; i++) {
                datos[j][i]=celdas[i][j].getText();
            }
        }
        return datos;
    }

    @Override
        public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JButton) {
            JButton b = (JButton) e.getSource();
            int pos[] = buscar(b);
            if (pos != null) {
                System.out.println("apreto " + pos[0] + "" + pos[1]);
                mover(pos[0], pos[1]);
            }
        } else if (e.getSource() == ingresar) {
            if (ingresar.isSelected()) {
                for (int j = 0; j < celdas[0].length; j++) {
                    for (int i = 0; i < celdas.length; i++) {
                        celdas[i][j].setVisible(false);
                        labels[i][j].setVisible(true);
                    }
                }

            } else {
                for (int j = 0; j < celdas[0].length; j++) {
                    for (int i = 0; i < celdas.length; i++) {
                        celdas[i][j].setVisible(true);
                        labels[i][j].setVisible(false);
                        celdas[i][j].setText(labels[i][j].getText());
                    }
                }
            }
        }
    }
}
