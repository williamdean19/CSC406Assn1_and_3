package graphs.intro;

import net.datastructures.*;
import java.awt.geom.Point2D;

public class GraphCode
{
    public static final Object LABEL_LENGTH = new Object();
    public static final Object SHORT = new Object();
    public static final Object LONG = new Object();

    public static void main ( String args[] )
    {
    	System.out.println ( "hello world" );
        int n = 0;
        int m = 0;
        int weight = 0;
        Vertex[] endpoints = null;
      
        AdjacencyListGraph<String,Integer> theGraph;

        theGraph = new AdjacencyListGraph();

        Vertex a = theGraph.insertVertex ( "alice" );
        Vertex b = theGraph.insertVertex ( "bob" );
        Vertex c = theGraph.insertVertex ( "chad" );
        Vertex d = theGraph.insertVertex ( "dan"  );

        Edge firstEdge = theGraph.insertEdge ( a, b, new Integer(10) );
        
        theGraph.insertEdge ( b, c, new Integer(20) );        
        theGraph.insertEdge ( c, a, new Integer(30) );
        theGraph.insertEdge ( c, d, new Integer(40) );
     
        n = theGraph.numVertices();
        m = theGraph.numEdges();  

        boolean neighbors = false;
        neighbors = theGraph.areAdjacent( a, c );
        neighbors = theGraph.areAdjacent( a, d );

        // traverse over all the edges in the graph
        // examine and process each one in turn
        String allEdges = "";
        for ( Edge<Integer>  e : theGraph.edges() )
        {
            weight = e.element();
            endpoints = theGraph.endVertices(e);
            allEdges += "(" + endpoints[0] + "," + endpoints[1] + ")";
        }

        // traverse over all the nodes in the graph
        // examine and process each one in turn
        for ( Vertex<String> v : theGraph.vertices() )
        {
        	String allNeighbors = "";
            for ( Edge<Integer>  e :  theGraph.incidentEdges(v) )
            {
                endpoints = theGraph.endVertices(e);
                
                allNeighbors += "(" + endpoints[0] + "," + endpoints[1] + ")";
            }
        }

        // Adding any information to a node or an edge - Decorating the Graph
        for ( Vertex<String> v : theGraph.vertices() )
        {      
            String name = v.element(); 

            if ( name.length() <= 3 )
                v.put ( LABEL_LENGTH, SHORT );
            else
                v.put ( LABEL_LENGTH, LONG );
        }

        String label = "";
        for ( Vertex<String> v : theGraph.vertices() )
        {      
            if ( v.get( LABEL_LENGTH ) == SHORT ) 
                label = v.element() + " is SHORT";
            else
                label = v.element() + " is LONG";
        }        
    }
}