
package backend;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;


public class MyPanel extends JPanel implements DrawingEngine{

    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private ArrayList<Shape> corners = new ArrayList<Shape>();
    private int numOfSquares = 0;
    private int numOfRectangles = 0;
    private int numOfLineSegments = 0;
    private int numOfCircles = 0;
    private int numOfTriangles = 0;

    public int getNumberOfTriangles() {
        return numOfTriangles;
    }
    
    public int getNumberOfSquares(){
        return numOfSquares;
    }
    
    public int getNumberOfRectangles(){
        return numOfRectangles;
    }
    
    public int getNumberOfCircles(){
        return numOfCircles;
    }
    
    public int getNumberOfLineSegments(){
        return numOfLineSegments;
    }
     public void increaseTriangles(){
        numOfTriangles ++ ;
    }
    
    public void increaseSquares(){
        numOfSquares ++ ;
    }
    
    public void increaseRectangles(){
        numOfRectangles ++ ;
    }
    
    public void increaseCircles(){
        numOfCircles ++;
    }
    
    public void increaseLineSegments(){
        numOfLineSegments ++;
    }
    
    @Override
    public void paintComponent(Graphics g){
      super.paintComponent(g);
        for(Shape s: shapes){
            s.draw(g);
        }
        if(!corners.isEmpty()){
            for(Shape s: corners){
            s.draw(g);
            }
        }
    }
    
    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    @Override
    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    @Override
    public Shape[] getShapes() {
        return shapes.toArray(new Shape[shapes.size()]);
    }
       public Shape[] getCorners() {
        return corners.toArray(new Shape[corners.size()]);
    }
    
    public void addCorner(Shape s){
        corners.add(s);
    }
    
    public void removeCorners(){
        corners.clear();
    }
    @Override
    public void refresh(Graphics canvas) {
        repaint();
    }
    
}
