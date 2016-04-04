package graphs.graphAlgs;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import net.datastructures.*;

public class GraphDriver extends JPanel
{
    private int minDimension;           // smaller of either the width or height of the output panel
    
    private GraphAlgorithm algorithm;   // indicates which Graph Algorithm the user wishes to execute
    private AdjacencyListGraph<Point2D.Double,Double> testGraph;

    public GraphDriver ( GraphAlgorithm alg, AdjacencyListGraph<Point2D.Double,Double> testGr )
    {
        setPreferredSize ( new Dimension( 650, 650 ));    
        setBackground ( Color.white );
        algorithm = alg;
        testGraph = testGr;
    }

    public void paintComponent ( Graphics screen )
    {
        super.paintComponent( screen );
        screen.setColor ( Color.black );	
        int pixelWidth = getWidth();
        int pixelHeight = getHeight();
        minDimension = ( pixelWidth < pixelHeight ) ? pixelWidth : pixelHeight;

        algorithm.runAlgorithm( testGraph );
        algorithm.draw( screen, testGraph, minDimension );       
    }		
}
