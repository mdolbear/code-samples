package com.oracle.samples;

/**
 *
 *
 */
public class Record {

    private int key;
    
    /**
     * Answer an instance for the following arguments
     */
    public Record() {
        super();
    }
    
    /**
     * Answer an instance of me for the following arguments
     * @param aValue int
     */
    public Record(int aValue) {
        
        this();
        this.setKey(aValue);
    }

    /**
     * Answer my key
     * @return int
     */
    public int getKey() {
        return key;
    }
    
    /**
     * Answer the aDigitNumber of my key
     * @param aDigitNumber int
     * @return int
     */
    public int getDigitOfKey(int aDigitNumber) {
        
        int tempResult;
        
        tempResult = this.getKey() / (int)Math.pow(10.0, aDigitNumber);
        tempResult = tempResult % 10;
        
        return tempResult;
        
    }

    /**
     * Set my key
     * @param key int
     */
    public void setKey(int key) {
        this.key = key;
    }
    
        
    /**
     * Answer my string representation
     * @return String
     */
    public String toString() {
        
        StringBuilder   tempBuilder = new StringBuilder();
        
        tempBuilder.append(" key: " );
        tempBuilder.append(this.getKey());

        
        return tempBuilder.toString();
        
    }
    
    /**
     * Interchange keys
     * @param anotherRecord
     */
    public void interchangeKeysAndData(Record anotherRecord) {
        
        int tempKey;
        
        tempKey = this.getKey();
        this.setKey(anotherRecord.getKey());
        anotherRecord.setKey(tempKey);
        
    }
    
}
