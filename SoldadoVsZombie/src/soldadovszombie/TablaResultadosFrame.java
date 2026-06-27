package soldadovszombie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

public class TablaResultadosFrame extends JFrame {
    private SoldadoVsZombie jugadorSol;
    private SoldadoVsZombie jugadorZom;

    public TablaResultadosFrame(SoldadoVsZombie jugadorSol, SoldadoVsZombie jugadorZom) {
        this.jugadorSol = jugadorSol;
        this.jugadorZom = jugadorZom;

        setUndecorated(true); // Remove title bar / window decorations
        setTitle("Tabla de Resultados del Método de la Secante");

        // Dynamically size based on screen size to prevent overflow
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.95);
        int height = (int) (screenSize.height * 0.85);
        setSize(width, height);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Add a styled neon border to the root pane
        this.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0), 4));
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        // Custom Header without toolbars
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(20, 20, 20));
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 255, 0)));
        JLabel titleLabel = new JLabel("TABLA DE COMPARACIÓN DE TELEMETRÍA GRÁFICA (SECANTE)");
        titleLabel.setFont(new Font("OCR A Extended", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 255, 0));
        headerPanel.add(titleLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Content panel containing both tables and graphs side-by-side
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        contentPanel.setBackground(Color.BLACK);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Create individual panels for Soldier and Zombie
        contentPanel.add(crearTablaPanel("COMPETIDOR: SOLDADO", jugadorSol, new Color(0, 123, 255)));
        contentPanel.add(crearTablaPanel("COMPETIDOR: ZOMBIE", jugadorZom, new Color(40, 167, 69)));
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Bottom panel with custom Close button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));
        
        JButton btnCerrar = new JButton("CERRAR TELEMETRÍA Y VER GANADOR");
        btnCerrar.setFont(new Font("OCR A Extended", Font.BOLD, 14));
        btnCerrar.setBackground(Color.BLACK);
        btnCerrar.setForeground(new Color(0, 255, 0));
        btnCerrar.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0), 2));
        btnCerrar.setFocusPainted(false);
        btnCerrar.setPreferredSize(new Dimension(350, 45));
        
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrar.setBackground(new Color(0, 255, 0));
                btnCerrar.setForeground(Color.BLACK);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrar.setBackground(Color.BLACK);
                btnCerrar.setForeground(new Color(0, 255, 0));
            }
        });
        btnCerrar.addActionListener(e -> dispose());
        
        bottomPanel.add(btnCerrar);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel crearTablaPanel(String titleName, SoldadoVsZombie solver, Color themeColor) {
        JPanel subPanel = new JPanel(new BorderLayout());
        subPanel.setBackground(Color.BLACK);
        subPanel.setBorder(BorderFactory.createLineBorder(new Color(40, 40, 40), 1));

        // Panel Title label
        JLabel titleLabel = new JLabel(titleName, JLabel.CENTER);
        titleLabel.setFont(new Font("OCR A Extended", Font.BOLD, 15));
        titleLabel.setForeground(themeColor);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        subPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel body with JTable and GraficaPanel side-by-side
        JPanel bodyPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        bodyPanel.setBackground(Color.BLACK);

        String[] columnNames = {"Iter", "x0", "x1", "x2", "f(x2)", "Error Rel"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Double> xHistory = solver.getXHistory();
        if (xHistory != null && xHistory.size() >= 3) {
            for (int j = 1; j < xHistory.size() - 1; j++) {
                double xPrev = xHistory.get(j - 1);
                double xCurr = xHistory.get(j);
                double xNext = xHistory.get(j + 1);
                double yNext = evaluar(xNext, solver);
                double error = Math.abs((xNext - xCurr) / xNext);

                model.addRow(new Object[]{
                        j,
                        xPrev,
                        xCurr,
                        xNext,
                        yNext,
                        error
                });
            }
        }

        // Table with Tooltips for high precision numbers
        JTable table = new JTable(model) {
            @Override
            public String getToolTipText(MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int col = columnAtPoint(e.getPoint());
                if (row > -1 && col > -1) {
                    Object value = getValueAt(row, col);
                    if (value instanceof Double) {
                        return "Valor completo: " + String.format("%.15f", (Double) value);
                    } else if (value != null) {
                        return value.toString();
                    }
                }
                return super.getToolTipText(e);
            }
        };
        
        table.setBackground(new Color(15, 15, 15));
        table.setForeground(Color.WHITE);
        table.setGridColor(new Color(40, 40, 40));
        table.setFont(new Font("Consolas", Font.PLAIN, 11));
        table.setRowHeight(22);
        
        table.getTableHeader().setFont(new Font("OCR A Extended", Font.BOLD, 11));
        table.getTableHeader().setBackground(new Color(25, 25, 25));
        table.getTableHeader().setForeground(themeColor);
        table.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, themeColor));

        // Custom Cell Renderer to format doubles on the fly (showing up to 8 decimals)
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object val, boolean isSel, boolean hasFocus, int r, int c) {
                Component comp = super.getTableCellRendererComponent(t, val, isSel, hasFocus, r, c);
                comp.setBackground(new Color(15, 15, 15));
                if (c == 0) {
                    comp.setForeground(themeColor);
                    setHorizontalAlignment(JLabel.CENTER);
                    setFont(new Font("Consolas", Font.BOLD, 11));
                    if (val != null) {
                        setText(val.toString());
                    }
                } else {
                    comp.setForeground(Color.LIGHT_GRAY);
                    setHorizontalAlignment(JLabel.LEFT);
                    setFont(new Font("Consolas", Font.PLAIN, 11));
                    if (val instanceof Double) {
                        setText(String.format("%.8f", (Double) val));
                    } else if (val != null) {
                        setText(val.toString());
                    }
                }
                return comp;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.BLACK);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 1));
        bodyPanel.add(scrollPane);

        // Add the graph of the function and the points path side-by-side
        GraficaRacePanel graphPanel = new GraficaRacePanel(solver, themeColor, "Gráfica de la Secante");
        if (xHistory != null) {
            graphPanel.setCurrentIteration(xHistory.size()); // Render full convergence path
        }
        bodyPanel.add(graphPanel);

        subPanel.add(bodyPanel, BorderLayout.CENTER);

        return subPanel;
    }

    private double evaluar(double x, SoldadoVsZombie solver) {
        int nEcuacion = solver.getNum();
        double c1 = solver.getC1();
        double c2 = solver.getC2();
        double c3 = solver.getC3();
        double c4 = solver.getC4();
        double c5 = solver.getC5();
        double c6 = solver.getC6();
        double e1 = solver.getE1();
        double e2 = solver.getE2();
        double e3 = solver.getE3();
        double e4 = solver.getE4();
        double e5 = solver.getE5();
        double e6 = solver.getE6();
        String base = solver.getBase();

        switch (nEcuacion) {
            case 1: return Math.pow(x, 3) - 2 * x - 5;
            case 2: return Math.exp(x) - 3 * Math.pow(x, 2);
            case 3: return Math.sin(x) - Math.pow(x, 2) + 2 * x + 1;
            case 4: return Math.log(x) - 2 * x + 3;
            case 5: return Math.sqrt(x) - 2;
            case 6: return Math.pow(x, 2) - 4;
            case 7: return c1 * Math.pow(x, 5) + c2 * Math.pow(x, 4) + c3 * Math.pow(x, 3) + c4 * Math.pow(x, 2) + c5 * x + c6;
            case 8: return Math.pow(c1, e1 * x + e2) + Math.pow(c2, e3 * x + e4) + Math.pow(c3, e5 * x + e6);
            case 9: return c1 * Math.sin(c2 * x + c3) + c4;
            case 10: return c1 * Math.cos(c2 * x + c3) + c4;
            case 11: return c1 * Math.tan(c2 * x + c3) + c4;
            case 12: 
                if ("e".equals(base)) {
                    return c1 * Math.log(c2 * x + c3) + c4;
                } else {
                    return c1 * Math.log10(c2 * x + c3) + c4;
                }
            default: return 0;
        }
    }
}
