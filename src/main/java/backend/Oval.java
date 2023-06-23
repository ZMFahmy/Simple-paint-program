
package backend;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import org.json.simple.JSONObject;


public class Oval extends AbstractShapeClass {

    private int horizontalDiameter;
    private int verticalDiameter;
    private Point center;
    
    public Oval(Point p, int horizontalDiamater, int verticalDiameter){
        super(p);
        this.horizontalDiameter = horizontalDiamater;
        this.verticalDiameter = verticalDiameter;
        this.setCenter(this.getPosition());
    }
   
    public void setHorizontalDiameter(int horizontalDiameter){
        this.horizontalDiameter = horizontalDiameter;
    }
    
    public void setVerticalDiameter(int verticalDiameter){
        this.verticalDiameter = verticalDiameter;
    }
    
    public int getHorizontalDiameter(){
        return horizontalDiameter;
    }
    
    public int getVerticalDiameter(){
        return verticalDiameter;
    }
    
    public void setCenter(Point position) {
        
        this.center = new Point (this.getPosition().x + horizontalDiameter/2, this.getPosition().y + verticalDiameter/2);
    }

    public Point getCenter() {
        return center;
    }
    
    @Override
    public boolean contains(Point p){
        Ellipse2D e = new Ellipse2D.Double(getPosition().x, getPosition().y, horizontalDiameter, verticalDiameter);
        if(e.contains(p.x, p.y))
            return true;
        else
            return false;
    }
    
    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(getColor());
        canvas.drawOval(getPosition().x, getPosition().y, horizontalDiameter, verticalDiameter);
        canvas.setColor(getFillColor());
        canvas.fillOval(getPosition().x, getPosition().y, horizontalDiameter, verticalDiameter);
    }

    @Override
    public void moveTo(Point newPoint) {
        int xMoved = newPoint.x - getDraggingPoint().x;
        int yMoved = newPoint.y - getDraggingPoint().y;
        
        setPosition(new Point(getPosition().x + xMoved, getPosition().y + yMoved));
        setCenter(getPosition());
    }
    @Override
    public Shape[] generateCornerMarkers() {
        Shape[]corners = new Shape[4];
        corners[0] = new Rectangle(5,5, getPosition());
        corners[1] = new Rectangle(5,5, new Point(getPosition().x + horizontalDiameter, getPosition().y));
        corners[2] = new Rectangle(5,5, new Point(getPosition().x, getPosition().y + verticalDiameter));
        corners[3] = new Rectangle(5,5, new Point(getPosition().x + horizontalDiameter, getPosition().y + verticalDiameter));
        
        for(Shape s: corners){
            s.setColor(Color.black);
            s.setFillColor(Color.black);
        }
        
        return corners;
    }
    
    public void increaseHorizontalDiameter(){
        this.horizontalDiameter ++ ;
    }
    public void increaseVerticalDiameter(){
        this.verticalDiameter ++ ;
    }
        @Override
    public JSONObject toJson() {
        JSONObject new_circle = new JSONObject();
        new_circle.put("x1",super.getPosition().x);
        new_circle.put("y1",super.getPosition().y);
        new_circle.put("verticalDiameter",verticalDiameter);
        new_circle.put("horizontalDiameter",horizontalDiameter);
        String hexBorderColor = "#"+Integer.toHexString(getColor().getRGB()).substring(2);
        new_circle.put("borderColor",hexBorderColor);
        String hexFillColor = "#"+Integer.toHexString(getFillColor().getRGB()).substring(2);
        new_circle.put("fillColor",hexFillColor);
        new_circle.put("type", "Circle");
        new_circle.put("name", super.getName());

        return new_circle;
    }
    public static Shape jsonToShape(JSONObject shapeJson) {
        Point p1 = new Point((int)(long)shapeJson.get("x1"),(int)(long) shapeJson.get("y1"));
        int DiameterV = (int)(long) shapeJson.get("verticalDiameter");
        int DiameterH = (int)(long) shapeJson.get("horizontalDiameter");
        Oval newCircle= new Oval(p1, DiameterH, DiameterV);
        Color fillColor=Color.decode((String)shapeJson.get("fillColor")) ;
        Color borderColor=Color.decode((String)shapeJson.get("borderColor")) ;
        newCircle.setColor(borderColor);
        newCircle.setFillColor(fillColor);
        String name = (String)shapeJson.get("name");
        newCircle.setName(name);
        return newCircle;
    }
    
}
