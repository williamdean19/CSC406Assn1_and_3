package graphs.graphAlgs;

import net.datastructures.*;
import java.util.Comparator;

/**
 * Comparator object defines how we determine which edge is "cheaper" 
 */
public class EdgeComparator implements Comparator<Edge<Double>>
{
   public int compare ( Edge<Double> e1, Edge<Double> e2 )
   {
       Double weight1 =  e1.element();
       Double weight2 =  e2.element();
       
       if ( weight1 < weight2 )
         return (-1);
       else if ( weight1 > weight2 )
         return (1);
       else 
         return (0);
   }
}
