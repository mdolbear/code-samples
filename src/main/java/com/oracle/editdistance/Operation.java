package com.oracle.editdistance;

/**
 *
 *
 */
public abstract class Operation {

    private String name;
    private Operation previousOperation;
    private char operationCharacter;
    private int editDistance;
    
    
    /**
     * Answer an instance for the following arguments
     * @param aName String
     */
    public Operation(String aName,
                     char anOperationCharacter,
                     int anEditDistance) {
        
        super();
        this.setName(aName);
        this.setEditDistance(1);
        this.setOperationCharacter(anOperationCharacter);
        
    }
    

    /**
     * Answer my name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Set my name
     * @param name String
     */
    protected void setName(String name) {
        this.name = name;
    }
    

    /**
     * Answer my string representation
     * @return String
     */
    @Override
    public String toString() {
        
        StringBuilder   tempBuilder = new StringBuilder();
        
        tempBuilder.append("operation: ");
        tempBuilder.append(this.getName());
        
        return tempBuilder.toString();
        
    }
    
    /**
     * Answer whether or not I have aName
     * @param aName String
     * @return boolean
     */
    public boolean hasName(String aName) {
        
        return this.getName() != null && 
                    aName != null &&
                        this.getName().equals(aName);
        
    }


    /**
     * Answer my previousOperation
     * @return Operation
     */
    protected Operation getPreviousOperation() {
        return previousOperation;
    }


    /**
     * Set my previousOperation
     * @param previousOperation Operation
     */
    public void setPreviousOperation(Operation previousOperation) {
        this.previousOperation = previousOperation;
    }
    
    
    /**
     * Answer whether or not I am the first operation
     * @return boolean
     */
    public boolean isFirstOperation() {
        
        return this.getPreviousOperation() == null;
    }


    /**
     * Answer my operationCharacter
     * @return char
     */
    protected char getOperationCharacter() {
        return operationCharacter;
    }


    /**
     * Set my operationCharacter
     * @param operationCharacter char
     */
    protected void setOperationCharacter(char operationCharacter) {
        this.operationCharacter = operationCharacter;
    }


    /**
     * Answer my editDistance
     * @return int
     */
    protected int getEditDistance() {
        return editDistance;
    }


    /**
     * Set my editDistance
     * @param editDistance int
     */
    protected void setEditDistance(int editDistance) {
        this.editDistance = editDistance;
    }
    
    /**
     * Compute edit distance
     * @return int
     */
    public int computeEditDistance() {
        
        int tempResult;
        
        tempResult = this.getEditDistance();
        if (!this.isFirstOperation()) {
           
            tempResult += this.getPreviousOperation().getEditDistance();
        }
        
        return tempResult;
        
    }
    
    /**
     * Compute string
     * @param aBuilder StringBuilder
     */
    public void computeString(StringBuilder aBuilder) {
       
        if (!this.isFirstOperation()) {
            
            this.getPreviousOperation().computeString(aBuilder);
        }
        
        aBuilder.append(this.getOperationCharacter());
        
    }
    
    /**
     * Answer the minimum of myself and anOperation
     * @param anOperation Operation
     */
    public Operation minimumOf(Operation anOperation) {
        
        Operation tempResult;
        
        tempResult = (this.computeEditDistance() <= anOperation.computeEditDistance()) ? this : anOperation;
        
        return tempResult;
        
    }
    
    /**
     * Answer the end of the chain
     * @return Operation
     */
    protected Operation getEndOfChain() {
        
        Operation   tempResult;
        
        if (this.isFirstOperation()) {
            tempResult = this;
        }
        else {
            
            tempResult = this.getPreviousOperation().getEndOfChain();
        }
        
        return tempResult;
        
    }
    
    /**
     * Add anOperation to the end
     * anOperation Operation
     */
    public void addOperationToEnd(Operation anOperation) {
        
        Operation   tempResult;
        
        tempResult = this.getEndOfChain();
        tempResult.setPreviousOperation(anOperation);
        
    }
    
    
}
