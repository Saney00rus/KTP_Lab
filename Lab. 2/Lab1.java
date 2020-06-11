import java.util.Scanner;
public class Lab1 {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter point 1: ");
        double x = input.nextDouble();
        double y = input.nextDouble();
        double z = input.nextDouble();
        Point3d myPoint = new Point3d(x,y,z);

        Scanner input1 = new Scanner(System.in);
        System.out.println("Enter point 2: ");
        double x1 = input1.nextDouble();
        double y1 = input1.nextDouble();
        double z1 = input1.nextDouble();
        Point3d myPoint1 = new Point3d(x1,y1,z1);

        Scanner input2 = new Scanner(System.in);
        System.out.println("Enter point 3: ");
        double x2 = input2.nextDouble();
        double y2 = input2.nextDouble();
        double z2 = input2.nextDouble();
        Point3d myPoint2 = new Point3d(x2,y2,z2);

        System.out.println(computeArea(myPoint,myPoint1,myPoint2));
    }

    static double computeArea(Point3d myPoint, Point3d myPoint1, Point3d myPoint2)
    {
        double a,b,c,S,P;
        a = myPoint.distanceTo(myPoint1);
        b = myPoint.distanceTo(myPoint2);
        c = myPoint1.distanceTo(myPoint2);
        P = (a+b+c)/2;
        S = Math.sqrt(P*(P-a)*(P-b)*(P-c));
        return (double) Math.round(S*100)/100;
    }

}