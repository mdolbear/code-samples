package com.oracle.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.oracle.utils.SetUtils;

/**
 *
 *
 */
public class Edge {

    private Vertex[] vertices;
    private boolean undirected;
    private int weight;
    
    
    /**
     * Answer an instance for the following arguments
     * @param aWeight int
     */
    public Edge(int aWeight) {
        
        super();
        this.setUndirected(true);
        this.setWeight(aWeight);
        this.clearVertices();
        
    }


    /**
     * Clear vertices
     */
    protected void clearVertices() {
        
        this.setVertices(new Vertex[2]);
    }


    /**
     * Add aVertex to me
     */
    public void addVertex(Vertex aVertex) 
                throws IllegalStateException {
        
      if (this.getVertices()[0] == null)  {
          
          this.getVertices()[0] = aVertex;
      }
      else if (this.getVertices()[1] == null) {
          
          this.getVertices()[1] = aVertex;
      }
      else {
          
          throw new IllegalStateException("Two vertices already exist for this edge");
      }
      
    }
    
    /**
     * Answer my vertices as a set
     * @return Set<Vertex>
     */
    public Set<Vertex> getVerticesAsSet() {
        
        List<Vertex>    tempResults;
        
        tempResults = Arrays.asList(this.getVertices());
        
        return new HashSet<Vertex>(tempResults);
        
    }
    
    /**
     * Answer the other vertex that is not aVertex. If aVertex is not part of me, return null
     * @param aVertex Vertex
     * @return Vertex
     */
    public Vertex getOtherVertex(Vertex aVertex) {
        
        Set<Vertex> tempVertices;
        Vertex      tempResult = null;
        boolean     tempRemoveResult;
        
        tempVertices = this.getVerticesAsSet();
        tempRemoveResult = tempVertices.remove(aVertex);
        if (tempRemoveResult) {
            
            tempResult = tempVertices.iterator().next();
        }
        
        return tempResult;
    }
    
    /**
     * Answer whether both vertices have aColor
     * @param aColor Color
     * @return boolean
     */
    public boolean hasBothVerticesWithColor(Color aColor) {
        
        return  this.getVertices()[0] != null &&
                this.getVertices()[0].hasColor(aColor) &&
                this.getVertices()[1] != null &&
                this.getVertices()[1].hasColor(aColor);
        
    }
    
    
    /**
     * Answer my vertices
     * @return Vertex[]
     */
    protected Vertex[] getVertices() {
        return vertices;
    }


