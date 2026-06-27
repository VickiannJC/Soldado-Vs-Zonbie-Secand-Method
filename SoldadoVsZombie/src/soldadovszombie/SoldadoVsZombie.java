package soldadovszombie;

import java.util.List;
import java.util.ArrayList;

public class SoldadoVsZombie {

    private double result;
    private int num;
    private double x0, x1, error, c1, c2, c3, c4, c5, c6, e1, e2, e3, e4, e5, e6;
    private String base;
    private List<Double> xHistory = new ArrayList<>();
    private boolean convergio = true;

    public SoldadoVsZombie(int num, double x0, double x1, double error) {
        this.num = num;
        this.x0 = x0;
        this.x1 = x1;
        this.error = error;
    }
    
    public SoldadoVsZombie(int num, double x0, double x1, double error, double c1, double c2, double c3, double c4, double c5, double c6) {
        this.num = num;
        this.x0 = x0;
        this.x1 = x1;
        this.error = error;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.c5 = c5;
        this.c6 = c6;
    }

    public SoldadoVsZombie(int num, double x0, double x1, double error, double c1, double c2, double c3, double e1, double e2, double e3, double e4, double e5, double e6) {
        this.num = num;
        this.x0 = x0;
        this.x1 = x1;
        this.error = error;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
        this.e4 = e4;
        this.e5 = e5;
        this.e6 = e6;
    }

    public SoldadoVsZombie(int num, double x0, double x1, double error, double c1, double c2, double c3, double c4) {
        this.num = num;
        this.x0 = x0;
        this.x1 = x1;
        this.error = error;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
    }

    public SoldadoVsZombie(int num, double x0, double x1, double error, String base, double c1, double c2, double c3, double c4) {
        this.num = num;
        this.x0 = x0;
        this.x1 = x1;
        this.error = error;
        this.base = base;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
    }

    public double getResult() {
        System.out.println("Ger result " + result);
        return result;      
    }

    public void setResult(double result) {
        this.result = result;
    }

    public boolean getConvergio() {
        return convergio;
    }

    public boolean tieneRaicesEnRegion() {
        double minX = Math.min(x0, x1) - 10;
        double maxX = Math.max(x0, x1) + 10;
        
        // Guard against negative inputs for log or sqrt functions
        if (num == 4 || num == 5 || num == 12) {
            minX = Math.max(0.0001, minX);
        }

        double step = (maxX - minX) / 200.0;
        boolean allPositive = true;
        boolean allNegative = true;
        double minAbsVal = Double.MAX_VALUE;

        for (int i = 0; i <= 200; i++) {
            double x = minX + i * step;
            double fx = evaluar(x);
            if (Double.isNaN(fx) || Double.isInfinite(fx)) continue;

            if (fx > 0) {
                allNegative = false;
            } else if (fx < 0) {
                allPositive = false;
            } else {
                return true; // Found exact root
            }
            
            double absFx = Math.abs(fx);
            if (absFx < minAbsVal) {
                minAbsVal = absFx;
            }
        }

        // If it changes sign, it definitely has a root (Intermediate Value Theorem)
        if (!allPositive && !allNegative) {
            return true;
        }

        // If f(x) never crossed or approached 0 closely, we suspect no roots
        return minAbsVal <= 0.05;
    }

    public double evaluar(double x) {
        switch (num) {
            case 1: return Math.pow(x, 3) - (2 * x) - 5;
            case 2: return Math.exp(x) - 3 * Math.pow(x, 2);
            case 3: return Math.sin(x) - Math.pow(x, 2) + (2 * x) + 1;
            case 4: return Math.log(x) - (2 * x) + 3;
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

    public void resolverEc() {
        xHistory.clear();
        xHistory.add(x0);
        xHistory.add(x1);
        convergio = true;

        double fx0 = evaluar(x0);
        double fx1 = evaluar(x1);

        double iter = 0;
        double[] aux = MSecanteF(x0, x1, fx0, fx1, iter);

        int iterCount = 0;
        // Run loop until target error is reached, with a hard limit of 100 iterations to avoid infinite loops,
        // and checks for NaN or Infinity values to stop on divergence/indeterminate states.
        while (aux[4] > error && iterCount < 100 && !Double.isNaN(aux[4]) && !Double.isInfinite(aux[1]) && !Double.isNaN(aux[1])) {
            double fx2 = evaluar(aux[1]);
            aux = MSecanteF(aux[0], aux[1], aux[2], fx2, aux[3]);
            iterCount++;
        }

        this.result = aux[3];
        
        // Mark convergence as false if it exceeded limit, error is NaN, or root is NaN/Infinity
        if (iterCount >= 100 || Double.isNaN(aux[1]) || Double.isInfinite(aux[1]) || Double.isNaN(aux[4]) || aux[4] > error) {
            this.convergio = false;
        }
    }

    public double[] MSecanteF(double x0, double x1, double fx0, double fx1, double iter) {
        double x2;
        double[] r = new double[5];

        // Guard against division by zero (e.g. flat slopes where fx1 == fx0)
        if (fx1 - fx0 == 0) {
            x2 = Double.NaN;
        } else {
            x2 = x0 - (((x1 - x0) / (fx1 - fx0)) * fx0);
        }

        xHistory.add(x2);
        
        double errorValue;
        if (x2 == 0 || Double.isNaN(x2)) {
            errorValue = Double.NaN;
        } else {
            errorValue = Math.abs((x2 - x1) / x2);
        }
        
        iter++;
        
        r[0] = x1;
        r[1] = x2;
        r[2] = fx1;
        r[3] = iter;
        r[4] = errorValue;

        System.out.println("iteracion " + iter);
        System.out.println("error " + errorValue);
        System.out.println("x0 " + x0);
        System.out.println("x1 " + x1);
        System.out.println("x2 " + x2);
        System.out.println("********");

        return r;
    }

    public List<Double> getXHistory() { return xHistory; }
    public int getNum() { return num; }
    public double getX0() { return x0; }
    public double getX1() { return x1; }
    public double getError() { return error; }
    public double getC1() { return c1; }
    public double getC2() { return c2; }
    public double getC3() { return c3; }
    public double getC4() { return c4; }
    public double getC5() { return c5; }
    public double getC6() { return c6; }
    public double getE1() { return e1; }
    public double getE2() { return e2; }
    public double getE3() { return e3; }
    public double getE4() { return e4; }
    public double getE5() { return e5; }
    public double getE6() { return e6; }
    public String getBase() { return base; }
}
