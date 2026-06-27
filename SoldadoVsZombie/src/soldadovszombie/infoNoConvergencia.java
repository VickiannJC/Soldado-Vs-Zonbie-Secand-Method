package soldadovszombie;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.Border;

public class infoNoConvergencia extends JFrame {

    public infoNoConvergencia(boolean noTieneRaices) {
        setUndecorated(true);
        setResizable(false);
        setSize(520, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Solid black border around the window, like infoError
        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
        this.getRootPane().setBorder(border);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        // Header Title (Neon Red)
        JLabel titleLabel = new JLabel("", JLabel.CENTER);
        titleLabel.setFont(new Font("OCR A Extended", Font.BOLD, 18));
        titleLabel.setForeground(new Color(255, 51, 51)); // Neon Red
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // JTextArea with pedagogical details
        JTextArea infoText = new JTextArea();
        infoText.setEditable(false);
        infoText.setBackground(Color.BLACK);
        infoText.setForeground(new Color(0, 255, 0)); // Neon Green
        infoText.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
        infoText.setLineWrap(true);
        infoText.setWrapStyleWord(true);

        if (noTieneRaices) {
            titleLabel.setText("¡FUNCIÓN SIN RAÍCES REALES!");
            infoText.setText(
                "¡ALERTA MATEMÁTICA!\n\n" +
                "La función evaluada no cruza el eje X en el intervalo de búsqueda elegido (no tiene raíces reales en esta región).\n\n" +
                "Dado que f(x) nunca se hace cero, el método de la Secante nunca podrá converger a una solución real.\n\n" +
                "CONSEJO:\n" +
                "   Prueba modificando los coeficientes de la función o introduce valores que encierren una raíz real."
            );
        } else {
            titleLabel.setText("¡DIVERGENCIA EN SECANTE!");
            infoText.setText(
                "El método de la Secante no pudo aproximar la raíz de la función. Esto ocurre cuando:\n\n" +
                "1. PENDIENTE PLANA: La secante es paralela al eje X (pendiente cero), indeterminando la fórmula por división para cero.\n\n" +
                "2. VALORES ALEJADOS: x0 y x1 se encuentran muy lejos de la raíz real.\n\n" +
                "CONSEJO:\n" +
                "   Ajusta los valores iniciales para acercarte al cruce con el eje X."
            );
        }
        
        infoText.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(51, 51, 51)));
        infoText.setSelectedTextColor(Color.BLACK);
        infoText.setSelectionColor(new Color(0, 255, 0));

        JScrollPane scrollPane = new JScrollPane(infoText);
        scrollPane.getViewport().setBackground(Color.BLACK);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));

        JButton btnEntendido = new JButton("Next");
        btnEntendido.setFont(new Font("OCR A Extended", Font.PLAIN, 12));
        btnEntendido.setBackground(Color.BLACK);
        btnEntendido.setForeground(new Color(0, 255, 0));
        btnEntendido.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, 
            new Color(51, 255, 102), new Color(0, 153, 0), new Color(51, 102, 0), new Color(51, 255, 0)));
        btnEntendido.setFocusPainted(false);
        btnEntendido.setPreferredSize(new Dimension(130, 36));

        btnEntendido.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEntendido.setBackground(new Color(0, 255, 0));
                btnEntendido.setForeground(Color.BLACK);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEntendido.setBackground(Color.BLACK);
                btnEntendido.setForeground(new Color(0, 255, 0));
            }
        });
        btnEntendido.addActionListener(e -> dispose());

        buttonPanel.add(btnEntendido);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
}
