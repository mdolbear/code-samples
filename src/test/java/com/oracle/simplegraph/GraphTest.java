package com.oracle.simplegraph;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 *
 *
 */
public class GraphTest {

    /**
     * Answer an instance for the following arguments
     */
    public GraphTest() {
        super();
    }
    
    /**
     * Test dijkstra algorithm
     */
    @Test
    public void dijkstraShortestPathTest() {
        
        int tempAdjacencyMatrix[][] 
                      = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                                    {4, 0, 8, 0, 0, 0, 0, 11, 0},
                                    {0, 8, 0, 7, 0, 4, 0, 0, 2},
                                    {0, 0, 7, 0, 9, 14, 0, 0, 0},
                                    {0, 0, 0, 9, 0, 10, 0, 0, 0},
                                    {0, 0, 4, 14, 10, 0, 2, 0, 0},
                                    {0, 0, 0, 0, 0, 2, 0, 1, 6},
                                    {8, 11, 0, 0, 0, 0, 1, 0, 7},
                                    {0, 0, 2, 0, 0, 0, 6, 7, 0}
                                   };
        Graph           tempGraph;
        StringBuilder   tempBuilder = new StringBuilder();
        
        tempGraph = new Graph(9,
                             tempAdjacencyMatrix);
        tempGraph.performDijkstraShortestPath(0); ///From source vertex 0
        tempGraph.dumpSolution(tempBuilder);
        System.out.println(tempBuilder.toString());
        
    }
    
    /**
     * Test Floyd Warshall algorithm for all shortest paths
     */
    @Test
    public void flordWarshallTest() {
        
        int tempAdjacencyMatrix[][] 
                = new int[][]{{0, 5, Integer.MAX_VALUE, 10},
                              {Integer.MAX_VALUE, 0, 3, Integer.MAX_VALUE},
                              {Integer.MAX_VALUE, Integer.MAX_VALUE, 0 , 1},
                              {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
                             };
                             
      Graph           tempGraph;
      StringBuilder   tempBuilder = new StringBuilder();
      int[][]         tempDistanceMatrix;
      
      tempGraph = new Graph(4,
                           tempAdjacencyMatrix);
      tempDistanceMatrix = tempGraph.computeAllShortestPathsFloydWarshall();
      
      tempGraph.dumpSolution(tempDistanceMatrix, tempBuilder);
      System.out.println(tempBuilder.toString());
  
    }

}
