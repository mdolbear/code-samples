package com.cracking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 *
 *
 */
public class Graph<T> {

    
    private List<Vertex<T>> vertices;
    
    /**
     * Answer an instance for the following arguments
     */
    public Graph() {
        
        super();
        this.setVertices(new ArrayList<Vertex<T>>());
    }

    
    /**
     * Add aVertex to me
     * @param aVertex GraphNode<T>
     */
    public void addVertex(Vertex<T> aVertex) {
        
        this.getVertices().add(aVertex);
        
    }
    
    /**
     * Answer my vertices
     * @return List<Vertex<T>>
     */
    protected List<Vertex<T>> getVertices() {
        return vertices;
    }

    /**
     * Set my vertices
     * @param vertices List<Vertex<T>>
     */
    protected void setVertices(List<Vertex<T>> vertices) {
        this.vertices = vertices;
    }
    
   /**
    * Perform dfs search
    * @param aVertex Vertex<T>
    */
    public void performDepthFirstSearch(Vertex<T> aVertex) {
        
        if (aVertex != null) {
            
            this.visit(aVertex);
            aVertex.setVisited(true);
            
            for (Vertex<T> childVertex: aVertex.getChildren())  {
                
                //Dives deep on all non-visited children first
                if (!childVertex.isVisited()) {
                    
                    this.performDepthFirstSearch(childVertex);
                }
            }
            
        }
        
        
    }
    
    /**
     * Perform breadth first search
     * @param aVertex Vertex<T>
     */
    public void performBreadthFirstSearch(Vertex<T> aVertex) {
        
        Queue<Vertex<T>> tempQueue = new Queue<Vertex<T>>();
        Vertex<T>        tempCurrentVertex;
        
        aVertex.setVisited(true);
        tempQueue.add(aVertex);
        
        while (!tempQueue.isEmpty()) {
            
            tempCurrentVertex = tempQueue.remove();
            
            //Visit "breadth" at the same level first
            this.visit(tempCurrentVertex);
            
            for (Vertex<T> aChildVertex: tempCurrentVertex.getChildren()) {
                
                //Save all children to the queue to visit later on the next iteration of this loop
                //
                if (!aChildVertex.isVisited()) {
                    
                    aChildVertex.setVisited(true);
                    tempQueue.add(aChildVertex);
                }
                
            }
        }
        
    }
    
    
    /**
     * Visit aVertex. Do something with it. This method does nothing currently other than dump its data
     * @param aVertex Vertex<T>
     */
    protected void visit(Vertex<T> aVertex) {
        
        System.out.println(aVertex.toString());
    }
    

    /**
     * Perform topological sort. This is for a directed graph
     * @return Stack
     */
    public Stack<Vertex<T>> performTopologicalSort() 
                            throws IllegalStateException {
        
        Stack<Vertex<T>>          tempResult = new Stack<Vertex<T>>();
        List<Vertex<T>>           tempVertices;
        
        tempVertices = new ArrayList<Vertex<T>>(this.getVertices());

        //perform sort
        for (int i = 0; i < this.getVertices().size(); i++) {
            
            if (!tempVertices.get(i).isVisited()) {
                
                this.basicPerformTopologicalSort(tempVertices.get(i),
                                               tempResult);
                
            }
            
        }
        
        
        return tempResult;
        
    }

    /**
     * Recursively perform topological sort
     * 
     * @param aVertex
     * @param aStack
     */
    protected void basicPerformTopologicalSort(Vertex<T> aVertex,
                                               Stack<Vertex<T>> aStack) {
      
        
        Iterator<Vertex<T>>    tempAdjacentVertices;
        Vertex<T>              tempCurrent;
        
        //Mark vertex as visited
        aVertex.setVisited(true);
        
        //Perform sort
        tempAdjacentVertices = aVertex.getChildren().iterator();
        while (tempAdjacentVertices.hasNext()) {
            
            tempCurrent = tempAdjacentVertices.next();
            if (!tempCurrent.isVisited()) {
                
                this.basicPerformTopologicalSort(tempCurrent, aStack);
            }
        }
        
        aStack.push(aVertex);
        
    }
    
    /**
     * Answer the vertex that has aData or null
     * @param aData T
     * @return Vertex<T>
     */
    public Vertex<T> getVertexWithData(T aData) {
        
        Vertex<T>           tempResult = null;
        Vertex<T>           tempCurrent;
        Iterator<Vertex<T>> tempVertices;
        
        tempVertices = this.getVertices().iterator();
        while (tempResult == null &&
                tempVertices.hasNext()) {
            
            tempCurrent = tempVertices.next();
            tempResult = tempCurrent.getVertexWithData(aData);
        }
        
        return tempResult;
        
    }
    
    //Methods for challenges:
    
    /**
     * Answer whether there is a path from aFromVertex to aToVertex
     * @param aFromVertex Vertex<T>
     * @param aToVertex Vertex<T>
     * @return boolean
     */
    public boolean isThereAPathFrom(Vertex<T> aFromVertex,
                                    Vertex<T> aToVertex) {
        
        Queue<Vertex<T>> tempQueue = new Queue<Vertex<T>>();
        Vertex<T>        tempCurrentVertex;
        boolean          tempPathExists = false;
        
        aFromVertex.setVisited(true);
        tempQueue.add(aFromVertex);
        
        while (!tempQueue.isEmpty() &&
                    !tempPathExists) {
            
            tempCurrentVertex = tempQueue.remove();
            
            //Visit "breadth" at the same level first
            tempPathExists = tempCurrentVertex.hasData(aToVertex.getData());
            
            if (!tempPathExists) {
                
                //Explore my children if the current vertex is not the same as aToVertex
                for (Vertex<T> aChildVertex: tempCurrentVertex.getChildren()) {
                    
                    //Save all children to the queue to visit later on the next iteration of this loop
                    //
                    if (!aChildVertex.isVisited()) {
                        
                        aChildVertex.setVisited(true);
                        tempQueue.add(aChildVertex);
                    }
                    
                }
                
            }
            
            
        }
        
        return tempPathExists;
        
        
    }
    
    
    /**
     * Clear all visited history
     */
    public void clearVisitedHistory() {
        
        for (Vertex<T> aVertex: this.getVertices()) {
            
            this.clearVistedHistory(aVertex); //Calls a "depth first algorithm" to clear visted nodes
        }
    }
    
    /**
     * Clear visited history. This will only clear nodes that have already been visited.
     * @param aVertex Vertex<T>
     */
     protected void clearVistedHistory(Vertex<T> aVertex) {
         
         if (aVertex != null) {
             
             this.visit(aVertex);
             aVertex.setVisited(false);
             
             for (Vertex<T> childVertex: aVertex.getChildren())  {
                 
                 //Dives deep on all visited children first
                 if (childVertex.isVisited()) {
                     
                     this.clearVistedHistory(childVertex);
                 }
             }
             
         }
         
         
     }
     
     
    
}
