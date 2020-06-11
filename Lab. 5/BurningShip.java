import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;
    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -1;
        range.y = -1.5;
        range.width = 2;
        range.height = 2;
    }

    @Override
    public int numIterations(double x, double y) {
        int iter = 0;
        double real = 0;
        double notreal = 0;

        while (iter < MAX_ITERATIONS && real*real+notreal*notreal<4)
        {
            double real2 = real*real - notreal*notreal + x;
            double notreal2 = 2 * Math.abs(real) * Math.abs(notreal) + y;
            real = real2;
            notreal = notreal2;
            iter+=1;
        }
        if (iter == MAX_ITERATIONS)
            return -1;

        return iter;
    }
    public String toString()
    {
        return "Burning Ship";
    }
}
