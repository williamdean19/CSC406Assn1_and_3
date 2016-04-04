package graphs.graphAlgs;

import net.datastructures.*;
import java.awt.geom.Point2D;
import java.util.PriorityQueue;
import java.util.Iterator;
import javax.swing.*;
import java.awt.*;

public class DrawGraph extends GraphAlgorithm
{
	/**
	 * This class does not actually execute any algorithm, but simply
	 * allows us to visualize the original graph
	 */
    public void runAlgorithm( AdjacencyListGraph<Point2D.Double, Double>  g )
    {
    }

    public void draw ( Graphics screen, AdjacencyListGraph<Point2D.Double,Double> testGraph, int minDimension )
    {
        drawNodes ( screen, testGraph, minDimension );
        drawEdges ( screen, testGraph, minDimension );
    }   

    /** 
     * Draw all the edges that are part of the solution onto the screen
     */
    private void drawEdges ( Graphics screen, AdjacencyListGraph<Point2D.Double,Double> testGraph, int minDimension )
    {    
        for ( Edge<Double> line : testGraph.edges() ) 
        {  
            Vertex[] endpts = testGraph.endVertices ( line );
            Vertex v1 = endpts[0];
            Vertex v2 = endpts[1];

            Point2D p1 =  (Point2D) v1.element();
            Point2D p2 =  (Point2D) v2.element();

            // draw the edge of the graph                            
            screen.drawLine( (int) (p1.getX() * minDimension) + (DOTRADIUS / 2),
                (int) (p1.getY() * minDimension) + (DOTRADIUS / 2),
                (int) (p2.getX() * minDimension) + (DOTRADIUS / 2),
                (int) (p2.getY() * minDimension) + (DOTRADIUS / 2) );
        }
    }		
}
