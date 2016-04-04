package graphs.graphAlgs.matching;

import net.datastructures.*;

import java.awt.geom.Point2D;

import java.awt.*;
import java.util.PriorityQueue;
import java.util.Stack;

import graphs.graphAlgs.VertexDegreeComparator;
import graphs.graphAlgs.VertexNameComparator;

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

		depthFirstSearch(g);

		for(Vertex v : g.vertices())
		{
			if (v.get(VERTEX_STATUS) == FREE)
			{
				for ( Edge e : g.incidentEdges(v) )
				{
					Vertex w = g.opposite(v, e);
					if(v.get(VERTEX_STATUS) == FREE && w.get(VERTEX_STATUS) == FREE)
					{
						e.put(EDGE_STATUS, MATCHED);
						v.put(VERTEX_STATUS, MATCHED);
						w.put(VERTEX_STATUS, MATCHED);
					}
				}
			}
		}
	}

	public void depthFirstSearch(AdjacencyListGraph<Point2D.Double, Double> g)
	{
		for ( Vertex u: g.vertices() )
		{
			u.put( DFS_STATUS, UNVISITED);
		}

		for ( Vertex v: g.vertices() )
		{
			if(v.get( DFS_STATUS ) == UNVISITED)
			{
				depthFirstSearch(g, v);
			}
		}
	}

	public void depthFirstSearch(AdjacencyListGraph<Point2D.Double, Double> g, Vertex v)
	{
		v.put(DFS_STATUS, VISITED);

		for ( Edge e : g.incidentEdges(v) )
		{
			Vertex w = g.opposite(v, e);
			if(w.get(DFS_STATUS) == UNVISITED)
			{
				w.put(PARENT, v);

				depthFirstSearch(g, w);

				if(v.get(VERTEX_STATUS) == FREE && w.get(VERTEX_STATUS) == FREE)
				{
					e.put(EDGE_STATUS, MATCHED);
					v.put(VERTEX_STATUS, MATCHED);
					w.put(VERTEX_STATUS, MATCHED);
				}
			}
		}
	}



	public void draw ( Graphics screen, AdjacencyListGraph<Point2D.Double,Double> testGraph, int minDimension )
	{
		drawNodes ( screen, testGraph, minDimension );
		drawEdges ( screen, testGraph, minDimension );
	}   
}
