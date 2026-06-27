/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package soldadovszombie;

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author USER
 */
public class Carrera extends JFrame {

    private JLabel lblSoldado;
    private JLabel lblZombie;
    private JPanel panelEstadio;
    private JButton btnInicio, btnRegresar, btnVerTabla;
    private int resSol, resZom;
    private int  xSoldado, xZombie;
    private JLabel lblEcuacion, lblNumIteSol, lblNumIteZom, lblNameSol,lblNameZom, lblDiamond;
    private JTextField txtNumIteSol, txtNumIteZom;
    private double contadorSol=0, contadorZom=0;

    private SoldadoVsZombie jugadorSol;
    private SoldadoVsZombie jugadorZom;
    private GraficaRacePanel graficaSoldado;
    private GraficaRacePanel graficaZombie;

    public Carrera(String ecuacion, int resSol, int resZom, SoldadoVsZombie jugadorSol, SoldadoVsZombie jugadorZom) {
        setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        setTitle("Carrera");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 750);

        Dimension dimensionFrame = new Dimension(1400, 500);
        int anchoPanel = (int) (dimensionFrame.width * 0.95);
        int altoPanel = (int) (dimensionFrame.height * 0.5);

        setLayout(null);
        this.resSol = resSol;
        this.resZom = resZom;
        this.jugadorSol = jugadorSol;
        this.jugadorZom = jugadorZom;
        
        ImageIcon iconoZombie = new ImageIcon(getClass().getResource("/Gráficos/pixil-frame-0 (3).png"));
        ImageIcon iconoSoldado = new ImageIcon(getClass().getResource("/Gráficos/soldadoIcono.png"));
        ImageIcon iconoDiamante = new ImageIcon(getClass().getResource("/Gráficos/Diamond.png"));

        lblSoldado = new JLabel(iconoSoldado);
        lblZombie = new JLabel(iconoZombie);
        lblDiamond = new JLabel(iconoDiamante);

        panelEstadio = new JPanel();
        panelEstadio.setBackground(new Color(128, 128, 128));
        panelEstadio.setLayout(null);
        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
        panelEstadio.setBorder(border);

        lblSoldado.setBounds(5, 5, 80, 80);
        lblZombie.setBounds(5, 150, 80, 80);
        lblDiamond.setBounds(1250, 95, 80, 80);
        panelEstadio.add(lblSoldado);
        panelEstadio.add(lblZombie);
        panelEstadio.add(lblDiamond);
        
        // Initialize the graph panels
        graficaSoldado = new GraficaRacePanel(jugadorSol, new Color(0, 123, 255), "SOLDIER'S SECANTS");
        graficaZombie = new GraficaRacePanel(jugadorZom, new Color(40, 167, 69), "ZOMBIE'S SECANTS");

        // Set bounds of graphs
        graficaSoldado.setBounds(30, 330, 400, 350);
        graficaZombie.setBounds(970, 330, 400, 350);
        
        // Center the label Ecuacion
        lblEcuacion = new JLabel(ecuacion);
        lblEcuacion.setBounds(450, 280, 500, 40);
        lblEcuacion.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
        lblEcuacion.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Symmetrical Stats and Controls
        // Soldier Stats (left of center)
        lblNameSol = new JLabel("SOLDIER");
        lblNameSol.setBounds(460, 340, 200, 30);
        lblNameSol.setFont(new Font("OCR A Extended", Font.BOLD, 18));
        
        lblNumIteSol = new JLabel("Iterations:");
        lblNumIteSol.setBounds(460, 375, 200, 30);
        lblNumIteSol.setFont(new Font("OCR A Extended", Font.PLAIN, 14));

        txtNumIteSol = new JTextField();
        txtNumIteSol.setBounds(460, 410, 80, 50);
        txtNumIteSol.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
        txtNumIteSol.setEditable(false);
        txtNumIteSol.setText(Double.toString(contadorSol));
        
        // Zombie Stats (right of center)
        lblNameZom = new JLabel("ZOMBIE");
        lblNameZom.setBounds(740, 340, 200, 30);
        lblNameZom.setFont(new Font("OCR A Extended", Font.BOLD, 18));
        
        lblNumIteZom = new JLabel("Iterations:");
        lblNumIteZom.setBounds(740, 375, 200, 30);
        lblNumIteZom.setFont(new Font("OCR A Extended", Font.PLAIN, 14));

        txtNumIteZom = new JTextField();
        txtNumIteZom.setBounds(740, 410, 80, 50);
        txtNumIteZom.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
        txtNumIteZom.setEditable(false);
        txtNumIteZom.setText(Double.toString(contadorZom));
        
        // Botón Inicio
        btnInicio = new JButton("Run");
        btnInicio.setBounds(550, 480, 300, 60);
        btnInicio.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        btnInicio.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
        
