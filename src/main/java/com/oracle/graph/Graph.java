package com.oracle.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import com.oracle.utils.SetUtils;

/**
 *
 *
 */
public class Graph {

    private Set<Vertex>                             vertices;
    private Set<Edge>                               edges;
    private boolean                                 undirected;
    private Map<Vertex, Vertex>                     predecessors;
    private Map<String, LinkedList<String>>         adjacencyList;
    private int                                     time;
    
    
    
    /**
     * Answer an instance for the following arguments
     */
    public Graph(boolean unidirected) {
        
        super();
        this.setUndirected(unidirected);
        this.setEdges(new HashSet<Edge>());
        this.setVertices(new HashSet<Vertex>());
        this.clearPredecessorsList();
        this.setTime(0);
        
    }


    /**
     * Clear predecessors list
     */
    protected void clearPredecessorsList() {
        
        this.setPredecessors(new HashMap<Vertex,Vertex>());
    }

    
    /**
     * Add aVertex to me
     * @param aVertex Vertex
     */
    public void addVertex(Vertex aVertex) {
        
        this.getVertices().add(aVertex);
    }
    
    /**
     * Add anEdge to me
     * @param anEdge Edge
     */
    public void addEdge(Edge anEdge) {
        
        if (!(this.isUndirected() ^ anEdge.isUndirected())) {
            
            this.getEdges().add(anEdge);
        }
        else {
            
            throw new IllegalStateException("Graph that is unidirectional: " + this.isUndirected() 
                                            + " cannot accept an edge that is unidirectional: " + anEdge.toString());
            
        }
        
    }

    /**
     * Answer my vertices
     * @return Set<Vertex>
     */
    protected Set<Vertex> getVertices() {
        return vertices;
    }


    /**
     * Set my vertices
     * @param vertices Set<Vertex>
     */
    protected void setVertices(Set<Vertex> vertices) {
        this.vertices = vertices;
    }


    /**
     * Answer my edges
     * @return Set<Edge>
     */
    protected Set<Edge> getEdges() {
        return edges;
    }


