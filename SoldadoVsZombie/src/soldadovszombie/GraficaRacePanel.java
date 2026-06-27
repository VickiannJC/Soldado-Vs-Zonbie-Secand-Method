package soldadovszombie;

import java.awt.*;
import javax.swing.*;
import java.util.List;

public class GraficaRacePanel extends JPanel {
    private SoldadoVsZombie solver;
    private Color playerColor;
    private String title;
    private double currentIteration = 0.0;
    
    // Bounds for auto-fitting
    private double xMinVal = -5.0;
    private double xMaxVal = 5.0;
    private double yMinVal = -5.0;
    private double yMaxVal = 5.0;
    
    public GraficaRacePanel(SoldadoVsZombie solver, Color playerColor, String title) {
        this.solver = solver;
        this.playerColor = playerColor;
        this.title = title;
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        
        // Calculate auto-fit bounds initially based on full history
        calculateBounds();
    }
    
    public void setCurrentIteration(double currentIteration) {
        this.currentIteration = currentIteration;
        repaint();
    }
    
    private void calculateBounds() {
        if (solver == null) return;
        List<Double> xHistory = solver.getXHistory();
        if (xHistory == null || xHistory.isEmpty()) return;
        
        double minX = Double.MAX_VALUE;
        double maxX = -Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = -Double.MAX_VALUE;
        
        // Include points
        for (double x : xHistory) {
            if (x < minX) minX = x;
            if (x > maxX) maxX = x;
            double y = evaluar(x);
            if (y < minY) minY = y;
            if (y > maxY) maxY = y;
        }
        
        // Add padding to ensure visual space, with a tiny minimum range to allow deep zoom
        double rangeX = maxX - minX;
        if (rangeX < 0.05) rangeX = 0.05; // allow deep zoom but prevent division by zero or overlap
        double paddingX = rangeX * 0.25;
        xMinVal = minX - paddingX;
        xMaxVal = maxX + paddingX;
        
        double rangeY = maxY - minY;
        if (rangeY < 0.05) rangeY = 0.05;
        double paddingY = rangeY * 0.25;
        yMinVal = minY - paddingY;
        yMaxVal = maxY + paddingY;
    }
    
