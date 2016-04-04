package graphs.graphAlgs.sampleInputGraphs;

import net.datastructures.*;
import graphs.graphAlgs.GraphAlgorithm;

import java.awt.geom.Point2D;
import java.util.*;

public class SampleHugeGraph
{
    public AdjacencyListGraph<Point2D.Double, Double> g;
    
    private final int GRID_SIZE = 15;
    private final double EDGE_PROBABILTY = 0.30;    // percentage of the edges prsent in the graph
        
    public SampleHugeGraph ( )
    {
        g = new AdjacencyListGraph<Point2D.Double, Double>( );

        Vertex<Point2D.Double> [] vArray = new Vertex [ GRID_SIZE * GRID_SIZE ];

        for ( int i=0; i < GRID_SIZE; i++ ) {
            for ( int j=0; j < GRID_SIZE; j++ )
            {
                double xValue, yValue;

                xValue = 0.0 +  ((double) (i+1) / (GRID_SIZE+1));
                yValue = 0.0 +  ((double) (j+1) / (GRID_SIZE+1));               

                vArray[ i*GRID_SIZE + j ] = g.insertVertex ( new Point2D.Double ( xValue, yValue ) );

                vArray[ i*GRID_SIZE + j ].put ( GraphAlgorithm.NAME, "(" + i + "," + j + ")" );
            }  
        }

        // add the edges of the graph.  
        Random r = new Random(314159);
        double probability = r.nextDouble();
        
        for ( int i=0; i < GRID_SIZE; i++ ) {
            for ( int j=0; j < GRID_SIZE; j++ )
            {
                Vertex currentVertex = vArray[i*GRID_SIZE + j];

                if ( inBounds( i, j-1 ) ) 
                    addEdgeWithProbablity ( currentVertex, vArray[(i)*GRID_SIZE + (j-1)], probability );
                probability = r.nextDouble();   
                
                if ( inBounds( i+1, j-1 ) ) 
                    addEdgeWithProbablity ( currentVertex, vArray[(i+1)*GRID_SIZE + (j-1)], probability );
                probability = r.nextDouble(); 
                
                if ( inBounds( i+1, j ) ) 
                    addEdgeWithProbablity ( currentVertex, vArray[(i+1)*GRID_SIZE + (j)], probability );
                probability = r.nextDouble(); 
                
                if ( inBounds( i+1, j+1 ) ) 
                    addEdgeWithProbablity ( currentVertex, vArray[(i+1)*GRID_SIZE + (j+1)], probability );
                probability = r.nextDouble();  
                
                if ( inBounds( i+1, j-2 ) ) 
                    addEdgeWithProbablity ( currentVertex, vArray[(i+1)*GRID_SIZE + (j-2)], probability );
                probability = r.nextDouble(); 
                
                if ( inBounds( i+2, j-1 ) ) 
                    addEdgeWithProbablity ( currentVertex, vArray[(i+2)*GRID_SIZE + (j-1)], probability );
                probability = r.nextDouble();  
                
                if ( inBounds( i+2, j+1 ) ) 
                    addEdgeWithProbablity ( currentVertex, vArray[(i+2)*GRID_SIZE + (j+1)], probability );
                probability = r.nextDouble();  
                
                if ( inBounds( i+1, j+2 ) ) 
                    addEdgeWithProbablity ( currentVertex, vArray[(i+1)*GRID_SIZE + (j+2)], probability );
                probability = r.nextDouble();                   
            }  
        }        
    }

    private void addEdgeWithProbablity ( Vertex vi, Vertex vj, Double probability ) 
    {             
        Point2D p1 =  (Point2D) vi.element();
        Point2D p2 =  (Point2D) vj.element();

        double distance = Math.sqrt(   (p1.getX() - p2.getX())*(p1.getX() - p2.getX())  +
                (p1.getY() - p2.getY())*(p1.getY() - p2.getY())  ); 
                
        if ( probability < EDGE_PROBABILTY )
          g.insertEdge( vi, vj, distance );      
    }

    /** 
     * return true if the coordinate (i,j) are within the GRID_SIZE x GRID_SIZE grid
     */
    private boolean inBounds ( int i, int j ) 
    {
      if ( i < 0  ||  j < 0 )
        return false;
      if ( i >= GRID_SIZE  ||  j >= GRID_SIZE )
        return false;
      return true;
    }
    
    public AdjacencyListGraph<Point2D.Double, Double> getSampleGraph( )
    {
        return ( g );
    } 
}