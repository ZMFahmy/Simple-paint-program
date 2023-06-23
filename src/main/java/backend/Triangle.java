package backend;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import org.json.simple.JSONObject;


public class Triangle extends AbstractShapeClass{
    private Point p2;
    private Point p3;
   
    
    public Triangle(Point p1, Point p2, Point p3){
        super(p1);
        this.p2 = p2;
        this.p3 = p3;
    }
    
    public void setP2(Point p2){
        this.p2 = p2;
    }
    
    public void setP3(Point p3){
        this.p3 = p3;
    }
    
    public Point getP2(){
        return this.p2;
    }
    
    public Point getP3(){
        return this.p3;
    }

    @Override
    public void draw(Graphics canvas) {
        int[]xPoints = new int[3];
        int[]yPoints = new int[3];
        
        xPoints[0] = getPosition().x;
        xPoints[1] = getP2().x;
        xPoints[2] = getP3().x;
        
        yPoints[0] = getPosition().y;
        yPoints[1] = getP2().y;
        yPoints[2] = getP3().y;
        
        canvas.setColor(getColor());
        canvas.drawPolygon(xPoints, yPoints, 3);
        canvas.setColor(getFillColor());
        canvas.fillPolygon(xPoints, yPoints, 3);
    }
    
    @Override
    public boolean contains(Point p){
        int[]xPoints = new int[3];
        int[]yPoints = new int[3];
        
        xPoints[0] = getPosition().x;
        xPoints[1] = getP2().x;
        xPoints[2] = getP3().x;
        
        yPoints[0] = getPosition().y;
        yPoints[1] = getP2().y;
        yPoints[2] = getP3().y;
        
        Polygon pol = new Polygon(xPoints, yPoints, 3);
        if(pol.contains(p))
            return true;
        else
            return false;
    }
    
    @Override
    public void moveTo(Point newPoint) {
        int xMoved = newPoint.x - getDraggingPoint().x;
        int yMoved = newPoint.y - getDraggingPoint().y;
        
        setPosition(new Point(getPosition().x + xMoved, getPosition().y + yMoved));
        setP2(new Point(p2.x + xMoved, p2.y + yMoved));
        setP3(new Point(p3.x + xMoved, p3.y + yMoved));
    }
      @Override
    public Shape[] generateCornerMarkers() {
        Shape[]corners = new Shape[3];
        corners[0] = new Rectangle(6,6, getPosition());
        corners[1] = new Rectangle(6,6, getP2());
        corners[2] = new Rectangle(6,6, getP3());
        
        for(Shape s: corners){
            s.setColor(Color.black);
            s.setFillColor(Color.black);
        }
        
        return corners;
    }
       @Override
    public JSONObject toJson() {
        JSONObject new_tri = new JSONObject();
       new_tri.put("x1",super.getPosition().x);
        new_tri.put("y1",super.getPosition().y);
        new_tri.put("x2",getP2().x);
        new_tri.put("y2",getP2().y);
        new_tri.put("x3",getP3().x);
        new_tri.put("y3",getP3().y);
        String hexBorderColor = "#"+Integer.toHexString(getColor().getRGB()).substring(2);
        new_tri.put("borderColor",hexBorderColor);
        String hexFillColor = "#"+Integer.toHexString(getFillColor().getRGB()).substring(2);
        new_tri.put("fillColor",hexFillColor);
        new_tri.put("type", "Triangle");
        new_tri.put("name", super.getName());

        return new_tri;
    }
          public static Shape jsonToShape(JSONObject shapeJson) {
        Point p1 = new Point((int)(long)shapeJson.get("x1"),(int)(long) shapeJson.get("y1"));
        Point p2 = new Point((int)(long)shapeJson.get("x2"),(int)(long) shapeJson.get("y2"));
        Point p3 = new Point((int)(long)shapeJson.get("x3"),(int)(long) shapeJson.get("y3"));
        Color fillColor=Color.decode((String)shapeJson.get("fillColor")) ;
        Color borderColor=Color.decode((String)shapeJson.get("borderColor")) ;
        Triangle newTri = new Triangle(p1, p2, p3);
        newTri.setColor(borderColor);
        newTri.setFillColor(fillColor);
        String name = (String)shapeJson.get("name");
        newTri.setName(name);
        return newTri;
    
    }
}
