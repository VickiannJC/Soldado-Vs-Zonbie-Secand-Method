/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package soldadovszombie;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



/**
 *
 * @author USER
 */
public class generarGrafica extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;
    int nEcuacion;
    String base;
    double c1,c2,c3,c4,c5,c6, e1,e2,e3,e4,e5,e6;
    /**
     * Creates new form Explicacion
     */
    public generarGrafica(int nEcuacion) {
        super("Gráfico de la Ecuación");
        this.nEcuacion = nEcuacion;

        // Agregar un adaptador de ventana para cerrar la aplicación
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        setLocation(80,80);
        setUndecorated(true);
        setSize(400, 400);
        setVisible(true);
    }
     public generarGrafica(int nEcuacion,double c1,double c2, double c3, double c4, double c5, double c6) {
        super("Gráfico de la Ecuación");
        this.nEcuacion = nEcuacion;
        this.c1=c1;
        this.c2=c2;
        this.c3=c3;
        this.c4=c4;
        this.c5=c5;
        this.c6=c6;

        // Agregar un adaptador de ventana para cerrar la aplicación
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        setLocation(490,295);
        setUndecorated(true);
        setSize(400, 400);
        setVisible(true);
    }
     
    public generarGrafica(int nEcuacion,double c1,double c2, double c3, double e1, double e2, double e3,double e4, double e5, double e6) {
        super("Gráfico de la Ecuación");
        this.nEcuacion = nEcuacion;
        this.c1=c1;
        this.c2=c2;
        this.c3=c3;
        this.e1=e1;
        this.e2=e2;
        this.e3=e3;
        this.e4=e4;
        this.e5=e5;
        this.e6=e6;

        // Agregar un adaptador de ventana para cerrar la aplicación
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        setLocation(490,280);
        setUndecorated(true);
        setSize(400, 400);
        setVisible(true);
    }
    
     public generarGrafica(int nEcuacion,double c1,double c2, double c3, double c4) {
        super("Gráfico de la Ecuación");
        this.nEcuacion = nEcuacion;
        this.c1=c1;
        this.c2=c2;
        this.c3=c3;
        this.c4=c4;
        

        // Agregar un adaptador de ventana para cerrar la aplicación
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        setLocation(490,260);
        setUndecorated(true);
        setSize(400, 400);
        setVisible(true);
    }
     
    public generarGrafica(int nEcuacion,String base,double c1,double c2, double c3, double c4) {
        super("Gráfico de la Ecuación");
        this.nEcuacion = nEcuacion;
        this.base = base;
        this.c1=c1;
        this.c2=c2;
        this.c3=c3;
        this.c4=c4;
        

        // Agregar un adaptador de ventana para cerrar la aplicación
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        setLocation(490,260);
        setUndecorated(true);
        setSize(400, 400);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        // Crear un objeto Graphics2D
        Graphics2D g2 = (Graphics2D) g;

        // Definir los límites del gráfico
        int xMin = 0;
        int xMax = getWidth();
        int yMin = 0;
        int yMax = getHeight();

        // Dibujar los ejes X e Y
        g2.setColor(Color.BLACK);
        g2.drawLine(xMin, yMax / 2, xMax, yMax / 2); // Eje X
        g2.drawLine(xMax / 2, yMin, xMax / 2, yMax); // Eje Y

        // Dibujar las ecuaciones
        g2.setColor(Color.RED);
        double escalaX = 30.0;
        double escalaY = 20.0;
        
        switch(nEcuacion){
            case 1: {
                //Ecuación Cúbica -> Elegir Ecuación
            for (int i = 0; i < getWidth(); i++) {
            double x = (i - getWidth() / 2) / escalaX;
            double y = -(Math.pow(x, 3) - 2 * x - 5) * escalaY + getHeight() / 2;
            g2.fillOval(i, (int) y, 2, 2);
             }}
            break;
            case 2:{ 
                //Ecuación exponencial-> Elegir Ecuación
            for (int i = 0; i < getWidth(); i++) {
            double x = (i - getWidth() / 2) / escalaX;
            double y = -(Math.exp(x) - 3 * Math.pow(x, 2)) * escalaY + getHeight() / 2;
            g2.fillOval(i, (int) y, 2, 2);
            }}
            break;
            case 3: {
                //Ecuación Trigonométrica-> Elegir Ecuación
                //seno 
            for (int i = 0; i < getWidth(); i++) {
            double x = (i - getWidth() / 2) / escalaX;
            double y = -(Math.sin(x) - Math.pow(x, 2) + 2 * x + 1) * escalaY + getHeight() / 2;
            g2.fillOval(i, (int) y, 2, 2);
            }}
            break;
            case 4: {
                //Ecuación Logarítmica -> Elegir Ecuación
                //Logaritmo base natural
          /*for (int i = 0; i < getWidth(); i++) {
            double x = (i + getWidth() / 2) / escalaX;
            double y = -(Math.log(x) - 2 * x + 3) * escalaY + getHeight() / 2;
            g2.fillOval(i, (int) y, 2, 2);}}*/
          
           //Logaritmo activo base 10
            for (int i = 0; i <= getWidth(); i++) {
            double x = (i - getWidth() / 2) / escalaX;
            double y = -(Math.log10(x) - 2 * x + 3) * escalaY + getHeight() / 2;
            g2.fillOval(i, (int) y, 2, 2);}}
            break;
            case 5: {
                //Ecuación con radical -> Elegir ecuación
            for (int i = 0; i < getWidth(); i++) {
            double x = (i - getWidth() / 2) / escalaX;
            double y = -(Math.sqrt(x) - 2) * escalaY + getHeight() / 2;
            g2.fillOval(i, (int) y, 2, 2);
            }}
            break;
            case 6: {
                //Ecuación Cuadrática ->Elegir ecuación
            for (int i = 0; i < getWidth(); i++) {
            double x = (i - getWidth() / 2) / escalaX;
            double y = -(Math.pow(x, 2) - 4) * escalaY + getHeight() / 2;
            g2.fillOval(i, (int) y, 2, 2);}}
            break;
            case 7: {
                //Ecuación Lineal-> Ingresar Ecuación
            for (int i = 0; i < getWidth(); i++) {
            double x = (i - getWidth() / 2) / escalaX;
            double y = -(c1*Math.pow(x, 5)+c2*Math.pow(x, 4)+c3*Math.pow(x, 3)+c4*Math.pow(x, 2)+c5*x+c6) * escalaY + getHeight() / 2;
            g2.fillOval(i, (int) y, 2, 2);}}
            break;
            case 8: {
                //Ecuación exponencial -> Ingresar Ecuación
            for (int i = 0; i < getWidth(); i++) {
            double x = (i - getWidth() / 2) / escalaX;
            double y = -(Math.pow(c1, e1*x+e2)+Math.pow(c2, e3*x+e4)+Math.pow(c3, e5*x+e6)) * escalaY + getHeight() / 2;
            g2.fillOval(i, (int) y, 2, 2);}}
            break;
            case 9: {
                //Ecuación Trigonométrica Seno -> Ingresar Ecuación 
            for (int i = 0; i < getWidth(); i++) {
            double x = (i - getWidth() / 2) / escalaX;
            double y = -(c1 * Math.sin(c2*x+c3)+c4) * escalaY + getHeight() / 2;
            g2.fillOval(i, (int) y, 2, 2);}}
            break;
            case 10: {
                //Ecuación Trigonompetrica coseno -> Ingresar Ecuación
            for (int i = 0; i < getWidth(); i++) {
            double x = (i - getWidth() / 2) / escalaX;
            double y = -(c1 * Math.cos(c2*x+c3)+c4) * escalaY + getHeight() / 2;
            g2.fillOval(i, (int) y, 2, 2);}}
            break;
            case 11: {
                //Ecuación Trigonométrica tangente -> Ingresar Ecuacion
            for (int i = 0; i < getWidth(); i++) {
            double x = (i - getWidth() / 2) / escalaX;
            double y = -(c1 * Math.tan(c2*x+c3)+c4) * escalaY + getHeight() / 2;
            g2.fillOval(i, (int) y, 2, 2);}}
            break;
            case 12: {
                //Ecuación logarítmica ->Ingresar Ecuación
                if(base=="e"){
                    //Logarítmo Natural
                    for (int i = 0; i < getWidth(); i++) {
                    double x = (i - getWidth() / 2) / escalaX;
                    double y = -(c1 * Math.log(c2*x+c3)+c4) * escalaY + getHeight() / 2;
                    g2.fillOval(i, (int) y, 2, 2);}
                    break;
                    
                }else{
                    //Logarítmo de base 10
                    for (int i = 0; i < getWidth(); i++) {
                    double x = (i - getWidth() / 2) / escalaX;
                    double y = -(c1 * Math.log10(c2*x+c3)+c4) * escalaY + getHeight() / 2;
                    g2.fillOval(i, (int) y, 2, 2);}
                    break;
                }
                
            
            
           
        }
        
        }
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
