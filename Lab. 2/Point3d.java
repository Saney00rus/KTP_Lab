public class Point3d {
    private double xCoord;
    private double yCoord;
    private double zCoord;
   public Point3d (double x, double y, double z)
    {
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }
    public Point3d()
    {
       this(0,0,0);
    }
    public boolean diffPoint(Point3d point)
    {
        if (xCoord == point.getX() && zCoord == point.getZ() && yCoord == point.getY())
        {
            return true;
        }
        else {
            return false;
        }
    }
    public double distanceTo(Point3d point)
    {
        double dis = 0;
        double q,w,e;
        q = Math.pow((point.getX()-xCoord),2);
        w = Math.pow((point.getY()-yCoord),2);
        e = Math.pow((point.getZ()-zCoord),2);
        dis = Math.sqrt(q + w + e);
        return (double) Math.round(dis*100)/100;
    }



    public double getX()
    {
        return xCoord;
    }

    public double getY()
    {
        return yCoord;
    }

    public double getZ()
    {
        return zCoord;
    }

    public void setX(double val)
    {
        xCoord = val;
    }
    public void setY(double val)
    {
        yCoord = val;
    }
    public void setZ(double val)
    {
        zCoord = val;
    }
}
