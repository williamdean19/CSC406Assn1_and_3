package graphs.graphAlgs;

import net.datastructures.*;
import java.util.Comparator;


/**
 * Comparator object defines how we determine which Vertex is "smaller" 
 * 
 * The Vertex with the smaller degree is defined to be smaller
 */
public class VertexDegreeComparator implements Comparator<Vertex>
{
    public int compare ( Vertex v1, Vertex v2 )
    {
        Integer degree1 = (Integer) v1.get( GraphAlgorithm.DEGREE );
        Integer degree2 = (Integer) v2.get( GraphAlgorithm.DEGREE );

        if ( degree1 < degree2 )
        	
            return (-1);
        else if ( degree2 < degree1 )
            return (1);
        else {  // tie-breaker is the NAME of the vertex, which is a String "1", "2"
            String name1 = (String) v1.get( GraphAlgorithm.NAME );
            String name2 = (String) v2.get( GraphAlgorithm.NAME );
            
            return ( name1.compareTo( name2 ) );
        }
    }
}