    private double evaluar(double x) {
        if (solver == null) return 0;
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
            case 1: 
                return Math.pow(x, 3) - 2 * x - 5;
            case 2: 
                return Math.exp(x) - 3 * Math.pow(x, 2);
            case 3: 
                return Math.sin(x) - Math.pow(x, 2) + 2 * x + 1;
            case 4: 
                return Math.log(x) - 2 * x + 3;
            case 5: 
                return Math.sqrt(x) - 2;
            case 6: 
                return Math.pow(x, 2) - 4;
            case 7: 
                return c1 * Math.pow(x, 5) + c2 * Math.pow(x, 4) + c3 * Math.pow(x, 3) + c4 * Math.pow(x, 2) + c5 * x + c6;
            case 8: 
                return Math.pow(c1, e1 * x + e2) + Math.pow(c2, e3 * x + e4) + Math.pow(c3, e5 * x + e6);
            case 9: 
                return c1 * Math.sin(c2 * x + c3) + c4;
            case 10: 
                return c1 * Math.cos(c2 * x + c3) + c4;
            case 11: 
                return c1 * Math.tan(c2 * x + c3) + c4;
            case 12: 
                if ("e".equals(base)) {
                    return c1 * Math.log(c2 * x + c3) + c4;
                } else {
                    return c1 * Math.log10(c2 * x + c3) + c4;
                }
            default: 
                return 0;
        }
    }
    
    private int toScreenX(double x) {
        double pct = (x - xMinVal) / (xMaxVal - xMinVal);
        return (int) (20 + pct * (getWidth() - 40));
    }
    
    private int toScreenY(double y) {
        double pct = (y - yMinVal) / (yMaxVal - yMinVal);
        return (int) (getHeight() - 25 - pct * (getHeight() - 50));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // Turn on antialiasing for smooth lines and text
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        // Recalculate bounds to handle panel resize/updates
        calculateBounds();
        
        // Draw elegant grid lines and labels
        g2.setColor(new Color(235, 235, 235));
        g2.setStroke(new BasicStroke(1.0f));
        g2.setFont(new Font("OCR A Extended", Font.PLAIN, 9));
        // Draw horizontal grid lines
        for (int i = 1; i < 10; i++) {
            double yVal = yMinVal + i * (yMaxVal - yMinVal) / 10.0;
            int sy = toScreenY(yVal);
            g2.drawLine(0, sy, getWidth(), sy);
            
            // Draw numerical label on the left edge (every 2 grid lines)
            if (i % 2 == 0) {
                g2.setColor(new Color(130, 130, 130));
                g2.drawString(String.format("%.2f", yVal), 5, sy + 3);
                g2.setColor(new Color(235, 235, 235)); // switch back for next grid lines
            }
        }
        // Draw vertical grid lines
        for (int i = 1; i < 10; i++) {
            double xVal = xMinVal + i * (xMaxVal - xMinVal) / 10.0;
            int sx = toScreenX(xVal);
            g2.drawLine(sx, 0, sx, getHeight());
            
            // Draw numerical label on the bottom edge (every 2 grid lines)
            if (i % 2 == 0) {
                g2.setColor(new Color(130, 130, 130));
                g2.drawString(String.format("%.2f", xVal), sx - 12, getHeight() - 10);
                g2.setColor(new Color(235, 235, 235));
            }
        }
        
        // Draw primary axes
        g2.setColor(new Color(150, 150, 150));
        g2.setStroke(new BasicStroke(1.5f));
        if (yMinVal <= 0 && yMaxVal >= 0) {
            int y0 = toScreenY(0);
            g2.drawLine(0, y0, getWidth(), y0);
            // X-axis label
            g2.setFont(new Font("OCR A Extended", Font.PLAIN, 10));
            g2.drawString("X", getWidth() - 15, y0 - 5);
        }
        if (xMinVal <= 0 && xMaxVal >= 0) {
            int x0 = toScreenX(0);
            g2.drawLine(x0, 0, x0, getHeight());
            // Y-axis label
            g2.setFont(new Font("OCR A Extended", Font.PLAIN, 10));
            g2.drawString("Y", x0 + 5, 15);
        }
        
        // Draw function curve
        g2.setColor(new Color(230, 50, 50)); // Modern red
        g2.setStroke(new BasicStroke(2.0f));
        int prevPx = -1, prevPy = -1;
        for (int i = 0; i <= 200; i++) {
            double x = xMinVal + i * (xMaxVal - xMinVal) / 200.0;
            double y = evaluar(x);
            int px = toScreenX(x);
            int py = toScreenY(y);
            if (i > 0 && Math.abs(py - prevPy) < getHeight() * 2) {
                g2.drawLine(prevPx, prevPy, px, py);
            }
            prevPx = px;
            prevPy = py;
        }
        
        // Draw the secant steps and iterations
        if (solver == null) return;
        List<Double> xHistory = solver.getXHistory();
        if (xHistory == null || xHistory.isEmpty()) return;
        
        int maxPoints = (int) Math.min(Math.floor(currentIteration) + 2, xHistory.size());
        
        // Setup strokes
        float dashPattern[] = {4.0f};
        BasicStroke dashed = new BasicStroke(1.2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dashPattern, 0.0f);
        BasicStroke solidSecant = new BasicStroke(1.5f);
        
        // 1. Draw completed secant lines and projections
        for (int j = 1; j < maxPoints - 1; j++) {
            double xPrev = xHistory.get(j - 1);
            double yPrev = evaluar(xPrev);
            double xCurr = xHistory.get(j);
            double yCurr = evaluar(xCurr);
            double xNext = xHistory.get(j + 1);
            double yNext = evaluar(xNext);
            
            int pPrevX = toScreenX(xPrev);
            int pPrevY = toScreenY(yPrev);
            int pCurrX = toScreenX(xCurr);
            int pCurrY = toScreenY(yCurr);
            int pNextX = toScreenX(xNext);
            int pNextY = toScreenY(0);
            int pNextCurveY = toScreenY(yNext);
            
            // Draw secant line extending from x_prev to x_next on the X-axis
            g2.setStroke(solidSecant);
            g2.setColor(new Color(playerColor.getRed(), playerColor.getGreen(), playerColor.getBlue(), 120));
            g2.drawLine(pPrevX, pPrevY, pNextX, pNextY);
            
            // Draw projection to curve (vertical dashed line)
            g2.setStroke(dashed);
            g2.setColor(new Color(100, 100, 100, 150));
            g2.drawLine(pNextX, pNextY, pNextX, pNextCurveY);
        }
        
        // 2. Draw active step animation (fractional part of iteration)
        int activeIdx = (int) Math.floor(currentIteration);
        if (activeIdx >= 0 && activeIdx + 2 < xHistory.size()) {
            double t = currentIteration - activeIdx;
            
            double xPrev = xHistory.get(activeIdx);
            double yPrev = evaluar(xPrev);
            double xCurr = xHistory.get(activeIdx + 1);
            double yCurr = evaluar(xCurr);
            double xNext = xHistory.get(activeIdx + 2);
            double yNext = evaluar(xNext);
            
            int pPrevX = toScreenX(xPrev);
            int pPrevY = toScreenY(yPrev);
            int pCurrX = toScreenX(xCurr);
            int pCurrY = toScreenY(yCurr);
            int pNextX = toScreenX(xNext);
            int pNextY = toScreenY(0);
            int pNextCurveY = toScreenY(yNext);
            
            if (t < 0.5) {
                // Animate secant line growing from P_curr to (x_next, 0)
                double tLine = t * 2.0;
                int targetX = (int) (pCurrX + tLine * (pNextX - pCurrX));
                int targetY = (int) (pCurrY + tLine * (pNextY - pCurrY));
                
                g2.setStroke(solidSecant);
                g2.setColor(new Color(playerColor.getRed(), playerColor.getGreen(), playerColor.getBlue(), 180));
                g2.drawLine(pPrevX, pPrevY, targetX, targetY);
            } else {
                // Secant line complete, animate projection growing from (x_next, 0) to P_next
                g2.setStroke(solidSecant);
                g2.setColor(new Color(playerColor.getRed(), playerColor.getGreen(), playerColor.getBlue(), 120));
                g2.drawLine(pPrevX, pPrevY, pNextX, pNextY);
                
                double tProj = (t - 0.5) * 2.0;
                int targetCurveY = (int) (pNextY + tProj * (pNextCurveY - pNextY));
                
                g2.setStroke(dashed);
                g2.setColor(new Color(80, 80, 80, 180));
                g2.drawLine(pNextX, pNextY, pNextX, targetCurveY);
            }
        }
        
        // 3. Draw dots and labels on the curve for all available points
        g2.setStroke(new BasicStroke(1.5f));
        for (int j = 0; j < maxPoints; j++) {
            double xj = xHistory.get(j);
            double yj = evaluar(xj);
            int px = toScreenX(xj);
            int py = toScreenY(yj);
            
            // Draw filled dot
            g2.setColor(playerColor);
            g2.fillOval(px - 5, py - 5, 10, 10);
            g2.setColor(Color.WHITE);
            g2.drawOval(px - 5, py - 5, 10, 10);
            
            // Label
            g2.setFont(new Font("OCR A Extended", Font.BOLD, 11));
            g2.setColor(new Color(50, 50, 50));
            
            String coordText = String.format("x%d=%.2f", j, xj);
            
            int offsetX = 8;
            int offsetY = (j % 2 == 0) ? -6 : 14;
            g2.drawString(coordText, px + offsetX, py + offsetY);
        }
        
        // Draw title/legend at top
        g2.setColor(new Color(30, 30, 30));
        g2.setFont(new Font("OCR A Extended", Font.BOLD, 13));
        g2.drawString(title, 15, 20);
        
        // Draw current approximation details if available
        if (maxPoints > 0) {
            double currentX = xHistory.get(maxPoints - 1);
            double currentY = evaluar(currentX);
            g2.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
            g2.setColor(new Color(100, 100, 100));
            String rootDetails = String.format("Aprox. Actual: x=%.4f (f(x)=%.4f)", currentX, currentY);
            g2.drawString(rootDetails, 15, getHeight() - 8);
        }
    }
}
