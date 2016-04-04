package graphs.graphAlgs;

import net.datastructures.*;
import java.util.Comparator;


/**
 * Comparator object defines how we determine which Vertex is "smaller" 
 * 
 * The Vertex with the smaller degree is defined to be smaller
 */
public class VertexNameComparator implements Comparator<Vertex>
{
	public int compare ( Vertex v1, Vertex v2 )
	{
		String name1 = (String) v1.get( GraphAlgorithm.NAME );
		String name2 = (String) v2.get( GraphAlgorithm.NAME );

		return ( name1.compareTo( name2 ) );

	}
}
