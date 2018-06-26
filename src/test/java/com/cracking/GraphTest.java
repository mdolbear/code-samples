package com.cracking;

import static org.junit.Assert.assertTrue;

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
     * Test for topological sort
     */
    @Test
    public void topologicalSortTest() {
        
        Graph<String>           tempGraph;
        Stack<Vertex<String>>   tempResult;
        
        tempGraph = this.buildGraphForClothing();
        
        tempResult = tempGraph.performTopologicalSort();
        assertTrue("No stack", !tempResult.isEmpty());
        
        System.out.println("Results of topological sort:");
        while (!tempResult.isEmpty()) {
            
            System.out.println(tempResult.pop());
        }
        
    }
    
    
    /**
     * Build graph for clothing
     * @return Graph<String>
     */
    protected Graph<String> buildGraphForClothing() {
        
        Graph<String>   tempGraph = new Graph<String>();
        Vertex<String>  tempVertex1;
        Vertex<String>  tempVertex2;
        Vertex<String>  tempShoes;
        Vertex<String>  tempPants;
        Vertex<String>  tempTie;
        Vertex<String>  tempBelt;
        Vertex<String>  tempShirt;
        
        //Create edge for undershorts to pants
        tempVertex1 = new Vertex<String>("undershorts");
        tempVertex2 = new Vertex<String>("pants");
        tempVertex2.addChild(tempVertex1);
        
        tempPants = tempVertex2;
        
        
        //Create edge for undershorts to shoes
        tempVertex1 =  tempVertex2; //pants
        tempVertex2 = new Vertex<String>("shoes");
        tempVertex2.addChild(tempVertex1);
        
        tempShoes = tempVertex2;
        
        //Create edge for socks to shoes
        tempVertex2 = tempShoes; //shoes
        tempVertex1 = new Vertex<String>("socks");
        tempVertex2.addChild(tempVertex1);
        
        tempGraph.addVertex(tempShoes); //shoes top dependency
        
        //watch vertex
        tempVertex1 = new Vertex<String>("watch");
        tempGraph.addVertex(tempVertex1); //watch top dependency

        
        //Create edge for pants to belt
        tempVertex1 =  tempPants;
        tempVertex2 = new Vertex<String>("belt");
        tempVertex2.addChild(tempVertex1);
        tempBelt = tempVertex2;
        
        //Create edge for pants to shoes
        tempVertex1 =  tempPants;
        tempVertex2 = tempShoes;
        tempVertex2.addChild(tempVertex1);

        
        //Create edge for shirt to belt
        tempVertex2 =  tempBelt;
        tempVertex1 = new Vertex<String>("shirt");
        tempVertex1.addChild(tempVertex2);
        tempShirt = tempVertex1;
        
        
        
        //Create edge for shirt to tie
        tempVertex1 =  tempShirt;
        tempVertex2 = new Vertex<String>("tie");
        tempVertex2.addChild(tempVertex1);
        tempTie = tempVertex2;
        
        //Create edge for tie to jacket
        tempVertex1 =  tempTie;
        tempVertex2 = new Vertex<String>("jacket");
        tempVertex2.addChild(tempVertex1);
        tempGraph.addVertex(tempVertex2); //jacket top dependency
        
        //Create edge for belt to jacket
        tempVertex1 =  tempGraph.getVertexWithData("belt");
        tempVertex2 = tempGraph.getVertexWithData("jacket");
        tempVertex2.addChild(tempVertex1);
        
        
        return tempGraph;
        
    }
    
    /**
     * Test of isThereAPathFrom
     */
    @Test
    public void isThereAPathFromTest() {
        
        Graph<String>           tempGraph;
        Vertex<String>          tempFromVertex;
        Vertex<String>          tempToVertex;
        
        tempGraph = this.buildGraphForClothing();
        
        //Show there is a path
        tempFromVertex = tempGraph.getVertexWithData("shoes");
        tempToVertex = tempGraph.getVertexWithData("undershorts");
        assertTrue("Didn't properly retrieve vertexes", tempFromVertex != null &&
                                                            tempToVertex != null);
        
        assertTrue("Should show a path exists", 
                   tempGraph.isThereAPathFrom(tempFromVertex, tempToVertex));
        
        //Show there is no path
        tempGraph.clearVisitedHistory();
        tempFromVertex = tempGraph.getVertexWithData("pants");
        tempToVertex = tempGraph.getVertexWithData("watch");
        assertTrue("Didn't properly retrieve vertexes", tempFromVertex != null &&
                                                            tempToVertex != null);
        
        assertTrue("Should show a path does not exist", 
                   !tempGraph.isThereAPathFrom(tempFromVertex, tempToVertex));
        
    }

}
