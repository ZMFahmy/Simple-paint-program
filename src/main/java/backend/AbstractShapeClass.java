
package backend;

/**
 *
 * @author kirol
 */
import java.awt.Color;
import java.awt.Point;
import org.json.simple.JSONObject;


public abstract class AbstractShapeClass implements Shape , Moveable, Resizeable{
    
    private Point position,draggingPoint;
    private Color borderColor;
    private Color fillColor;
    private String name;
   
    public AbstractShapeClass(Point position) {
        
        this.setPosition(position);
    }
   public abstract JSONObject toJson();
    
    @Override
    public java.awt.Color getColor() {
        return this.borderColor;
    }
    
    @Override
     public void setColor(java.awt.Color color) {
        this.borderColor = color;
    }
     
    @Override
      public java.awt.Color getFillColor() {
        return fillColor;

    }
    @Override
      public void setFillColor(java.awt.Color color) {
        this.fillColor = color;
    }
    @Override
       public java.awt.Point getPosition() {
        return this.position;
    }
 
    @Override
    public void setPosition(java.awt.Point position) {
        this.position = position;
    }

    @Override
     public Point getDraggingPoint(){
         return this.draggingPoint;
     } 
  
    @Override
     public void setDraggingPoint(Point point){
         this.draggingPoint=point;
     }
       @Override
    public void setName(String name){
        this.name = name;
    }
     @Override
    public String getName(){
        return name;
    }
   
   
    @Override
      public abstract boolean contains(Point point); //overridden in all subclasses

      
    @Override
    public abstract void draw(java.awt.Graphics canvas); 
}
