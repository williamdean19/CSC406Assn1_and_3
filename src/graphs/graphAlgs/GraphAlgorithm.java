package graphs.graphAlgs;

import java.awt.*;
import java.awt.geom.Point2D;
import net.datastructures.*;

public abstract class GraphAlgorithm
{
    public static final int DOTRADIUS = 12;        // size used to draw the nodes of the graph on the output panel

    // decoration used to place a NAME on vertices or edges
    public static final Object NAME = new Object();
    
    // decorate each Vertex with its degree in the graph, which will be an Integer
    public static final Object DEGREE = new Object();  

    public abstract void runAlgorithm( AdjacencyListGraph<Point2D.Double,Double>  testGraph );


    public abstract void draw ( Graphics screen, 
                                AdjacencyListGraph<Point2D.Double,Double> testGraph,
                                int minDimension );     

    public void drawNodes ( Graphics screen, AdjacencyListGraph<Point2D.Double,Double> graph, int minDimension )
    {        
    	screen.drawString( "William Dean", 20, 20 );
    	
        for ( Vertex u : graph.vertices() )
        {
            Point2D p = (Point2D) u.element(); 

            double xPos = p.getX()  *  minDimension;
            double yPos = p.getY()  *  minDimension;
            
            screen.fillOval( (int) xPos, (int) yPos, DOTRADIUS, DOTRADIUS );             
            
            screen.drawString( ((String) u.get(GraphAlgorithm.NAME)) + "", 
                    (int)(xPos + DOTRADIUS ), 
                    (int)(yPos + DOTRADIUS ) );  
            

                    
        }
    }
    public void drawDegrees(Graphics screen, AdjacencyListGraph<Point2D.Double,Double> graph, int minDimension)
    {
    	 for ( Vertex u : graph.vertices() )
         {
    		 
             Point2D p = (Point2D) u.element(); 

             double xPos = p.getX()  *  minDimension;
             double yPos = p.getY()  *  minDimension;
             screen.drawString( ((String) u.get(GraphAlgorithm.DEGREE)) + "", 
                     (int)(xPos - DOTRADIUS ), 
                     (int)(yPos - DOTRADIUS ) );  
         }
    	
    }

}
