package puzzle15.front;

import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;

public class Movimiento extends Thread {

    private JButton uno;
    private JButton dos;
    private JComponent padre;

    public Movimiento(JButton uno, JButton dos, JComponent parent) {
        if (uno != dos) {
            this.uno = uno;
            this.dos = dos;
            padre = parent;
        }
    }

    @Override
    public void run() {
        if (uno != null && dos != null) {
            Point puno = uno.getLocation();
            int xx=puno.x;
            int yy=puno.y;
            Point pdos = dos.getLocation();
            if (xx == pdos.getX()) {
                int y = (int)uno.getLocation().getY();
                while (uno.getLocation().y != pdos.y) {
                    if (yy < pdos.getY()) {
                        uno.setLocation(new Point(puno.x, y++));
                        //uno.setBounds(puno.x, y++, 50,50);
                        uno.repaint();
                    } else {
                        uno.setLocation(new Point(puno.x, y--));
                        uno.repaint();
                        //    uno.setBounds(puno.x, y--, 50,50);
                    }
                    dormir(10);
                }
            } else if (puno.getY() == pdos.getY()) {
                int x = uno.getLocation().x;
                while (uno.getLocation().x != pdos.x) {
                    if (puno.getX() < pdos.getX()) {
                        uno.setLocation(new Point(x++, puno.y));
                    } else {
                        uno.setLocation(new Point(x--, puno.y));
                    }
                    dormir(10);

                }
            } else {
                System.out.println("oh no");
            }
        }
    }

    public void dormir(int mili) {
        try {
            padre.repaint();
            this.sleep(mili);
        } catch (InterruptedException ex) {
            Logger.getLogger(Movimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
