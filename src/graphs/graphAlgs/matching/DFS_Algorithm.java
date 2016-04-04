package graphs.graphAlgs.matching;

/**
 * This interface holds the "decorations" used by a
 * DFS traversal algorithm
 */
public interface DFS_Algorithm {
	
	/** This is the name of the Decoration that indicates
	 *  whether or not a Vertex has been encountered in the traversal
	 */
    public static final Object DFS_STATUS = new Object();
    
	/** These are the two possible values of the DFS_STATUS decoration */
    public static final Object VISITED = new Object();
    public static final Object UNVISITED = new Object();
    
    /** Each vertex can be decorated with the identity of its PARENT in the
     *  DFS forest.  This is the "name" of the decoration.  The value of
     *  the decoration will be a Vertex object 
     */
    public static final Object PARENT = new Object();
    
    /** Each vertex can be decorated with time-stamps to indicate when it was
     *  encountered in the DFS traversal. 
     *  These are the "names" of the decorations.  
     *  The values of these decorations will be Integer object 
     */
    public static final Object START_TIME = new Object();
    public static final Object FINISH_TIME = new Object(); 
}
