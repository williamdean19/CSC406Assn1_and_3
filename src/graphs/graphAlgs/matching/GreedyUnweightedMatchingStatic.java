package graphs.graphAlgs.matching;

import net.datastructures.*;
import graphs.graphAlgs.GraphAlgorithm;
import graphs.graphAlgs.VertexDegreeComparator;

import java.awt.geom.Point2D;
import java.util.PriorityQueue;
import java.util.Iterator;

import javax.swing.*;

import java.awt.*;

public class GreedyUnweightedMatchingStatic extends MatchingAlgorithm
{

	public void runAlgorithm( AdjacencyListGraph<Point2D.Double, Double>  g )
	{
		//pQueue contains all vertices of the graph, priority given to lowest degree vertex
		PriorityQueue<Vertex> pQueue = new PriorityQueue <Vertex> (g.numVertices(), new VertexDegreeComparator());

		//Mark all vertices as FREE, set the degree of each vertex, and add them all to pQueue:
		for ( Vertex v : g.vertices() )
		{
			v.put (  VERTEX_STATUS, FREE  );   
			v.put (  DEGREE, + (Integer) g.degree(v));
			pQueue.add(v);
		}

		for ( Edge e : g.edges() )
		{
			e.put ( EDGE_STATUS, FREE ); 
		}

		//While there are still vertices in the PriorityQueue we keep trying to find a pair for that vertex to create an edge
		while (   pQueue.size() > 0 )
		{
			// Poll out the lowest degree vertex:
			Vertex currentVertex = pQueue.poll();

			// Find the best (lowest degree) adjacent vertex
			// Set the value of the best adjacent vertex degree to be the maximum degree size
			// Compare against this value to start with and update value as you go along until you find the best one:
			Vertex bestAdjacentVertex = null;
			int bestAdjVertDegree = g.numVertices();

			// Find the best matching vertex opposite among the current available vertices

			// Only do stuff with this vertex if it is free:
			if (currentVertex.get( VERTEX_STATUS ) == FREE)
			{
				for(Edge e : g.incidentEdges(currentVertex))
				{
					Vertex currentAdjacentVertex = g.opposite(currentVertex, e);

					if (currentAdjacentVertex.get( VERTEX_STATUS ) == FREE)
					{

						int currentAdjVertDegree = ((Integer) currentAdjacentVertex.get(GraphAlgorithm.DEGREE)).intValue();

						if(currentAdjVertDegree < bestAdjVertDegree )
						{
							bestAdjacentVertex = currentAdjacentVertex;
							bestAdjVertDegree = ((Integer) bestAdjacentVertex.get(GraphAlgorithm.DEGREE)).intValue();
						}

					}

				}
				// match the edge between currentVertex and bestAdjacentVertex
				for(Edge e : g.incidentEdges(currentVertex))
				{
					if (g.opposite(currentVertex, e) == bestAdjacentVertex)
					{
						e.put(EDGE_STATUS, MATCHED);
						currentVertex.put(VERTEX_STATUS, MATCHED);
						bestAdjacentVertex.put(VERTEX_STATUS, MATCHED);

					}

				}

			}
		}
	}
	public void draw ( Graphics screen, AdjacencyListGraph<Point2D.Double,Double> testGraph, int minDimension )
	{
		drawNodes ( screen, testGraph, minDimension );
		drawEdges ( screen, testGraph, minDimension );
	}   

	public void drawNodes ( Graphics screen, AdjacencyListGraph<Point2D.Double,Double> graph, int minDimension )
	{     
		super.drawNodes(  screen, graph, minDimension );

		// label each vertex in the graph with its degree on the output screen
		for ( Vertex u : graph.vertices() )
		{
			Point2D p = (Point2D) u.element(); 

			double xPos = p.getX()  *  minDimension;
			double yPos = p.getY()  *  minDimension;

			//                         screen.drawString( (u.get(DEGREE)) + "", 
			//                                 (int)(xPos + 1.5*DOTRADIUS ), 
			//                                 (int)(yPos + 1.5*DOTRADIUS ) );  
		}
	}	
}
