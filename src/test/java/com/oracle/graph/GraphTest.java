package com.oracle.graph;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

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
     * Test minimum spanning tree Kruskal
     */
    @Test
    public void testMinimumSpanningTreeKruskal() {
        
        Graph tempGraph;
        Graph tempResultGraph;
        
        tempGraph = this.buildInitialUndirectionalGraph();
        tempResultGraph = tempGraph.produceMinimumSpanningTreeKruskal();
        assertTrue("No MST", tempResultGraph != null && (tempResultGraph.getEdges().size() < tempGraph.getEdges().size()));
        
        System.out.println("Total weight of result: " + tempResultGraph.getTotalWeight());
        System.out.println("Edges in resulting tree: ");
        for (Edge anEdge: tempResultGraph.getEdgesAsSortedList()) {
            
            System.out.println(anEdge.toString());
        }
        
    }
    
    /**
     * Test shortest path algorithm
     */
    @Test
    public void shortestPathTest() {
        
        Vertex          tempStartingVertex;
        Graph           tempGraph;
        List<Edge>      tempListOfShortestPaths;
        StringBuilder   tempBuilder = new StringBuilder();
        
        tempGraph = this.buildGraphForShortestPathSearch();
        tempStartingVertex = tempGraph.getVertexFor("s");
        
        tempListOfShortestPaths = tempGraph.computeBreadthFirstSearchForShortestPath(tempStartingVertex);
        assertTrue("No tree", !tempListOfShortestPaths.isEmpty());
        System.out.println("Shortest path list: " + tempListOfShortestPaths);
        
        tempGraph.dumpShortestPathTo(tempBuilder, tempStartingVertex, tempGraph.getVertexFor("v"));
        System.out.println("Shortest Path for s to v: " + tempBuilder.toString());
        
    }
    
    /**
     * Test for topological sort
     */
    @Test
    public void topologicalSortTest() {
        
        Graph           tempGraph;
        Stack<Vertex>   tempResult;
        
        tempGraph = this.buildGraphForClothing();
        
        tempResult = tempGraph.performTopologicalSort();
        assertTrue("No stack", !tempResult.isEmpty());
        
        System.out.println("Results of topological sort:");
        while (!tempResult.isEmpty()) {
            
            System.out.println(tempResult.pop());
        }
        
    }
    
    /**
     * Build initial graph
     * @return Graph
     */
    protected Graph buildInitialUndirectionalGraph() {
        
        Graph   tempGraph = new Graph(true); //undirectional
        Vertex  tempVertex1;
        Vertex  tempVertex2;
        Edge    tempEdge;
        
        //Create edge for a - b
        tempVertex1 = new Vertex("a");
        tempVertex2 = new Vertex("b");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex1);
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for b - h
        tempVertex1 = tempGraph.getVertexFor("b");
        tempVertex2 = new Vertex("h");
        tempEdge = new Edge(11);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for a - h
        tempVertex1 = tempGraph.getVertexFor("a");
        tempVertex2 = tempGraph.getVertexFor("h");
        tempEdge = new Edge(8);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addEdge(tempEdge);
        
        //Create edge for b to c
        tempVertex1 = tempGraph.getVertexFor("b");
        tempVertex2 = new Vertex("c");
        tempEdge = new Edge(8);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);

        
        //Create edge for h - g
        tempVertex1 = tempGraph.getVertexFor("h");
        tempVertex2 = new Vertex("g");
        tempEdge = new Edge(1);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for i - h
        tempVertex1 = new Vertex("i");
        tempVertex2 = tempGraph.getVertexFor("h");
        tempEdge = new Edge(7);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex1);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for i - g
        tempVertex1 = tempGraph.getVertexFor("i");
        tempVertex2 = tempGraph.getVertexFor("g");
        tempEdge = new Edge(6);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addEdge(tempEdge);
        
        //Create edge for i - c
        tempVertex1 = tempGraph.getVertexFor("i");
        tempVertex2 = tempGraph.getVertexFor("c");
        tempEdge = new Edge(2);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addEdge(tempEdge);
        
        //Create edge for c to d
        tempVertex1 = tempGraph.getVertexFor("c");
        tempVertex2 = new Vertex("d");
        tempEdge = new Edge(7);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for g to f
        tempVertex1 = tempGraph.getVertexFor("g");
        tempVertex2 = new Vertex("f");
        tempEdge = new Edge(2);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for c to f
        tempVertex1 = tempGraph.getVertexFor("c");
        tempVertex2 = tempGraph.getVertexFor("f");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addEdge(tempEdge);
        
        //Create edge d to f
        tempVertex1 = tempGraph.getVertexFor("d");
        tempVertex2 = tempGraph.getVertexFor("f");
        tempEdge = new Edge(14);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addEdge(tempEdge);
        
        //Create edge d to e
        tempVertex1 = tempGraph.getVertexFor("d");
        tempVertex2 = new Vertex("e");
        tempEdge = new Edge(9);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        //Create e to f
        tempVertex1 = tempGraph.getVertexFor("e");
        tempVertex2 = tempGraph.getVertexFor("f");
        tempEdge = new Edge(10);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addEdge(tempEdge);
        
        
        return  tempGraph;
    }
    
    
    /**
     * Build graph for shortest path search
     * @return Graph
     */
    protected Graph buildGraphForShortestPathSearch() {
        
        Graph   tempGraph = new Graph(true); //undirectional
        Vertex  tempVertex1;
        Vertex  tempVertex2;
        Edge    tempEdge;
        
        //Create edge for v - r
        tempVertex1 = new Vertex("v");
        tempVertex2 = new Vertex("r");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex1);
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        
        //Create edge for r - s
        tempVertex1 =  tempGraph.getVertexFor("r");
        tempVertex2 = new Vertex("s");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for s - w
        tempVertex1 =  tempGraph.getVertexFor("s");
        tempVertex2 = new Vertex("w");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for w - t
        tempVertex1 =  tempGraph.getVertexFor("w");
        tempVertex2 = new Vertex("t");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for t - u
        tempVertex1 =  tempGraph.getVertexFor("t");
        tempVertex2 = new Vertex("u");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for w - x
        tempVertex1 =  tempGraph.getVertexFor("w");
        tempVertex2 = new Vertex("x");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        
        //Create edge for x - y
        tempVertex1 =  tempGraph.getVertexFor("x");
        tempVertex2 = new Vertex("y");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        
        
        
        return tempGraph;
        
    }
        
        
    /**
     * Build graph for clothing
     * @return Graph
     */
    protected Graph buildGraphForClothing() {
        
        Graph   tempGraph = new Graph(false);
        Vertex  tempVertex1;
        Vertex  tempVertex2;
        Edge    tempEdge;
        
        //Create edge for undershorts to pants
        tempVertex1 = new Vertex("undershorts");
        tempVertex2 = new Vertex("pants");
        tempEdge = new Edge(1);
        tempEdge.setUndirected(false);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex1);
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        
        //Create edge for undershorts to shoes
        tempVertex1 =  tempGraph.getVertexFor("undershorts");
        tempVertex2 = new Vertex("shoes");
        tempEdge = new Edge(2);
        tempEdge.setUndirected(false);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for socks to shoes
        tempVertex2 =  tempGraph.getVertexFor("shoes");
        tempVertex1 = new Vertex("socks");
        tempEdge = new Edge(3);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        tempEdge.setUndirected(false);
        
        tempGraph.addVertex(tempVertex1);
        tempGraph.addEdge(tempEdge);
        
        //watch vertex
        tempVertex1 = new Vertex("watch");        
        tempGraph.addVertex(tempVertex1);

        
        //Create edge for pants to belt
        tempVertex1 =  tempGraph.getVertexFor("pants");
        tempVertex2 = new Vertex("belt");
        tempEdge = new Edge(4);
        tempEdge.setUndirected(false);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for pants to shoes
        tempVertex1 =  tempGraph.getVertexFor("pants");
        tempVertex2 = tempGraph.getVertexFor("shoes");
        tempEdge = new Edge(5);
        tempEdge.setUndirected(false);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);

        tempGraph.addEdge(tempEdge);
        
        //Create edge for shirt to belt
        tempVertex2 =  tempGraph.getVertexFor("belt");
        tempVertex1 = new Vertex("shirt");
        tempEdge = new Edge(6);
        tempEdge.setUndirected(false);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex1);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for shirt to tie
        tempVertex1 =  tempGraph.getVertexFor("shirt");
        tempVertex2 = new Vertex("tie");
        tempEdge = new Edge(7);
        tempEdge.setUndirected(false);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for tie to jacket
        tempVertex1 =  tempGraph.getVertexFor("tie");
        tempVertex2 = new Vertex("jacket");
        tempEdge = new Edge(8);
        tempEdge.setUndirected(false);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for belt to jacket
        tempVertex1 =  tempGraph.getVertexFor("belt");
        tempVertex2 = tempGraph.getVertexFor("jacket");
        tempEdge = new Edge(9);
        tempEdge.setUndirected(false);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);

        tempGraph.addEdge(tempEdge);
        
        
        return tempGraph;
        
    }
    
    
    /**
     * Test for articulation points 1
     */
    @Test
    public void articulationPointTest1() {
        
        Graph               tempGraph;
        Set<Vertex>         tempResult;
        Iterator<Vertex>    tempItr;
        
        tempGraph = this.buildArticulationPointGraph1();
        
        tempResult = tempGraph.findArticulationPoints();
        assertTrue("No articulation points 1", !tempResult.isEmpty());
        
        System.out.println("Results of articulation point search 1");
        tempItr = tempResult.iterator();
        while (tempItr.hasNext()) {
            
            System.out.println(tempItr.next().toString());
        }
        
    }
    
    /**
     * Build graph for articulation points 1
     * @return Graph
     */
    protected Graph buildArticulationPointGraph1() {
        
        Graph   tempGraph = new Graph(true); //undirectional
        Vertex  tempVertex1;
        Vertex  tempVertex2;
        Edge    tempEdge;
        
        //Create edge for 1 - 0
        tempVertex1 = new Vertex("1");
        tempVertex2 = new Vertex("0");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex1);
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        
        //Create edge for 0 - 2
        tempVertex1 =  tempGraph.getVertexFor("0");
        tempVertex2 = new Vertex("2");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for 2 - 1
        tempVertex1 =  tempGraph.getVertexFor("2");
        tempVertex2 = tempGraph.getVertexFor("1");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addEdge(tempEdge);
        
        //Create edge for 0 - 3
        tempVertex1 =  tempGraph.getVertexFor("0");
        tempVertex2 = new Vertex("3");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for 3 - 4
        tempVertex1 =  tempGraph.getVertexFor("3");
        tempVertex2 = new Vertex("4");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
               
        
        
        return tempGraph;
        
    }
    
    
    
    /**
     * Test for articulation points 2
     */
    @Test
    public void articulationPointTest2() {
        
        Graph               tempGraph;
        Set<Vertex>         tempResult;
        Iterator<Vertex>    tempItr;
        
        tempGraph = this.buildArticulationPointGraph2();
        
        tempResult = tempGraph.findArticulationPoints();
        assertTrue("No articulation points 2", !tempResult.isEmpty());
        
        System.out.println("Results of articulation point search 2");
        tempItr = tempResult.iterator();
        while (tempItr.hasNext()) {
            
            System.out.println(tempItr.next().toString());
        }
        
    }
    
    /**
     * Build graph for articulation points 2
     * @return Graph
     */
    protected Graph buildArticulationPointGraph2() {
        
        Graph   tempGraph = new Graph(true); //undirectional
        Vertex  tempVertex1;
        Vertex  tempVertex2;
        Edge    tempEdge;
        
        //Create edge for 0 - 1
        tempVertex1 = new Vertex("0");
        tempVertex2 = new Vertex("1");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex1);
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        
        //Create edge for 1 - 2
        tempVertex1 =  tempGraph.getVertexFor("1");
        tempVertex2 = new Vertex("2");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);

        
        //Create edge for 2 - 3
        tempVertex1 =  tempGraph.getVertexFor("2");
        tempVertex2 = new Vertex("3");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
               
        
        
        return tempGraph;
        
    }
    
    /**
     * Test for articulation points 3
     */
    @Test
    public void articulationPointTest3() {
        
        Graph               tempGraph;
        Set<Vertex>         tempResult;
        Iterator<Vertex>    tempItr;
        
        tempGraph = this.buildArticulationPointGraph3();
        
        tempResult = tempGraph.findArticulationPoints();
        assertTrue("No articulation points 3", !tempResult.isEmpty());
        
        System.out.println("Results of articulation point search 3");
        tempItr = tempResult.iterator();
        while (tempItr.hasNext()) {
            
            System.out.println(tempItr.next().toString());
        }
        
    }
    
    /**
     * Build graph for articulation points 3
     * @return Graph
     */
    protected Graph buildArticulationPointGraph3() {
        
        Graph   tempGraph = new Graph(true); //undirectional
        Vertex  tempVertex1;
        Vertex  tempVertex2;
        Edge    tempEdge;
        
        //Create edge for 0 - 1
        tempVertex1 = new Vertex("0");
        tempVertex2 = new Vertex("1");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex1);
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        
        //Create edge for 1 - 2
        tempVertex1 =  tempGraph.getVertexFor("1");
        tempVertex2 = new Vertex("2");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for 2 - 0
        tempVertex1 =  tempGraph.getVertexFor("2");
        tempVertex2 = tempGraph.getVertexFor("0");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addEdge(tempEdge);
        
        //Create edge for 1 - 3
        tempVertex1 =  tempGraph.getVertexFor("1");
        tempVertex2 = new Vertex("3");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for 1 - 4
        tempVertex1 =  tempGraph.getVertexFor("1");
        tempVertex2 = new Vertex("4");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for 1 - 6
        tempVertex1 =  tempGraph.getVertexFor("1");
        tempVertex2 = new Vertex("6");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for 3 - 5
        tempVertex1 =  tempGraph.getVertexFor("3");
        tempVertex2 = new Vertex("5");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addVertex(tempVertex2);
        tempGraph.addEdge(tempEdge);
        
        //Create edge for 4 - 5
        tempVertex1 =  tempGraph.getVertexFor("4");
        tempVertex2 = tempGraph.getVertexFor("5");
        tempEdge = new Edge(4);
        tempEdge.addVertex(tempVertex1);
        tempEdge.addVertex(tempVertex2);
        
        tempGraph.addEdge(tempEdge);
        
        
        return tempGraph;
        
    }
    
    
}
