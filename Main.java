package homework;

     class Point
{
       private double x_cord, y_cord;
    
    public Point(double x, double y)
    {
        this.x_cord = x;
        this.y_cord = y;    }
    
    public double getX()
    {
        return x_cord;
    }
    
    public double getY()
    {
        return y_cord;
    }
    
    public String toString()
    {
        return "Coordintes: "+"X_Cord: "+x_cord+"\nY_Cord: "+y_cord+"\n";
    }}

   class Quadrilateral
          {
            private Point P1, P2, P3, P4;
   
    public Quadrilateral(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4)
    {
        P1 = new Point(x1, y1);
        P2 = new Point(x2, y2);
        P3 = new Point(x3, y3);
        P4 = new Point(x4, y4);    }
   
    public Point getP1()
    {
        return P1;
    }

    public Point getP2()
    {
        return P2;
    }
    
    public Point getP3()
    {
        return P3;
    }
    
    public Point getP4()
    {
        return P4;
    }
    
    //return points 
    public String toString()
    {
        return "\nPoints: "+P1+" "+P2+" "+P3+" "+P4+" "+"\n";
    }}


class Trapezoid extends Quadrilateral
{
    private double height, base1, base2;
    
    public Trapezoid(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4)
    {
        super(x1, y1, x2, y2, x3, y3, x4, y4);
        height = Math.abs(x1 - x2);
        base1 = y2 - y3;
        base2 = y1 - y4;
    }
    
    public double area()
    {
        return Math.abs(0.5 * (base1 + base2) * height);
    }
    
    public String toString()
    {
        return "\nTrapezoid:\n"+super.toString()+"height: "+height+" base1: "+base1+" Base2: "+base2+" Area:"+area();
    }}

class Parallelogram extends Trapezoid
{
    private double height, width;
    
    public Parallelogram(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4)
    {
        super(x1, y1, x2, y2, x3, y3, x4, y4);
        width = Math.sqrt(Math.pow((getP2().getX() - getP1().getX()), 2) + Math.pow((getP2().getY() - getP1().getY()), 2));
        height = Math.sqrt(Math.pow((getP4().getX() - getP1().getX()), 2) + Math.pow((getP4().getY() - getP1().getY()), 2));
    }
    
    public double area()
    {
        return height*width;
    }
    
    public String toString()
    {
        return "\nParallelogram: \n"+super.toString()+" Area:"+area();
    }    
    
}

class Rectangle extends Parallelogram
{
    private double height, length;
    
    public Rectangle(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4)
    {
        super(x1, y1, x2, y2, x3, y3, x4, y4);
        length = Math.sqrt(Math.pow((getP2().getX() - getP1().getX()), 2) + Math.pow((getP2().getY() - getP1().getY()), 2));
        height = Math.sqrt(Math.pow((getP4().getX() - getP1().getX()), 2) + Math.pow((getP4().getY() - getP1().getY()), 2));
    }
    
    public double area()
    {
        return height*length;
    }
    
    public String toString()
    {
        return "\nRectangle: \n"+super.toString()+" height: "+height+" Length: "+length+" Area:"+area();
    }    
}

class Square extends Rectangle
{
    private double side;
    
    public Square(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4)
    {
        super(x1, y1, x2, y2, x3, y3, x4, y4);
        side = Math.sqrt(Math.pow((getP2().getX() - getP1().getX()), 2) + Math.pow((getP2().getY() - getP1().getY()), 2));
    }
    
    public double area()
    {
        return side * 4;
    }

    public String toString()
    {
        return "\nSquare: \n"+super.toString()+" Sides: "+side+" Area:"+area();
    }      }

public class Main {

	public static void main(String[] args) {
	
		Quadrilateral Q[] = {
		        new Trapezoid(1.2, 1.4, 5.6, 3.8, 4.2, 8.9, 1.2, 4.1),
		        new Parallelogram(4.0, 2.0, 7.0, 5.0, 2.0, 3.0, 7.0, 1.0),
		        new Rectangle(7.0, 4.0, 3.0, 4.0, 3.0, 2.0, 1.0, 2.0),
		        new Square(4.0, 0.0, 8.0, 0.0, 8.0, 4.0, 4.0, 4.0) };
		        
		        for(int i=0;i<Q.length;i++)
		        {
		            System.out.println(Q[i]+" ");
		        }
	}

}
