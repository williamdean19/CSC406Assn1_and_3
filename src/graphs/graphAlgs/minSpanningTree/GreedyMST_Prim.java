package graphs.graphAlgs.minSpanningTree;

import net.datastructures.*;
import graphs.graphAlgs.EdgeComparator;
import graphs.graphAlgs.GraphAlgorithm;

import java.awt.geom.Point2D;

import javax.swing.*;

import java.awt.*;
import java.util.PriorityQueue;
import java.util.Iterator;

/**
 * This class contains a method to identify the edges of a Minimum Spanning Tree.
 * 
 * The method will "decorate" each edge chosen to be included as part of the Minimum Spanning Tree
 */
public class GreedyMST_Prim extends GraphAlgorithm
{
    // decoration that indicates whether or not a Vertex has been added to the MST
    public static final Object VERTEX_STATUS = new Object();

    // decoration that indicates whether or not an Edge has been added to the MST
    public static final Object EDGE_STATUS = new Object();

    // possible values for the STATUS decorations
    public static final Object IN_TREE = new Object();
    public static final Object NOT_IN_TREE = new Object();

    // decoration that indicates in what order the edges were added to the solution MST
    // the associated value will be a String (e.g., "1", "2", "3", etc.)
    public static final Object EDGE_ORDER = new Object(); 

    public void runAlgorithm ( AdjacencyListGraph<Point2D.Double, Double>  g )
    {
        // This heap will contain all the edges in the graph
        // We will examine the edges from the least-costly to the most-costly
        PriorityQueue<Edge<Double>> heap = new PriorityQueue<Edge<Double>>(11, new EdgeComparator());

        // decorate all vertices as NOT IN TREE  
        for ( Vertex v : g.vertices() ) {
            v.put ( VERTEX_STATUS, NOT_IN_TREE );          
        }

        // initialize all the edges of the graph as beining NOT IN THE Min Spanning Tree
        for ( Edge e : g.edges() )
            e.put ( EDGE_STATUS, NOT_IN_TREE ); 

        if ( g.numVertices() == 0 )  // if the graph is empty, return immediately
        	return;

        // select an arbitrary vertex to be the start vertex
        Vertex start = null;
        for ( Vertex v : g.vertices() ) {
            start = v;
            break;     // once we have found a vertex we quit the iterator loop
        } 
        start.put ( VERTEX_STATUS, IN_TREE );   

        // fill the heap with all the edges in the graph that are incident to the start node
        for ( Edge e : g.incidentEdges( start ) )            
            heap.add ( e );      

        // this is the current number of nodes that have been included in the Min Spanning Tree so far
        int numMSTVertices = 1;

        // continue to add edges to the MST until all nodes are part of the MST
        while ( numMSTVertices < g.numVertices() &&  heap.size() > 0 ) {

            // remove the cheapest edge from the heap
            Edge e = heap.poll();

            // identify the two endpoints of the selected edge
            Vertex[] endpts = g.endVertices ( e );
            Vertex v1 = endpts[0];
            Vertex v2 = endpts[1];

            if ( v1.get( VERTEX_STATUS ) == IN_TREE  &&
                 v2.get( VERTEX_STATUS ) == IN_TREE  )
                ;   // do nothing, selecting this edge would create a cycle

            else if ( v1.get( VERTEX_STATUS ) == NOT_IN_TREE  &&
                      v2.get( VERTEX_STATUS ) == NOT_IN_TREE  )
                throw ( new RuntimeException ( "Illegal state in Prim's algorithm" ));   

            else  // grow the Min Spanning Tree to include the edge (v1,v2)
            {
                // make sure that v1 is the vertex currently in the tree
                if ( v1.get( VERTEX_STATUS ) == NOT_IN_TREE  &&
                     v2.get( VERTEX_STATUS ) == IN_TREE  ) {
                    Vertex temp = v1;   v1 = v2;  v2 = temp;   // swap the names of the vertices
                }

                // decorate this edge as part of the Min Spanning Tree
                e.put ( EDGE_STATUS, IN_TREE );

                // label each edge in the Min Spanning Tree in the order in which they were selected
                e.put ( EDGE_ORDER, numMSTVertices + "" );

                numMSTVertices++;

                // grow the tree from v1 out to v2
                v2.put( VERTEX_STATUS, IN_TREE ); 

                // add to the heap with all the edges in the graph incident to v2
                // that connect to vertices that are not already in the Min Spanning Tree
                for ( Edge<Double> v2edge : g.incidentEdges(v2) ) {

                    Vertex u2 = g.opposite(v2, v2edge);

                    if ( u2.get( VERTEX_STATUS ) == NOT_IN_TREE  )
                        heap.add ( v2edge );  
                }    
            }
        }  
    }

    /** 
     * Draw the graph onto the output screen 
     */ 
    public void draw ( Graphics screen, AdjacencyListGraph<Point2D.Double,Double> graph, int minDimension )
    {
        drawNodes ( screen, graph, minDimension );
        drawEdges ( screen, graph, minDimension );
    }   

    /** 
     * Draw all the edges that are part of the solution onto the screen
     */
    private void drawEdges ( Graphics screen, AdjacencyListGraph<Point2D.Double,Double> graph, int minDimension )
    {    
        double totalCost = 0.0;

        for ( Edge<Double> line : graph.edges() ) 
        {  
            // Check if the edge is part of the solution
            if ( line.get ( EDGE_STATUS ) == IN_TREE ) 
            {
                totalCost += line.element();

                String edgeOrderLabel = (String) line.get( EDGE_ORDER );

                Vertex[] endpts = graph.endVertices ( line );
                Vertex v1 = endpts[0];
                Vertex v2 = endpts[1];

                Point2D p1 =  (Point2D) v1.element();
                Point2D p2 =  (Point2D) v2.element();

                // For each edge, indicate when it was selected to be part of the solution MST
                Point2D midPoint = new Point2D.Double( (p1.getX() + p2.getX())/2.0, 
                        (p1.getY() + p2.getY())/2.0 );
                screen.drawString( edgeOrderLabel, (int) (midPoint.getX() * minDimension) + DOTRADIUS, 
                    (int) (midPoint.getY() * minDimension) + DOTRADIUS );

                // draw the edge of the graph                            
                screen.drawLine( (int) (p1.getX() * minDimension) + (DOTRADIUS / 2),
                    (int) (p1.getY() * minDimension) + (DOTRADIUS / 2),
                    (int) (p2.getX() * minDimension) + (DOTRADIUS / 2),
                    (int) (p2.getY() * minDimension) + (DOTRADIUS / 2) );
            }
        }
        screen.drawString ( "The total cost is " + totalCost, 20, 40 );
    }		
}
