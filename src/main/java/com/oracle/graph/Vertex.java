package com.oracle.graph;

/**
 *
 *
 */
public class Vertex {

    private String identifier;
    private Color color;
    private int distance;
    
    
    /**
     * Answer an instance for the following arguments
     * @param anIdentifier String
     */
    public Vertex(String anIdentifier) {
        
        super();
        this.setIdentifier(anIdentifier);
        
    }

    /**
     * Answer whether I am for anIdentifier
     * @param anIdentifier 
     */
    public boolean hasIdentifier(String anIdentifer) {
        
        return this.getIdentifier() != null &&
                    this.getIdentifier().equals(anIdentifer);
        
    }
    
    
    /**
     * Answer my identifier
     * @return String
     */
    protected String getIdentifier() {
        return identifier;
    }

    /**
     * Set my identifier
     * @param identifier String
     */
    protected void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    
    /**
     * Answer whether or not I am equal to anotherVertex
     * @param anotherVertex Object
     * @return boolean
     */
    @Override
    public boolean equals(Object anotherVertex) {
        
        Vertex  tempAnotherVertex;
        boolean tempResult = false;
        
        if (anotherVertex instanceof Vertex) {
            
            tempAnotherVertex = (Vertex)anotherVertex;
            
            //either both identifiers null or equal
            tempResult = (this.getIdentifier() == null &&
                                tempAnotherVertex.getIdentifier() == null) ||
                            (this.getIdentifier() != null) &&
                                 tempAnotherVertex.getIdentifier() != null &&
                                    this.getIdentifier().equals(tempAnotherVertex.getIdentifier());
                                    
        }
        
        return tempResult;
    }
    
    /**
     * Answer my hashcode
     * @return int
     */
    @Override
    public int hashCode() {
        
        int tempValue = this.getClass().hashCode();
        
        if (this.getIdentifier() != null) {
            
            tempValue += this.getIdentifier().hashCode();
        }
        
        return tempValue;
        
    }
    
    /**
     * Answer my string representation
     * @return String
     */
    @Override
    public String toString() {
        
        StringBuilder   tempBuilder = new StringBuilder();
        
        tempBuilder.append(" id: ");        
        if (this.getIdentifier() != null) {
            tempBuilder.append(this.getIdentifier());
        }
        else {
            tempBuilder.append("null");
        }
        
        tempBuilder.append(" distance: ");
        tempBuilder.append(this.getDistance());
        
        tempBuilder.append(" color: ");       
        if (this.getColor() != null) {
            tempBuilder.append(this.getColor().name());
        }
        else {
            tempBuilder.append("null ");
        }
        
        
        return tempBuilder.toString();
        
    }

    
    /**
     * Initialize color to Color.White
     * 
     */
    public void initializeColorToWhite() {
        
        this.setColor(Color.WHITE);
    }
    
    /**
     * Answer whether I have aColor
     * @param aColor Color
     * @return boolean
     */
    public boolean hasColor(Color aColor) {
        
        return this.getColor() != null &&
                this.getColor().equals(aColor);
        
    }
    
    
    /**
     * Answer my color
     * @return Color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Set my color
     * @param color Color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    
    /**
     * Initialize distance. All distances should be set to max at the beginning
     */
    public void initializeDistanceToMaximum() {
        
        this.setDistance(Integer.MAX_VALUE);
    }
    
    /**
     * Answer my distance
     * @return int
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Set my distance
     * @param distance int
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }
    
    

    
}
