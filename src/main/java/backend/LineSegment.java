
package backend;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import org.json.simple.JSONObject;

public class LineSegment extends AbstractShapeClass{

    private Point p2;
    
    public LineSegment(Point p1, Point p2){
        super(p1);
        this.setSecondPoint(p2);
    } 
    
    public void setSecondPoint(Point p2){
        this.p2 = p2;
    }
    
    public Point getSecondPoint(){
        return p2;
    }
    
    @Override
    public boolean contains(Point p){
        int[]xPoints = new int[5];
        int[]yPoints = new int[5];
        
        xPoints[0] = getPosition().x - 3;
        xPoints[1] = getPosition().x + 3;
        xPoints[2] = getSecondPoint().x - 3;
        xPoints[3] = getSecondPoint().x + 3;
        
        yPoints[0] = getPosition().y;
        yPoints[1] = getPosition().y;
        yPoints[2] = getSecondPoint().y;
        yPoints[3] = getSecondPoint().y;
        
        
        Polygon l = new Polygon(xPoints, yPoints, 4);
        
        if(l.contains(p))
            return true;
        else
            return false;
    }
    
    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(getColor());
        canvas.drawLine(getPosition().x, getPosition().y, getSecondPoint().x, getSecondPoint().y);
    }

    @Override
    public void moveTo(Point newPoint) {
        int xMoved = newPoint.x - getDraggingPoint().x;
        int yMoved = newPoint.y - getDraggingPoint().y;
        
        setPosition(new Point(getPosition().x + xMoved, getPosition().y + yMoved));
        setSecondPoint(new Point(p2.x + xMoved, p2.y + yMoved));
    }
        @Override
    public Shape[] generateCornerMarkers() {
        Shape[]corners = new Shape[2];
        corners[0] = new Rectangle(6,6, getPosition());
        corners[1] = new Rectangle(6,6, getSecondPoint());
        
        for(Shape s: corners){
            s.setColor(Color.black);
            s.setFillColor(Color.black);
        }
        return corners;
    }
     @Override
    public JSONObject toJson() {
        JSONObject new_line = new JSONObject();
        new_line.put("x1",super.getPosition().x);
        new_line.put("y1",super.getPosition().y);
        new_line.put("x2",getSecondPoint().x);
        new_line.put("y2",getSecondPoint().y);
        String hexBorderColor = "#"+Integer.toHexString(getColor().getRGB()).substring(2);
        new_line.put("borderColor",hexBorderColor);
        //String hexFillColor = "#"+Integer.toHexString(getFillColor().getRGB()).substring(2);
        //new_line.put("fillColor",hexFillColor);
        new_line.put("type", "Line");
        new_line.put("name", super.getName());

        return new_line;
    }
        public static Shape jsonToShape(JSONObject shapeJson) {
       Point p1 = new Point((int)(long)shapeJson.get("x1"),(int)(long) shapeJson.get("y1"));
       Point p2 = new Point((int)(long)shapeJson.get("x2"),(int)(long) shapeJson.get("y2"));
       // Color fillColor=Color.decode(shapeJson.get("fillColor").getAsString()) ;
        Color borderColor=Color.decode((String)shapeJson.get("borderColor")) ;
        LineSegment newLine = new LineSegment(p1, p2);
        newLine.setColor(borderColor);
       String name = (String)shapeJson.get("name");
        newLine.setName(name);
        return newLine;
      
    }
    
}