    /**
     * Set my edges
     * @param edges Set<Edge>
     */
    protected void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }


    /**
     * Answer my undirected
     * @return boolean
     */
    protected boolean isUndirected() {
        return undirected;
    }


    /**
     * Set my undirected
     * @param undirected boolean
     */
    protected void setUndirected(boolean undirected) {
        this.undirected = undirected;
    }
    
    /**
     * Answer my Edges as a sorted list
     * @return List<Edge>
     */
    protected List<Edge> getEdgesAsSortedList() {
        
        List<Edge>  tempResult;
        
        tempResult = new ArrayList<Edge>(this.getEdges());
        Collections.sort(tempResult, this.getEdgeComparator());
        
        return tempResult;
        
    }
    
    /**
     * Answer my Edges as a sorted list
     * @param anEdges List<Edge>
     * @return List<Edge>
     */
    protected List<Edge> getEdgesAsSortedListByTotalDistance(List<Edge> anEdges) {
        
        List<Edge>  tempResult;
        
        tempResult = new ArrayList<Edge>(anEdges);
        Collections.sort(tempResult, this.getEdgeComparatorBasedOnDistance());
        
        return tempResult;
        
    }
    
    
    /**
     * Answer a comparator for sorting Edges
     * @return Comparator<Edge>
     */
    protected Comparator<Edge> getEdgeComparator() {
        
        return (Edge e1, Edge e2) -> (e1.getWeight() - e2.getWeight());
        
    }
    
    /**
     * Answer my distance for sorting Edges
     * @return Comparator<Edge>
     */
    protected Comparator<Edge> getEdgeComparatorBasedOnDistance() {
        
        return (Edge e1, Edge e2) -> (e1.getTotalDistanceOfVertices() - e2.getTotalDistanceOfVertices());
        
    }
    
    

    
    /**
     * Answer a minimum spanning tree using Kruskal's algorithm
     * @return Graph
     */
    public Graph produceMinimumSpanningTreeKruskal() {
        
        Graph                   tempGraph;
        Set<Set<Vertex>>        tempVertexSets;
        List<Edge>              tempEdges;
        Set<Vertex>             tempNewVerteces;
        Set<Edge>               tempNewEdges;

        
        tempGraph = new Graph(this.isUndirected());
        tempVertexSets = SetUtils.getInstance().getSetElementsAsIndependentSets(this.getVertices());
        tempEdges = this.getEdgesAsSortedList();
        this.createAdjacencyList();
        
        tempNewVerteces = new HashSet<Vertex>();
        tempNewEdges = new HashSet<Edge>();
        
        for (Edge anEdge: tempEdges) {
            
            tempVertexSets =
                    anEdge.handleEdgeKruskal(tempVertexSets, 
                                             tempNewVerteces, 
                                             tempNewEdges);
            
        }
        
        
        if (!tempNewEdges.isEmpty() && !tempNewVerteces.isEmpty()) {
            
            tempGraph.setEdges(tempNewEdges);
            tempGraph.setVertices(tempNewVerteces);
        }
        
        return tempGraph;
        
    }



    /**
     * Answer the vertex for anIdentifier or null
     * @param anIdentifier String
     * @return Vertex
     */
    public Vertex getVertexFor(String anIdentifier) {
        
        Iterator<Vertex>    tempItr;
        Vertex              tempCurrent;
        Vertex              tempResult = null;
        
        tempItr = this.getVertices().iterator();
        while (tempItr.hasNext() && tempResult == null) {
            
            tempCurrent = tempItr.next();
            if (tempCurrent.hasIdentifier(anIdentifier)) {
                
                tempResult = tempCurrent;
                
            }
            
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer the edge for anIdentifier1 and anIdentifier2. Note that edges expect identifiers to be in order,
     * for the case where we have a directed graph
     * @param anIdentifier1 String
     * @param anIdentfier2 String
     * @return Edge
     */
    public Edge getEdgeFor(String anIdentifier1, String anIdentifier2) {
        
        Iterator<Edge>  tempItr;
        Edge            tempCurrent;
        Edge            tempResult = null;
        
        tempItr = this.getEdges().iterator();
        while (tempItr.hasNext() && tempResult == null) {
            
            tempCurrent = tempItr.next();
            if (tempCurrent.hasVertexIdentifiers(anIdentifier1, anIdentifier2)) {
                
                tempResult = tempCurrent;
            }
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer my total weight which is the cumulative weight of all of my edges
     * @return int
     */
    public int getTotalWeight() {
        
        int tempSum = 0;
        
        for (Edge anEdge: this.getEdgesAsSortedList()) {
            
            tempSum += anEdge.getWeight();
        }
        
        return tempSum;
        
    }
    
    /**
     * Initialize color for all vertices
     */
    protected void initializeAllVerticesToWhite() {
        
        for (Vertex aVertex: this.getVertices()) {
            
            aVertex.initializeColorToWhite();
        }
        
    }
    
    /**
     * Initialize distance for all vertices
     */
    protected void initializeDistanceForAllVertices() {
        
        for (Vertex aVertex: this.getVertices()) {
            
            aVertex.initializeDistanceToMaximum();
        }
        
    }
    
    /**
     * Compute the breadth first search for shortest path given aVertex as the starting point
     * @param aStartingPoint Vertex
     * @return List<Edge>
     */
    public List<Edge> computeBreadthFirstSearchForShortestPath(Vertex aStartingPoint) {
        
        List<Edge>      tempResults;
        Queue<Vertex>   tempQueue = new LinkedList<Vertex>();
        Vertex          tempCurrent;
        
        //Initialize vertices
        this.initializeAllVerticesToWhite();
        this.initializeDistanceForAllVertices();
        this.clearPredecessorsList();
        this.createAdjacencyList();
        
        //Set up the starting point
        aStartingPoint.setDistance(0);
        aStartingPoint.setColor(Color.GRAY);
        tempQueue.add(aStartingPoint);
        this.getPredecessors().put(aStartingPoint, null);
        
        while (!tempQueue.isEmpty()) {
            
            tempCurrent = tempQueue.peek();
            
            this.computeDistanceAndMarkAdjacentVerticesBreadthFirst(tempQueue,
                                                                    tempCurrent);
            
            tempQueue.remove();
            tempCurrent.setColor(Color.BLACK);
            
        }
        
        tempResults = this.filterBy(Color.BLACK, new ArrayList<Edge>(this.getEdges()));
        tempResults = this.getEdgesAsSortedListByTotalDistance(tempResults);
        
        return tempResults;
        
    }
    
    
    /**
     * Dump shortest path
     * @aStringBuilder StringBuilder
     * @param aStartingPoint Vertex
     * @param anEndPoint Vertex
     */
    public void dumpShortestPathTo(StringBuilder aStringBuilder,
                                   Vertex aStartingPoint,
                                   Vertex anEndPoint) {
        
        if (aStartingPoint.equals(anEndPoint)) {
            
            aStringBuilder.append(" " + aStartingPoint.toString() + " ");
        }
        else {
            
            if (this.getPredecessors().get(anEndPoint) == null) {
                
                aStringBuilder.append(" No path exists from " + aStartingPoint.toString() + " to " + anEndPoint.toString()  + " ");
            }
            else {
                
                this.dumpShortestPathTo(aStringBuilder, aStartingPoint, this.getPredecessors().get(anEndPoint));
                aStringBuilder.append(" " + anEndPoint.toString() + " ");
            }
        }
        
    }
    
    
    /**
     * Filter by aColor. Both vertices must have aColor
     * @param aColor Color
     * @return List<Edge>
     */
    protected List<Edge> filterBy(Color aColor,
                                  List<Edge> anEdges) {
        
        List<Edge> tempResults = new ArrayList<Edge>();
        
        for (Edge anEdge: anEdges) {
            
            if (anEdge.hasBothVerticesWithColor(aColor)) {
                
                tempResults.add(anEdge);
            }
            
        }
        
        return tempResults;
        
    }
    
    /**
     * Answer all vertices ordered by distance
     */


    /**
     * @param aQueue
     * @param aCurrent
     */
    protected void computeDistanceAndMarkAdjacentVerticesBreadthFirst(Queue<Vertex> aQueue, 
                                                                      Vertex aCurrent) {
        
        Set<Vertex>     tempAdjacentVertices;
        
        tempAdjacentVertices = this.getVerticesAdjacentTo(aCurrent);
        
        for (Vertex anAdjacent: tempAdjacentVertices) {
            
            if (anAdjacent.hasColor(Color.WHITE)) {
                
                anAdjacent.setColor(Color.GRAY);
                anAdjacent.setDistance(aCurrent.getDistance() + 1);
                aQueue.add(anAdjacent);
                this.getPredecessors().put(anAdjacent, aCurrent);
            }
            
        }
        
        
    }
    
    
    /**
     * Answer the Vertices that are adjacent to aVertex
     * @param aVertex Vertex
     * @return Set<Vertex>
     */
    protected Set<Vertex> getVerticesAdjacentTo(Vertex aVertex) {
        
        Set<Vertex> tempResults;
        Set<Edge>   tempEdges;
        
        if (this.isUndirected()) {
            
            tempEdges = this.getEdgesThatHaveSourceOrDestination(aVertex);
        }
        else {
            
            tempEdges = this.getEdgesThatHaveSource(aVertex);
        }
        
        tempResults = this.asVerticesWithout(tempEdges, aVertex);
        
        return tempResults;
        
    }
    
    /**
     * Answer the edges that have aVertex as a source or destination. This method is only useful for undirected 
     * graphs
     * @param aVertex Vertex
     * @return Set<Edge>
     */
    protected Set<Edge> getEdgesThatHaveSourceOrDestination(Vertex aVertex) {
        
        Set<Edge>   tempEdges = new HashSet<Edge>();
        
        for (Edge anEdge: this.getEdges()) {
            
            if (anEdge.hasVertexAsSourceOrDestination(aVertex)) {
                
                tempEdges.add(anEdge);
                
            }
        }
        
        return tempEdges;
    }
    
    /**
     * Answer the edges that have aVertex as a source or destination. This method is only useful for directed 
     * graphs
     * @param aVertex Vertex
     * @return Set<Edge>
     */
    protected Set<Edge> getEdgesThatHaveSource(Vertex aVertex) {
        
        Set<Edge>   tempEdges = new HashSet<Edge>();
        
        for (Edge anEdge: this.getEdges()) {
            
            if (anEdge.hasVertexAsSource(aVertex)) {
                
                tempEdges.add(anEdge);
                
            }
        }
        
        return tempEdges;
    }
    
    
    
    /**
     * Answer anEdges as Set<Vertex> without aVertex
     * @param anEdges Set<Edge>
     * @param aVertex Vertex
     * @return Set<Vertex>
     */
    protected Set<Vertex> asVerticesWithout(Set<Edge> anEdges,
                                            Vertex aVertex) {
        
        Set<Vertex>     tempResults = new HashSet<Vertex>();
        Vertex          tempCurrent;
        
        for (Edge anEdge: anEdges) {
            
            tempCurrent = anEdge.getOtherVertex(aVertex);
            if (tempCurrent != null) {
                
                tempResults.add(tempCurrent);
            }
            
        }
        
        return tempResults;
        
    }


    /**
     * Answer my predecessors
     * @return Map<Vertex,Vertex>
     */
    protected Map<Vertex, Vertex> getPredecessors() {
        return predecessors;
    }


    /**
     * Set my predecessors
     * @param predecessors Map<Vertex,Vertex>
     */
    protected void setPredecessors(Map<Vertex, Vertex> predecessors) {
        this.predecessors = predecessors;
    }
    
    
    /**
     * Create adjacency list by utilizing my list of edges
     * 
     */
    protected void createAdjacencyList() {
        
        LinkedList<String>                      tempInternalList;
        Map<String, LinkedList<String>>         tempAdjacencyList  = new HashMap<String, LinkedList<String>>();
        

        for (Edge anEdge: this.getEdges()) {
            
            tempInternalList = this.getOrCreateInternalAdjacencyList(0, tempAdjacencyList, anEdge);
            tempInternalList.add(anEdge.getVertices()[1].getIdentifier());
            
            if (this.isUndirected()) {
                
                tempInternalList = this.getOrCreateInternalAdjacencyList(1, tempAdjacencyList, anEdge);
                tempInternalList.add(anEdge.getVertices()[0].getIdentifier());
                
            }
            
        }
        

        this.setAdjacencyList(tempAdjacencyList);
        
    }


    /**
     * Answer the internal list for anEdge or create one and return it
     * @param anAdjacencyList
     * @param anEdge
     */
    protected LinkedList<String> getOrCreateInternalAdjacencyList(int anIndex,
                                                                  Map<String, LinkedList<String>> anAdjacencyList, 
                                                                  Edge anEdge) {
        LinkedList<String> tempInternalList;
        
        
        tempInternalList = anAdjacencyList.get(anEdge.getVertices()[anIndex].getIdentifier());      
        if (tempInternalList == null) {
            
            tempInternalList = new LinkedList<String>();
            anAdjacencyList.put(anEdge.getVertices()[anIndex].getIdentifier(), tempInternalList);
        }
        
        return tempInternalList;
        
    }


    /**
     * Answer my adjacencyList
     * @return Map<String,LinkedList<String>>
     */
    protected Map<String, LinkedList<String>> getAdjacencyList() {
        return adjacencyList;
    }


    /**
     * Set my adjacencyList
     * @param adjacencyList Map<String,LinkedList<String>>
     */
    protected void setAdjacencyList(Map<String, LinkedList<String>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }
        
     
    
    /**
     * Perform topological sort. This is for a directed graph
     * @return Stack
     */
    public Stack<Vertex> performTopologicalSort() 
                            throws IllegalStateException {
        
        Stack<Vertex>           tempResult = new Stack<Vertex>();
        List<Vertex>            tempVertices;
        
        //Validation
        this.validateDirectedGraph();
        
        //Initialization
        this.clearPredecessorsList();
        this.createAdjacencyList();
        this.initializeAllVerticesToWhite();
        this.initializeDistanceForAllVertices();
        tempVertices = new ArrayList<Vertex>(this.getVertices());

        //perform sort
        for (int i = 0; i < this.getVertices().size(); i++) {
            
            if (tempVertices.get(i).hasColor(Color.WHITE)) {
                
                this.basicPeformTopolocialSort(tempVertices.get(i),
                                               tempResult);
                
            }
            
        }
        
        
        return tempResult;
        
    }

    /**
     * Recursively perform topological sort
     * 
     * @param aVertex
     * @param aVisited
     * @param aStack
     */
    protected void basicPeformTopolocialSort(Vertex aVertex,
                                            Stack<Vertex> aStack) {
      
        
        Iterator<Vertex>    tempAdjacentVertices;
        Vertex              tempCurrent;
        
        //Mark current vertex as visited
        aVertex.setColor(Color.GRAY);
        
        //Perform sort
        tempAdjacentVertices = this.getVerticesAdjacentTo(aVertex).iterator();
        while (tempAdjacentVertices.hasNext()) {
            
            tempCurrent = tempAdjacentVertices.next();
            if (tempCurrent.hasColor(Color.WHITE)) {
                
                this.basicPeformTopolocialSort(tempCurrent, aStack);
            }
        }
        
        aVertex.setColor(Color.BLACK);
        aStack.push(aVertex);
        
    }


    /**
     * Throw exception if I am not a directed graph
     */
    protected void validateDirectedGraph() {
        
        if (this.isUndirected()) {
            
            throw new IllegalStateException("Must be a directed graph");
        }
        
    }
    
    
    /**
     * Find articulation points
     * @return Set<Vertex>
     */
    public Set<Vertex> findArticulationPoints() {
        
        Set<Vertex>             tempArticulationPoints = new HashSet<Vertex>();
        Map<Vertex, Integer>    tempDiscoveryTimes = new HashMap<Vertex, Integer>();
        Map<Vertex, Integer>    tempLowValues = new HashMap<Vertex, Integer>();        
        List<Vertex>            tempVertices;
        
        
        //Initialization
        this.clearPredecessorsList();
        this.createAdjacencyList();
        this.initializeAllVerticesToWhite();
        this.initializeDistanceForAllVertices();
        tempVertices = new ArrayList<Vertex>(this.getVertices());
        
        
        for (int i = 0; i < tempVertices.size(); i++) {
            
            this.basicFindArticulationPoints(tempVertices.get(i), 
                                             tempArticulationPoints, 
                                             tempDiscoveryTimes, 
                                             tempLowValues);
        }
        
        return tempArticulationPoints;
        
    }
    
    
    /**
     * 
     */
    protected void basicFindArticulationPoints(Vertex aVertex,
                                               Set<Vertex> anArticulationPoints,
                                               Map<Vertex, Integer> aDiscoveryTimes,
                                               Map<Vertex, Integer> aLowValues) {
      
        
        Iterator<Vertex>    tempAdjacentVertices;
        Vertex              tempCurrent;
        int                 tempChildren = 0;
        
        //Mark current vertex as visited
        aVertex.setColor(Color.GRAY);
        
        //Initialize discovery and low values
        this.incrementTime();
        aDiscoveryTimes.put(aVertex, new Integer(this.getTime()));
        aLowValues.put(aVertex, new Integer(this.getTime()));
        
        //Perform sort
        tempAdjacentVertices = this.getVerticesAdjacentTo(aVertex).iterator();
        while (tempAdjacentVertices.hasNext()) {
            
            tempCurrent = tempAdjacentVertices.next();
            if (tempCurrent.hasColor(Color.WHITE)) {
                
                tempChildren++;              
                this.getPredecessors().put(tempCurrent, aVertex);
                this.basicFindArticulationPoints(tempCurrent, 
                                                 anArticulationPoints, 
                                                 aDiscoveryTimes, 
                                                 aLowValues);
                
                //Determine if the subtree rooted with an adjacent vertex
                //has a connection to one of the ancestors of aVertex
                if (aLowValues.get(aVertex) != null &&
                        aLowValues.get(tempCurrent) != null) {
                    
                    aLowValues.put(aVertex, Math.min(aLowValues.get(aVertex), 
                                                     aLowValues.get(tempCurrent)));
                    
                }
                
                //Test if tempCurrent is an articulation point
                if (this.getPredecessors().get(aVertex) == null
                        && tempChildren > 1) {
                    
                    anArticulationPoints.add(aVertex);
                }
                
                if (this.getPredecessors().get(aVertex) != null 
                        && (aLowValues.get(tempCurrent) != null &&
                                aDiscoveryTimes.get(aVertex) != null &&
                                    aLowValues.get(tempCurrent) >= aDiscoveryTimes.get(aVertex))) {
                    
                    anArticulationPoints.add(aVertex);
                }
                
                
            }
            else if (this.getPredecessors().get(aVertex) != null &&
                        !tempCurrent.equals(this.getPredecessors().get(aVertex))) {
                
                    if (aLowValues.get(aVertex) != null &&
                                aLowValues.get(tempCurrent) != null) {
                        
                            aLowValues.put(aVertex, Math.min(aLowValues.get(aVertex), 
                                                             aLowValues.get(tempCurrent)));
                            
                    }
                
            }
        }
        
        aVertex.setColor(Color.BLACK);
        
    }


    /**
     * Increment time
     * @return int
     */
    protected int incrementTime() {
        
        int tempValue;
        
        tempValue = this.getTime();
        tempValue++;
        this.setTime(tempValue);
        
        return tempValue;
        
    }
    
    
    /**
     * Answer my time
     * @return int
     */
    protected int getTime() {
        return time;
    }


    /**
     * Set my time
     * @param time int
     */
    protected void setTime(int time) {
        this.time = time;
    }
    
    
    
    
}
