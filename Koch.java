//Koch.java: Koch curves.
import java.awt.*;
import java.awt.event.*;

public class Koch extends Frame {
   public static void main(String[] args) {new Koch();}

   Koch() {
      super("Koch. Click the mouse button to increase the level");
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {System.exit(0);}
      });
      setSize(1000, 1000);
      add("Center", new CvKoch());
      setVisible(true);
      pack();
   }
}

class CvKoch extends Canvas {
   public float x, y;
   double dir;
   int midX, midY, level = 1;

   int iX(float x) {return Math.round(500 + x);}
   int iY(float y) {return Math.round(500 + y);}

   CvKoch() {
      addMouseListener(new MouseAdapter() {
         public void mousePressed(MouseEvent evt) {
            level++; // Each mouse click increases the level by 1.
            repaint();
         }
      });
   }

   public void paint(Graphics g) {
      Dimension d = getSize();
      int maxX = d.width - 1, maxY = d.height - 1, 
          length = 3 * maxX / 4;
      midX = maxX / 2; midY = maxY / 2;
      x = (float) (-length / 2); // Start point
      y = 0;
      dir = 0;

      for (int i = 0; i < 3; i++) {
         drawKoch(g, length, level);
         dir -= 120;
      }
   }

   public void drawKoch(Graphics g, double length, int level) {
      if (level == 0) {
         double dirRad, xInc, yInc;
         dirRad = dir * Math.PI / 180;
         xInc = length * Math.cos(dirRad); // x increment
         yInc = length * Math.sin(dirRad); // y increment
         float x1 = x + (float) xInc, y1 = y + (float) yInc;
         g.drawLine(iX(x), iY(y), iX(x1), iY(y1));
         x = x1; y = y1;
      } else {
         //Recurive bristles
         drawKoch(g, length /= 3, --level);
         dir += 60;
         drawKoch(g, length, level);
         dir -= 120;
         drawKoch(g, length, level);
         dir += 60;
         drawKoch(g, length, level);
      }
   }
}