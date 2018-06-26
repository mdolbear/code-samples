package com.oracle.simplegraph;

/**
 *
 *
 */
public class Graph {

    
    private int[][] adjacencyList;
    private boolean[] shortestPaths;
    private int[] distance;
    
    
    /**
     * Answer an instance for the following arguments
     * @param aNumberOfVertices int
     * @param anAdjacencyList int[][]
     */
    public Graph(int aNumberOfVertices,
                 int[][] anAdjacencyList) {
        
        super();
        this.setAdjacencyList(anAdjacencyList);
        this.setShortestPaths(new boolean[aNumberOfVertices]);
        this.setDistance(new int[aNumberOfVertices]);
        
    }


    /**
     * Answer my adjacencyList. Each entry represents the possibility of an edge between one vertex and another. 
     * The value at the given index is the weight.
     * [i,j] - connection from i to j if non-zero. The value is the weight.
     * @return int[][]
     */
    protected int[][] getAdjacencyList() {
        return adjacencyList;
    }


    /**
     * Set my adjacencyList
     * @param adjacencyList int[][]
     */
    protected void setAdjacencyList(int[][] adjacencyList) {
        this.adjacencyList = adjacencyList;
    }


    /**
     * Answer my shortestPaths
     * @return boolean[]
     */
    protected boolean[] getShortestPaths() {
        return shortestPaths;
    }


    /**
     * Set my shortestPaths
     * @param shortestPaths boolean[]
     */
    protected void setShortestPaths(boolean[] shortestPaths) {
        this.shortestPaths = shortestPaths;
    }


    /**
     * Answer my distance
     * @return int[]
     */
    protected int[] getDistance() {
        return distance;
    }


    /**
     * Set my distance
     * @param distance int[]
     */
    protected void setDistance(int[] distance) {
        this.distance = distance;
    }

    
    
    /**
     * Initialize lists for dijkstra
     * @param aNumberOfVertices int
     */
    protected void initializeListsForDijkstra(int aNumberOfVertices) {
        
        for (int i = 0; i < aNumberOfVertices; i++ ) {
            
            this.getDistance()[i] = Integer.MAX_VALUE;
            this.getShortestPaths()[i] = false;
        }
        
    }
    
    /**
     * Perform relax for
     * @param aU int
     * @param aV int
     * 
     * 
     */
    protected void performRelax(int aU, int aV) {
        
        boolean tempRelaxTest;
        
        tempRelaxTest = this.getDistance()[aV] > (this.getDistance()[aU] + this.getAdjacencyList()[aU][aV]);
        if (tempRelaxTest) {
            
            this.getDistance()[aV] = this.getDistance()[aU] + this.getAdjacencyList()[aU][aV];
            
        }
        
    }
    
    /**
     * Answer the vertex with the minimum distance
     * 
     */
    protected int getVertexWithMinimumDistance() {
        
        int tempMin = Integer.MAX_VALUE;
        int tempMinIdx = -1;
        
        for (int v = 0; v < this.getDistance().length; v++) {
            
            if (!this.getShortestPaths()[v] &&
                    this.getDistance()[v] <= tempMin) {
                
                tempMin = this.getDistance()[v];
                tempMinIdx = v;
            }
        }
        
        return tempMinIdx;
        
    }
    
    /**
     * Perform dijkstra shortest path
     * @param aSource int
     */
    public void performDijkstraShortestPath(int aSource) {
        
        int tempU;
        
        this.initializeListsForDijkstra(this.getDistance().length);
        this.getDistance()[aSource] = 0;
        
        for (int count= 0; count < this.getDistance().length - 1; count++) {
            
            
            tempU = this.getVertexWithMinimumDistance();
            if (tempU != -1) {
                
                this.getShortestPaths()[tempU] = true;
                
                for (int v = 0; v <  this.getDistance().length; v++) {
                    
                    if (!this.getShortestPaths()[v] && 
                            (this.getAdjacencyList()[tempU][v] != 0) &&
                                this.getDistance()[tempU] != Integer.MAX_VALUE) {
                        
                        this.performRelax(tempU, v);
                        
                    }
                    
                }
                
            }
            
        }
        
    }
    
    /**
     * Dump solution
     *@param aBuilder StringBuilder
     */
    public void dumpSolution(StringBuilder aBuilder) {
        
        aBuilder.append("Vertex distance from source: ");
        aBuilder.append(System.getProperty("line.separator"));
        for (int i = 0; i < this.getDistance().length; i++) {
            
            aBuilder.append("vertex: " + i + " distance: " + this.getDistance()[i]);
            aBuilder.append(System.getProperty("line.separator"));
        }
        
        
    }
    
    /**
     * Dump solution
     * @param aResult int[][]
     *@param aBuilder StringBuilder
     */
    public void dumpSolution(int[][] aResult,
                             StringBuilder aBuilder) {
        
        aBuilder.append("aResult matrix: ");
        aBuilder.append(System.getProperty("line.separator"));
        for (int i = 0; i < aResult.length; i++) {
            
            for (int j = 0; j < aResult.length; j++) {
                
                aBuilder.append(aResult[i][j] + " ");
            }
            
            aBuilder.append(System.getProperty("line.separator"));
        }
        
        
    }
    
    /**
     * Answer all shortest paths in an nxn matrix where n is the number of vertices for me
     * @return int[][]
     */
    public int[][] computeAllShortestPathsFloydWarshall() {
        
        int[][] tempDistanceResult;
        
        //Make copy of original graph "weights"
        tempDistanceResult = this.copyOf(this.getAdjacencyList());
        
        //Look at the intermediate paths for every i-j vertex
        for (int k = 0; k < this.getAdjacencyList().length; k++) {
            
            //For each source vertex i, do the following
            for (int i = 0; i < this.getAdjacencyList().length; i++) {
                
                //For each destination vertex j, do the following evaluation
                for (int j = 0; j < this.getAdjacencyList().length; j++) {
                    
                    //if the path exists from i-k and k-j, and its shorter than the current value in i-j, then add the two values and
                    //use that as the distance. You have found a shorter path
                    if ((tempDistanceResult[i][k] != Integer.MAX_VALUE) &&
                            (tempDistanceResult[k][j] != Integer.MAX_VALUE) &&
                                ((tempDistanceResult[i][k] + tempDistanceResult[k][j]) < tempDistanceResult[i][j])) {
                        
                        tempDistanceResult[i][j] = tempDistanceResult[i][k] + tempDistanceResult[k][j];
                        System.out.println("k: " + k +  " i: " + i + " j: "+ j + " newValue: " +  tempDistanceResult[i][j]);
                        
                    }
                    
                }
            }
        }
        
        return tempDistanceResult;
        
    }
    
    /**
     * Copy anArray. Answer int[][]
     * @param anArray int[][]
     * @return int[][]
     */
    protected int[][] copyOf(int[][] anArray) {
        
        int[][] tempResult = new int[anArray.length][anArray.length]; //Assumes square matrix
        
        for (int i = 0; i < anArray.length; i++) {
            
            for (int j = 0; j < anArray.length; j++) {
                
                tempResult[i][j] = anArray[i][j];
            }
        }
        
        return tempResult;
        
    }


    
}