        // Botón Regresar
        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(550, 560, 300, 60);
        btnRegresar.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
        btnRegresar.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        
        // Botón Ver Tabla
        btnVerTabla = new JButton("Resultados");
        btnVerTabla.setBounds(550, 640, 300, 60);
        btnVerTabla.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
        btnVerTabla.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        btnVerTabla.setEnabled(false); // Only enable once race is finished
        
        btnVerTabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TablaResultadosFrame tabla = new TablaResultadosFrame(jugadorSol, jugadorZom);
                tabla.setVisible(true);
            }
        });
        
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ElegirMenuEcuaciones menu = new ElegirMenuEcuaciones();
                menu.setVisible(true);
                dispose();
            }
        });
        
        btnInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Iniciar la carrera al hacer clic en el botón
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        iniciarCarrera();
                    }
                }).start();
            }
        });
        
        // Agregar un listener para el evento de entrada del ratón al botón
        btnInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInicioMouseEntered(evt);
                btnRegresarMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInicioMouseExited(evt);
                btnRegresarMouseExited(evt);
            }
        });

        panelEstadio.setBounds(10, 10, anchoPanel, altoPanel);

        add(panelEstadio);
        add(btnInicio);
        add(btnRegresar);
        add(btnVerTabla);
        add(txtNumIteSol);
        add(lblNameSol);
        add(lblNumIteSol);
        add(txtNumIteZom);
        add(lblNameZom);
        add(lblNumIteZom);
        add(lblEcuacion);
        add(graficaSoldado);
        add(graficaZombie);

        setVisible(true);
    }

    private void iniciarCarrera() {
        int velocidadSoldado, velocidadZombie;

        if (this.resSol < this.resZom) {
            velocidadSoldado = 2;
            velocidadZombie = 1;
        } else if (this.resZom < this.resSol) {
            velocidadSoldado = 1;
            velocidadZombie = 2;
        } else {
            velocidadSoldado = 2;
            velocidadZombie = 2;
        }

        // Reset positions and counters to guarantee clean start
        lblSoldado.setBounds(5, 5, 80, 80);
        lblZombie.setBounds(5, 150, 80, 80);
        this.xSoldado = 5;
        this.xZombie = 5;
        this.contadorSol = 0;
        this.contadorZom = 0;

        while (xSoldado < panelEstadio.getWidth()-100 && xZombie < panelEstadio.getWidth()-100) {

            xSoldado += velocidadSoldado;
            xZombie += velocidadZombie;
            if(contadorSol<=resSol){
                contadorSol += 0.01;
                txtNumIteSol.setText(String.format("%.2f", contadorSol));  
            }
            if(contadorZom<=resZom){
                contadorZom += 0.01;
                txtNumIteZom.setText(String.format("%.2f", contadorZom));
            }

            final double finalContadorSol = contadorSol;
            final double finalContadorZom = contadorZom;
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    lblSoldado.setBounds(xSoldado, 5, 80, 80);
                    lblZombie.setBounds(xZombie, 150, 80, 80);
                    graficaSoldado.setCurrentIteration(finalContadorSol);
                    graficaZombie.setCurrentIteration(finalContadorZom);
                }
            });

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        txtNumIteSol.setText((Integer.toString(resSol)));
        txtNumIteZom.setText((Integer.toString(resZom)));
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                graficaSoldado.setCurrentIteration(resSol);
                graficaZombie.setCurrentIteration(resZom);
                btnVerTabla.setEnabled(true);
            }
        });
        
        // Show the comparative results table automatically at the end of the race
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TablaResultadosFrame tabla = new TablaResultadosFrame(jugadorSol, jugadorZom);
                tabla.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        infoGanador ganador = new infoGanador();
                        // Correct tie condition check: if both crossed the line, it is a tie
                        if (xSoldado >= (panelEstadio.getWidth())-100 && xZombie >= (panelEstadio.getWidth())-100) {
                            JOptionPane.showMessageDialog(null, "EMPATE\n"
                                    + "Tienen el mismo número de iteraciones");
                        } else if (xSoldado >= (panelEstadio.getWidth())-100) {
                            ganador.premiacion("soldado");
                            ganador.setVisible(true);
                        } else if (xZombie >= (panelEstadio.getWidth())-100) {
                            ganador.premiacion("zombie");
                            ganador.setVisible(true);
                        }
                    }
                });
                tabla.setVisible(true);
            }
        });
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
    private void btnInicioMouseEntered(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
        btnInicio.setBackground(new Color(0, 255, 0));//Color verde 
    } 
    private void btnInicioMouseExited(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        btnInicio.setBackground(null);//Volver a color default 
    }   
    private void btnRegresarMouseEntered(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
        btnRegresar.setBackground(new Color(255, 255, 153));
    } 
    private void btnRegresarMouseExited(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        btnRegresar.setBackground(null);//Volver a color default 
    } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
