package graphs.graphAlgs.sampleInputGraphs;

import net.datastructures.*;
import graphs.graphAlgs.GraphAlgorithm;

import java.awt.geom.Point2D;

public class SampleSmallGraph
{
    public AdjacencyListGraph<Point2D.Double, Double> g;

    public SampleSmallGraph ( )
    {
        g = new AdjacencyListGraph<Point2D.Double, Double>( );

        Vertex<Point2D.Double> [] vArray = new Vertex [26];

        vArray[ 0 ] = g.insertVertex ( new Point2D.Double ( 0.2603992900899115, 0.6042535462273607 ) ); 
        vArray[ 1 ] = g.insertVertex ( new Point2D.Double ( 0.13738011217215143, 0.6876563857113344 ) );
        vArray[ 2 ] = g.insertVertex ( new Point2D.Double ( 0.5813959608443059, 0.36228106610296423 ) ); 
        vArray[ 3 ] = g.insertVertex ( new Point2D.Double ( 0.42303481895188366, 0.6334445344170602 ) );
        vArray[ 4 ] = g.insertVertex ( new Point2D.Double ( 0.13738011217215143, 0.8794829027630059 ) );          
        vArray[ 5 ] = g.insertVertex ( new Point2D.Double ( 0.7420506640272906, 0.4958298561492375 ) );
        vArray[ 6 ] = g.insertVertex ( new Point2D.Double ( 0.4584810323005461, 0.47289408060807553 ) );
        vArray[ 7 ] = g.insertVertex ( new Point2D.Double ( 0.7045193881360663, 0.6292743968215103 ) );
        
        for ( int i=0; i < g.numVertices(); i++ ) {
            vArray[i].put ( GraphAlgorithm.NAME, "" + i ); 
        }
        
        // add edges to the graph        

        addEdge ( vArray[0], vArray[1] );         
        addEdge ( vArray[1], vArray[4] );   
        addEdge ( vArray[4], vArray[3] );
        addEdge ( vArray[3], vArray[0] ); 
        addEdge ( vArray[3], vArray[6] );            
        addEdge ( vArray[6], vArray[2] );            
        addEdge ( vArray[2], vArray[5] );
        addEdge ( vArray[5], vArray[7] );            
        addEdge ( vArray[7], vArray[3] );  
    }

    private void addEdge ( Vertex vi, Vertex vj ) 
    {
        Point2D p1 =  (Point2D) vi.element();
        Point2D p2 =  (Point2D) vj.element();

        double distance = Math.sqrt(   (p1.getX() - p2.getX())*(p1.getX() - p2.getX())  +
                (p1.getY() - p2.getY())*(p1.getY() - p2.getY())  );
        
        g.insertEdge( vi, vj, distance );      
    }

    public AdjacencyListGraph<Point2D.Double, Double> getSampleGraph( )
    {
        return ( g );
    } 
}