    /**
     * Set my vertices
     * @param verticies Vertex[]
     */
    protected void setVertices(Vertex[] vertices) {
        this.vertices = vertices;
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
    public void setUndirected(boolean undirected) {
        this.undirected = undirected;
    }


    /**
     * Answer my weight
     * @return int
     */
    public int getWeight() {
        return weight;
    }


    /**
     * Set my weight
     * @param weight int
     */
    protected void setWeight(int weight) {
        this.weight = weight;
    }
    
    /**
     * Answer whether or not I am equal to anotherEdge. Compares vertices only
     * @param anotheEdge Object
     * @return boolean
     */
    @Override
    public boolean equals(Object anotherEdge) {
        
        Edge    tempAnotherEdge;
        boolean tempResult = false;
        
        if (anotherEdge instanceof Edge) {
            
            tempAnotherEdge = (Edge)anotherEdge;
            
            if ((tempAnotherEdge.isUndirected() && this.isUndirected()) ||
                    (!tempAnotherEdge.isUndirected() && !this.isUndirected())) {
                
                if (this.isUndirected()) {
                    
                    tempResult = this.compareUnidirected(tempAnotherEdge);
                }
                else {
                    
                    tempResult = this.compareDirected(tempAnotherEdge);
                }
            }
            
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer my hashCode
     * @return int
     */
    @Override
    public int hashCode() {
        
        int tempValue = 0;
        
        if (this.getVertices()[0] != null) {
            
            tempValue += this.getVertices()[0].hashCode();
        }
        
        if (this.getVertices()[1] != null) {
            
            tempValue += this.getVertices()[1].hashCode();
        }
        
        return tempValue;
        
    }

    /**
     * Since I am a directed edge, my vertices must be in a specific order
     * @param anotherEdge Edge
     * @return boolean
     */
    protected boolean compareDirected(Edge anotherEdge) {
        
        boolean tempResult = false;
        
        tempResult = this.allVerticesNull(anotherEdge);
        if (!tempResult &&
                this.allVerticesNotNull(anotherEdge)) {
            
            tempResult = this.getVertices()[0].equals(anotherEdge.getVertices()[0]) &&
                            this.getVertices()[1].equals(anotherEdge.getVertices()[1]);
        }
        
        return tempResult;
    }
    
    /**
     * Answer whether my vertices and anotherEdge vertices are all null
     * @param anotherEdge Edge
     * @return boolean
     */
    protected boolean   allVerticesNull(Edge anotherEdge) {
        
        return this.getVertices()[0] == null &&
                this.getVertices()[1] == null &&
                    anotherEdge.getVertices()[0] == null &&
                        anotherEdge.getVertices()[1] == null;
        
    }
    
    /**
     * Answer whether my vertices and anotherEdge vertices are all null
     * @param anotherEdge Edge
     * @return boolean
     */
    protected boolean   allVerticesNotNull(Edge anotherEdge) {
        
        return this.getVertices()[0] != null &&
                this.getVertices()[1] != null &&
                    anotherEdge.getVertices()[0] != null &&
                        anotherEdge.getVertices()[1] != null;
        
    }
    

    

    /**
     * Compare undirected edges
     * @param anotherEdge
     * @return boolean
     */
    protected boolean compareUnidirected(Edge anotherEdge) {
        
        Set<Vertex>     tempMyVertexSet;
        Set<Vertex>     tempAnotherVertexSet;
        Set<Vertex>     tempMySetDifference;
        Set<Vertex>     tempAnotherSetDifference;
        
        tempMyVertexSet = this.getVerticesAsSet();
        tempAnotherVertexSet = anotherEdge.getVerticesAsSet();
        tempMySetDifference = new HashSet<Vertex>(tempMyVertexSet);
        tempAnotherSetDifference = new HashSet<Vertex>(tempAnotherVertexSet);
        
        
        //Set difference between two equal sets should be empty
        tempMySetDifference.removeAll(tempAnotherVertexSet);
        tempAnotherSetDifference.removeAll(tempMyVertexSet);
        
        return tempMySetDifference.isEmpty() && tempAnotherSetDifference.isEmpty();
        
    }
    
    /**
     * Answer my string representation
     * @return String
     */
    @Override
    public String toString() {
        
        StringBuilder   tempBuilder = new StringBuilder();
        
        tempBuilder.append(" weight: ");
        tempBuilder.append(this.getWeight());
        
        tempBuilder.append(" vertex[0]: ");
        if (this.getVertices()[0] != null) {
            
            tempBuilder.append(this.getVertices()[0]);
            tempBuilder.append(" ");
        }
        else {
            
            tempBuilder.append("null ");
        }
        
        tempBuilder.append(" vertex[1]: ");
        if (this.getVertices()[1] != null) {
            
            tempBuilder.append(this.getVertices()[1]);
            tempBuilder.append(" ");
        }
        else {
            
            tempBuilder.append("null ");
        }
        
        
        tempBuilder.append("undirected: ");
        tempBuilder.append(this.isUndirected());
        
        return tempBuilder.toString();
        
    }

    
    /**
     * Handle my comparison for Kruksal
     * @param aVertexSets
     * @param aNewVerteces
     * @param aNewEdges
     * @param anEdge
     * @return Set<Set<Vertex>>
     */
    public Set<Set<Vertex>> handleEdgeKruskal(Set<Set<Vertex>> aVertexSets,
                                              Set<Vertex> aNewVerteces, 
                                              Set<Edge> aNewEdges) {
        
        Vertex              tempFirstVertex;
        Vertex              tempSecondVertex;
        Set<Vertex>         tempFirstVertexSet;
        Set<Vertex>         tempSecondVertexSet;
        boolean             tempSameSets;
        
        tempFirstVertex = this.getVertices()[0];
        tempSecondVertex = this.getVertices()[1];
        
        tempFirstVertexSet = this.getSetFor(aVertexSets, tempFirstVertex);
        tempSecondVertexSet = this.getSetFor(aVertexSets, tempSecondVertex);
        
        //I need to know whether or not they are the exact same instance -- hence the test for the original hashCode       
        tempSameSets = SetUtils.getInstance().isSameSet(tempFirstVertexSet, tempSecondVertexSet);
        
        //Perform evaluation
        if (!tempSameSets) {
            
            tempFirstVertexSet.addAll(tempSecondVertexSet);
            aVertexSets  = this.newSetWithout(aVertexSets, tempSecondVertexSet);
            
            aNewVerteces.add(tempFirstVertex);
            aNewVerteces.add(tempSecondVertex);
            aNewEdges.add(this);
            
        }
        
        return aVertexSets;
        
    }
    
    /**
     * Answer a new set without aVertexSet
     * @return Set<Set<Vertex>>
     */
    protected Set<Set<Vertex>> newSetWithout( Set<Set<Vertex>> oldVertexSets,
                                              Set<Vertex> aVertexSet) {
        
        return SetUtils.getInstance().newSetWithout(oldVertexSets, aVertexSet);
        
    }
    
    /**
     * Answer the set that holds aVertex
     * @param aVertexSets Set<Set<Vertex>>
     * @return Set<Vertex>
     */
    protected Set<Vertex> getSetFor(Set<Set<Vertex>> aVertexSets,
                                    Vertex aVertex) {
        
       return SetUtils.getInstance().getSetFor(aVertexSets, aVertex);
        
    }
    
    /**
     * Answer whether or not I am an Edge that has anIdentifier1 and anIdentifier2 for my underlying
     * Vertices
     * @param anIdentifier1 String
     * @param anIdentifier2 String
     * @return boolean
     */
    public boolean hasVertexIdentifiers(String anIdentifier1,
                                        String anIdentifier2) {
        
        return this.getVertices()[0] != null &&
                this.getVertices()[0].hasIdentifier(anIdentifier1) &&
                this.getVertices()[1] != null &&
                this.getVertices()[1].hasIdentifier(anIdentifier2);
        
        
    }
    
    /**
     * Answer whether or not I am an Edge that has aVertex as one of its vertices
     * @param aVertex Vertex
     * @return boolean
     */
    public boolean hasVertexAsSourceOrDestination(Vertex aVertex) {
        
        return (this.getVertices()[0] != null &&
                this.getVertices()[0].equals(aVertex)) ||
                (this.getVertices()[1] != null &&
                this.getVertices()[1].equals(aVertex));
    }
    
    /**
     * Answer whether or not I am an Edge that has aVertex as its source
     * @param aVertex Vertex
     * @return boolean
     */
    public boolean hasVertexAsSource(Vertex aVertex) {
        
        return (this.getVertices()[0] != null &&
                this.getVertices()[0].equals(aVertex));
        
    }
    
    
    
    /**
     * Answer the total distance of my vertices
     * @return int
     */
    public int getTotalDistanceOfVertices() {
        
        int tempResult = 0;
        
        if (this.getVertices()[0] != null) {
            
            tempResult += this.getVertices()[0].getDistance();
        }
        
        if (this.getVertices()[1] != null) {
            
            tempResult += this.getVertices()[1].getDistance();
        }
        
        return tempResult;
        
        
    }
    
}
