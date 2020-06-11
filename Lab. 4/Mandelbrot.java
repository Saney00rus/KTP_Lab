import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;
    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -1.5;
        range.width = 3;
        range.height = 3;
    }

    @Override
    public int numIterations(double x, double y) {
        int iter = 0;
        double real = 0;
        double notreal = 0;

        while (iter < MAX_ITERATIONS && real*real+notreal*notreal<4)
        {
            double real2 = real*real - notreal*notreal + x;
            double notreal2 = 2 * real * notreal + y;
            real = real2;
            notreal = notreal2;
            iter+=1;
        }
        if (iter == MAX_ITERATIONS)
            return -1;

        return iter;
    }
}
