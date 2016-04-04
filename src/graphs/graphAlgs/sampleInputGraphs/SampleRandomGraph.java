package graphs.graphAlgs.sampleInputGraphs;

import net.datastructures.*;
import graphs.graphAlgs.GraphAlgorithm;

import java.awt.geom.Point2D;
import java.util.Random;

public class SampleRandomGraph
{
   public AdjacencyListGraph<Point2D.Double, Double> g;
   
   private final double EDGE_PROBABILTY = 0.20;  // percentage of the edges present in the graph

   private final long SEED = 314159;             // seed for the random number generator
   
   public SampleRandomGraph ( )
   {
        g = new AdjacencyListGraph<Point2D.Double, Double>( );
 
        Vertex<Point2D.Double> [] vArray = new Vertex [26];
       
        vArray[ 0 ] = g.insertVertex ( new Point2D.Double ( 0.53303481895188366, 0.6834445344170602 ) );
        vArray[ 1 ] = g.insertVertex ( new Point2D.Double ( 0.8463042165098644, 0.11426188458845668 ) );
        vArray[ 2 ] = g.insertVertex ( new Point2D.Double ( 0.3896736931666348, 0.8002502133850073 ) );
        vArray[ 3 ] = g.insertVertex ( new Point2D.Double ( 0.4584810323005461, 0.47289408060807553 ) );
        vArray[ 4 ] = g.insertVertex ( new Point2D.Double ( 0.8162096878739339, 0.4481164827234023 ) );
        vArray[ 5 ] = g.insertVertex ( new Point2D.Double ( 0.8321604801277043, 0.46705587796596554 ) );
        vArray[ 6 ] = g.insertVertex ( new Point2D.Double ( 0.8249669855818863, 0.4936405317222495 ) );
        vArray[ 7 ] = g.insertVertex ( new Point2D.Double ( 0.8708037952131605, 0.44064498150663267 ) );
        vArray[ 8 ] = g.insertVertex ( new Point2D.Double ( 0.7420506640272906, 0.4958298561492375 ) );
        vArray[ 9 ] = g.insertVertex ( new Point2D.Double ( 0.7566461643773531, 0.5479566323905242 ) );
        vArray[ 10 ] = g.insertVertex ( new Point2D.Double ( 0.779581939918515, 0.27689741345042884 ) );
        vArray[ 11 ] = g.insertVertex ( new Point2D.Double ( 0.8421340664038889, 0.28940783874750364 ) );
        vArray[ 12 ] = g.insertVertex ( new Point2D.Double ( 0.8337937912127896, 0.04545454545454538 ) );
        vArray[ 13 ] = g.insertVertex ( new Point2D.Double ( 0.8671549169980384, 0.36655546558293983 ) );
        vArray[ 14 ] = g.insertVertex ( new Point2D.Double ( 0.21035758890161235, 0.4582985802580132 ) );
        vArray[ 15 ] = g.insertVertex ( new Point2D.Double ( 0.2729097153869863, 0.40617181652715173 ) );
        vArray[ 16 ] = g.insertVertex ( new Point2D.Double ( 0.7232850260816784, 0.41242702917568913 ) );
        vArray[ 17 ] = g.insertVertex ( new Point2D.Double ( 0.7045193881360663, 0.6292743968215103 ) );
        vArray[ 18 ] = g.insertVertex ( new Point2D.Double ( 0.13738011217215143, 0.5854879082817486 ) );
        vArray[ 19 ] = g.insertVertex ( new Point2D.Double ( 0.13738011217215143, 0.6876563857113344 ) );
        vArray[ 20 ] = g.insertVertex ( new Point2D.Double ( 0.09984883628092707, 0.4437030924183761 ) );
        vArray[ 21 ] = g.insertVertex ( new Point2D.Double ( 0.0414668473911028, 0.9545454545454546 ) );
        vArray[ 22 ] = g.insertVertex ( new Point2D.Double ( 0.13738011217215143, 0.8794829027630059 ) );
        vArray[ 23 ] = g.insertVertex ( new Point2D.Double ( 0.2603992900899115, 0.6042535462273607 ) );
        vArray[ 24 ] = g.insertVertex ( new Point2D.Double ( 0.706604463189054, 0.13511259758705638 ) );
        

        // add the edges of the graph.  
        Random r = new Random(SEED);
        double randomNumber = r.nextDouble();
        
        for ( int i=0; i < g.numVertices(); i++ ) {
            
              vArray[i].put ( GraphAlgorithm.NAME, "" + i );
        
              int numNeighbors = 0;   // count the number of neighbors added to vertex i
          
              for ( int j=i+1; j < g.numVertices(); j++ )
              {
               	    Point2D p1 =  vArray[i].element();
            	    Point2D p2 =  vArray[j].element();
            	    
            	    double distance = Math.sqrt(   (p1.getX() - p2.getX())*(p1.getX() - p2.getX())  +
            	                                   (p1.getY() - p2.getY())*(p1.getY() - p2.getY())  ); 
            	    
            	    randomNumber = r.nextDouble();                               
            	    if ( randomNumber < EDGE_PROBABILTY )
                      g.insertEdge( vArray[i], vArray[j], distance );    
              }  
        }  
   }
   
   public AdjacencyListGraph<Point2D.Double, Double> getSampleGraph( )
   {
       return ( g );
   } 
}