package graphs.graphAlgs;


import graphs.graphAlgs.matching.GreedyUnweightedMatchingDFS_BottomUp;
import graphs.graphAlgs.matching.GreedyUnweightedMatchingDFS_TopDown;
import graphs.graphAlgs.matching.GreedyUnweightedMatchingDynamic;
import graphs.graphAlgs.matching.GreedyUnweightedMatchingStatic;
import graphs.graphAlgs.matching.MatchingAlgorithm;
import graphs.graphAlgs.minSpanningTree.GreedyMST_Prim;
import graphs.graphAlgs.sampleInputGraphs.SampleCompleteGraph;
import graphs.graphAlgs.sampleInputGraphs.SampleDenseGraph;
import graphs.graphAlgs.sampleInputGraphs.SampleHugeGraph;
import graphs.graphAlgs.sampleInputGraphs.SampleRandomGraph;
import graphs.graphAlgs.sampleInputGraphs.SampleSmallGraph;
import graphs.graphAlgs.sampleInputGraphs.SampleTree;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.Point2D;

import net.datastructures.*;

public class Assn3Driver
{	
    public static final int NUMBER_TEST_CASES = 1000;
    
    /**
     * Query the User about which Graph Algorithm he/she wishes to execute, and
     * which sample input graph should be used by the algorithm, and then
     * execute that algorithm and display the results.
     */
    public static void main( String args[] )
    {
    	System.out.println ( "hello world" );
    	
        // identifies which algorithm will be executed
        GraphAlgorithm algorithm = selectAlgorithm();

        // the algorithm will be executed on the selected test graph
        AdjacencyListGraph<Point2D.Double,Double> testGraph = initializeSampleGraph();

        JFrame  frame = new JFrame ( );
        frame.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );

        frame.getContentPane().add( new GraphDriver( algorithm, testGraph ) );

        frame.pack();
        frame.setVisible(true);

        /** test the algorithm on many test graphs */
//        if ( algorithm instanceof MatchingAlgorithm )
//           MatchingAlgorithm.batchTesting( algorithm, NUMBER_TEST_CASES );
        
    	System.out.println ( "goodbye world" );
    }

    /**
     * Query the User about which Graph Algorithm he/she wishes to execute
     */
    private static GraphAlgorithm selectAlgorithm()
    {
        GraphAlgorithm algorithm = null;

        String[] algorithmOptions = {
                "Draw the Graph", 
                "Minimum Span Tree (Prim's algorithm)",
                "Greedy Unweighted Matching, static",
                "Greedy Unweighted Matching, dynamic",
                "Greedy Unweighted Matching, DFS, bottom-up",
                "Greedy Unweighted Matching, DFS, top-down"
            };    

        String choice = (String)JOptionPane.showInputDialog(
                null,   
                "Select which algorithm you wish to execute",
                "Graph Algorithm",
                JOptionPane.PLAIN_MESSAGE,
                null,  
                algorithmOptions,
                algorithmOptions[0]);

        if ( choice == null ) 
            System.exit(1);    // abort the program  

        if ( choice.equals ( algorithmOptions[0] ) )
            algorithm = new DrawGraph(); 

        else if ( choice.equals ( algorithmOptions[1] ) )
        	algorithm = new GreedyMST_Prim();

        else if ( choice.equals ( algorithmOptions[2] ) )
            algorithm = new GreedyUnweightedMatchingStatic(); 

        else if ( choice.equals ( algorithmOptions[3] ) )
            algorithm = new GreedyUnweightedMatchingDynamic(); 

        else if ( choice.equals ( algorithmOptions[4] ) )
            algorithm = new GreedyUnweightedMatchingDFS_BottomUp();   

        else 
            algorithm = new GreedyUnweightedMatchingDFS_TopDown();    

        return algorithm;
    }

    /**
     * Query the User about which sample graph he/she wished to use
     */
    private static AdjacencyListGraph<Point2D.Double,Double> initializeSampleGraph()
    {  
        String[] sampleGraphOptions = {
                "Complete graph, n = 26",
                "Dense graph, n = 26",
                "Small graph, n = 8", 
                "Tree, n = 26",
                "Huge graph, n = 225",
                "Random graph, n = 25"
            };  

        String choice = (String)JOptionPane.showInputDialog(
                null,   
                "Select which input graph you wish to use",
                "Input Graph",
                JOptionPane.PLAIN_MESSAGE,
                null,  
                sampleGraphOptions,
                sampleGraphOptions[0]);

        if ( choice == null ) 
            System.exit(1);    // abort the program  

        if ( choice.equals ( sampleGraphOptions[0] ) )
            return (new SampleCompleteGraph()).getSampleGraph();

        else if ( choice.equals ( sampleGraphOptions[1] ) )
            return (new SampleDenseGraph()).getSampleGraph(); 

        else if ( choice.equals ( sampleGraphOptions[2] ) )
            return (new SampleSmallGraph()).getSampleGraph(); 

        else if ( choice.equals ( sampleGraphOptions[3] ) )
            return (new SampleTree()).getSampleGraph();  

        else if ( choice.equals ( sampleGraphOptions[4] ) )
            return (new SampleHugeGraph()).getSampleGraph();     

        else 
            return (new SampleRandomGraph()).getSampleGraph();   
    }    
}
