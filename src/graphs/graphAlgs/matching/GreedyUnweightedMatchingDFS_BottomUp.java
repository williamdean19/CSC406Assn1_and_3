package graphs.graphAlgs.matching;

import net.datastructures.*;

import java.awt.geom.Point2D;

import java.awt.*;

public class GreedyUnweightedMatchingDFS_BottomUp extends MatchingAlgorithm implements DFS_Algorithm
{

	public void runAlgorithm( AdjacencyListGraph<Point2D.Double, Double>  g )
	{
		// initialize all the nodes of the graph as being FREE (not in the matching)   	
		for ( Vertex v : g.vertices() )
		{
			v.put ( VERTEX_STATUS, FREE );
		}
		
		
		// initialize all the edges of the graph as being FREE (not in the matching)
		for ( Edge e : g.edges() )
		{
			e.put ( EDGE_STATUS, FREE );             
		}
		



	}


	public void draw ( Graphics screen, AdjacencyListGraph<Point2D.Double,Double> testGraph, int minDimension )
	{
		drawNodes ( screen, testGraph, minDimension );
		drawEdges ( screen, testGraph, minDimension );
	}   
}
