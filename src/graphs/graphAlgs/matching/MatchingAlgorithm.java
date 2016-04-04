package graphs.graphAlgs.matching;

import graphs.graphAlgs.GraphAlgorithm;
import graphs.graphAlgs.sampleInputGraphs.SampleRandomGraph;

import java.awt.*;
import java.awt.geom.Point2D;

import net.datastructures.*;

/**
 * Abstract class MatchingAlgorithm 
 * 
 * contains the definition of the "decorations" used in the Graph Matching Algorithms
 * 
 * @author Joan Lucas
 */
public abstract class MatchingAlgorithm extends GraphAlgorithm
{
    // decoration name
    public static final Object VERTEX_STATUS = new Object();
    
    // possible values for the VERTEX_STATUS decorations
    public static final Object FREE = new Object();
    public static final Object MATCHED = new Object(); 

    // decoration name.  legal values for this decoration are: FREE or MATCHED
    public static final Object EDGE_STATUS = new Object();

    public static final Object EDGE_ORDER = new Object();
    
    
    /** 
     * Draw all the edges that are part of the solution onto the screen
     */
    protected void drawEdges ( Graphics screen, AdjacencyListGraph<Point2D.Double,Double> testGraph, int minDimension )
    {    
        int totalNumMatchedEdges = 0;
        
        for ( Edge<Double> line : testGraph.edges() ) 
        {  
            Vertex[] endpts = testGraph.endVertices ( line );
            Vertex v1 = endpts[0];
            Vertex v2 = endpts[1];

            Point2D p1 =  (Point2D) v1.element();
            Point2D p2 =  (Point2D) v2.element();
            Point2D midPoint = new Point2D.Double( (p1.getX() + p2.getX())/2.0, 
                    (p1.getY() + p2.getY())/2.0 );       

            // Check if the edge is part of the solution
            if ( line.get ( EDGE_STATUS ) == MATCHED )
            {
                totalNumMatchedEdges++;

                screen.setColor ( Color.BLACK );

                String edgeOrderLabel = (String) line.get( EDGE_ORDER );
                if ( edgeOrderLabel != null )
                   screen.drawString( edgeOrderLabel, (int) (midPoint.getX() * minDimension) + DOTRADIUS, 
                                      (int) (midPoint.getY() * minDimension) + DOTRADIUS );

                screen.drawLine( (int) (p1.getX() * minDimension) + (DOTRADIUS / 2),
                    (int) (p1.getY() * minDimension) + (DOTRADIUS / 2),
                    (int) (p2.getX() * minDimension) + (DOTRADIUS / 2),
                    (int) (p2.getY() * minDimension) + (DOTRADIUS / 2) );  
            }
        }

        screen.drawString ( "The total number of matched edge is " + totalNumMatchedEdges, 20, 40 );
    }		
          
    
    /** Verify that the given graph represents a Maximal Matching
     * @param g the graph to be examined
     * @return true if-and-only-if the collection of "matched" edges is valid (no two
     * matched edges shared an endpoint) and is maximal, i.e., it cannot be increased 
     * by adding any more edges. 
     */
    private static boolean isMaximalMatching( AdjacencyListGraph<Point2D.Double,Double>  g ) {
        
        /** for every node in the graph, make sure at most one of its edge is marked as "matched" */
        for ( Vertex v : g.vertices() ) {
            boolean hasOneMatchedIncidentEdge = false;
            for ( Edge e : g.incidentEdges(v) ) {
                if ( e.get(EDGE_STATUS) == MATCHED  && hasOneMatchedIncidentEdge )
                   return false;   // this is the second matched edge incident to v
                                   // so this is not a valid matching
                else if ( e.get(EDGE_STATUS) == MATCHED  )
                   hasOneMatchedIncidentEdge = true;
            }
        }
        
        /** for every edge in the graph, check whether the size of the collection of matched edges
         * can be increased by adding this edge (if so, the matching is not maximal ) */
        for ( Edge e : g.edges() ) {
            Vertex[] endpts = g.endVertices(e);
            if ( e.get(EDGE_STATUS) != MATCHED && endpts[0].get(VERTEX_STATUS) != MATCHED
                                               && endpts[1].get(VERTEX_STATUS) != MATCHED )
                return (false);
        }
        return true;
    }  
    
    
    /** test the matching algorithm on many test graphs 
     * @param algorithm The graph matching algorithm to be tested
     * @param numTests The number of test cases to carry out
     */
    public static void batchTesting( GraphAlgorithm alg, int numTests ) {

        /** test the algorithm on many test graphs */
        int numberOfTests = numTests;
        try {
            for ( int i=0; i < numberOfTests; i++ ) {

                AdjacencyListGraph<Point2D.Double,Double> testGraph;
                testGraph =(new SampleRandomGraph()).getSampleGraph(); 

                alg.runAlgorithm( testGraph );

                /** verify that the edges selected by the algorithm 
                 * form a legal matching, and that the matching is maximal */
                boolean validResult = MatchingAlgorithm.isMaximalMatching ( testGraph );

                if ( ! validResult )
                    throw new RuntimeException();
            }
        }
        catch ( RuntimeException woe ) {
            System.out.println( "The matching algorithm failed on a random test graph" );
            System.exit(1);
        }
        System.out.println( "The matching algorithm succeeded on all " + numberOfTests +
            " test graphs" );
    }
}
