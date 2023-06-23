
package backend;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import org.json.simple.JSONObject;


public class Rectangle extends AbstractShapeClass{
    private int length;
    private int width;
    
    
    public Rectangle(int length, int width, Point point){
        super(point);
        this.width = width;
        this.length = length;
        
    }
      public Rectangle(int sideLength, Point center){ // For constructing corners
        super(new Point(center.x - sideLength/2, center.y - sideLength/2));
        this.width = sideLength;
        this.length = sideLength;  
    }
        
    public void setLength(int length){
        this.length = length;
    }
    
    public int getLength(){
        return length;
    }
    
    public void setWidth(int width){
        this.width = width;
    }
    
    public int getWidth(){
        return width;
    }
    
    @Override
    public boolean contains(Point p){
        if((p.x - getPosition().x)<=length && p.x>getPosition().x && (p.y - getPosition().y)<=width && p.y>getPosition().y)
            return true;
        else
            return false;
    }
    
    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(getColor());
        canvas.drawRect(getPosition().x, getPosition().y, getLength(), getWidth());
        canvas.setColor(getFillColor());
        canvas.fillRect(getPosition().x, getPosition().y, getLength(), getWidth());
    }

    @Override
    public void moveTo(Point newPoint) {
        int xMoved = newPoint.x - getDraggingPoint().x;
        int yMoved = newPoint.y - getDraggingPoint().y;
        
        setPosition(new Point(getPosition().x + xMoved, getPosition().y + yMoved));
    }
        @Override
    public Shape[] generateCornerMarkers() {
        Shape[]corners = new Shape[4];
        corners[0] = new Rectangle(6, getPosition());
        corners[1] = new Rectangle(6, new Point(getPosition().x + length, getPosition().y));
        corners[2] = new Rectangle(6, new Point(getPosition().x, getPosition().y + width));
        corners[3] = new Rectangle(6, new Point(getPosition().x + length, getPosition().y + width));
        
        for(Shape s: corners){
            s.setColor(Color.black);
            s.setFillColor(Color.black);
        }
        
        return corners;
    }
    public void resize(int horizontalChange, int verticalChange) {
        
        this.setLength(this.getLength()+ horizontalChange);
        this.setWidth(this.getWidth()+ verticalChange);
    }
    
    public void increaseLength(){
        this.length ++ ;
    }
    public void increaseWidth(){
        this.width ++ ;
    }
    @Override
    public JSONObject toJson() {
        JSONObject new_rect = new JSONObject();
        new_rect.put("x1",super.getPosition().x);
        new_rect.put("y1",super.getPosition().y);
        new_rect.put("width",width);
        new_rect.put("length",length);
        String hexBorderColor = "#"+Integer.toHexString(getColor().getRGB()).substring(2);
        new_rect.put("borderColor",hexBorderColor);
        String hexFillColor = "#"+Integer.toHexString(getFillColor().getRGB()).substring(2);
        new_rect.put("fillColor",hexFillColor);
        new_rect.put("type", "Rectangle");
        new_rect.put("name", super.getName());

        return new_rect;
    }
    public static Shape jsonToShape(JSONObject shapeJson) {
        Point p1 = new Point((int)(long)shapeJson.get("x1"),(int)(long) shapeJson.get("y1"));
        int width = (int)(long) shapeJson.get("width");
        int length = (int)(long) shapeJson.get("length");
        Color fillColor=Color.decode((String)shapeJson.get("fillColor")) ;
        Color borderColor=Color.decode((String)shapeJson.get("borderColor")) ;
        Rectangle newRectangle = new Rectangle(length, width, p1);
        newRectangle.setColor(borderColor);
        newRectangle.setFillColor(fillColor);
        String name = (String)shapeJson.get("name");
        newRectangle.setName(name);
        return newRectangle;
    }
}
