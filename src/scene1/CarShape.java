package scene1;
import java.awt.*;
import java.awt.geom.*;

/**
   A car shape.
*/
public class CarShape extends SelectableShape
{
   /**
      Constructs a car shape.
      @param x the left of the bounding rectangle
      @param y the top of the bounding rectangle
      @param width the width of the bounding rectangle
   */
   public CarShape(int x, int y, int width)
   {
      this.x = x;
      this.y = y;
      this.width = width;
      unit=width/6;
   }

   public void draw(Graphics2D g2){
	   //DRAW A PICTURE FOR UNDERSTANDING
	   
	   //The left side of the car body
	   int bodyLeft=x;
	   //the left side of the left window
	   int leftWindow = x + unit;
	   //the right side of the left window == the left side of the roof
	   int  roofLeft = leftWindow+unit;
	   //the right side of the roof == the left side of the right window
	   int roofRight = roofLeft+2*unit;
	   //the right side of the right window
	   int rightWindow= roofRight+unit;
	   //
	   //onto wheels 
	   //left side of left wheel
	   int leftWheelLeft = leftWindow;
	   //left side of right wheel -- unit compensates
	   //since we're on the left side now
	   int rightWheelLeft = roofRight;
	   
	   //Y axis
	   //the top of the roof
	   int roofTop=y;
	   //the top of the body 
	   //== the bottom of the roof window thigny
	   int bodyTop = y+unit;
	   //the bottom of the body 
	   //== the top of the wheel
	   int wheelTop = bodyTop+unit;
	   int wheelDiameter = unit;
	   
	   
	   //LET'S DRAW SOME COOL STUFF!
	   //the body
	   Rectangle2D.Double body
	   	= new Rectangle2D.Double(bodyLeft, bodyTop, width , unit);
	   
	   //the wheels
	   Ellipse2D.Double frontTire
       	= new Ellipse2D.Double(leftWheelLeft,wheelTop,wheelDiameter,wheelDiameter );
	   Ellipse2D.Double rearTire
       	= new Ellipse2D.Double(rightWheelLeft,wheelTop,wheelDiameter,wheelDiameter);
	   
	   //the ugly roof thing
	   Point2D.Double leftWindowBasePoint= new Point2D.Double(leftWindow,bodyTop);
	   
	   //moving left to right in the picture
	   Point2D.Double roofLeftPoint= new Point2D.Double(roofLeft,roofTop);
	   
	   //the right side of roof
	   Point2D.Double roofRightPoint= new Point2D.Double(roofRight,roofTop);
	   
	   //finally
	   Point2D.Double rightWindowBasePoint = new Point2D.Double(rightWindow,bodyTop);
	   
	   //now turn these into lines
	   Line2D.Double leftWindowLine = new Line2D.Double(leftWindowBasePoint, roofLeftPoint);
	   
	   Line2D.Double roofLine = new Line2D.Double(roofLeftPoint,roofRightPoint);
	   
	   Line2D.Double rightWindowLine = new Line2D.Double(roofRightPoint,rightWindowBasePoint);
	   
	   //AND ACTUALLY DRAWING
	   g2.draw(body);
	   
	   g2.draw(frontTire);
	   g2.draw(rearTire);
	   
	   g2.draw(leftWindowLine);
	   g2.draw(roofLine);
	   g2.draw(rightWindowLine);
   }
   
   
   public void drawSelection(Graphics2D g2)
   {
      Rectangle2D.Double body
         = new Rectangle2D.Double(x, y + width / 6, 
            width - 1, width / 6);
      g2.fill(body);
   }

   public boolean contains(Point2D p)
   {
      return x <= p.getX() && p.getX() <= x + width 
         && y <= p.getY() && p.getY() <= y + width / 2;
   }

   public void translate(int dx, int dy)
   {
      x += dx;
      y += dy;
   }

   private int x;
   private int y;
   private int width;
   private final int unit;
}
