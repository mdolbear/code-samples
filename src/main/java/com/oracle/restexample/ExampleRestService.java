/**
 * 
 */
package com.oracle.restexample;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.samples.Record;
import com.oracle.samples.RecordList;

/**
 * @author mdolbear
 *
 */
@RestController
public class ExampleRestService {
    
    @Autowired
    private ExampleApplicationProperties properties;
    
    //Constants
    private static final Logger logger = LoggerFactory.getLogger(ExampleRestService.class);
    
    
    
    /**
     * Answer my logger
     * @return Logger
     */
    protected static Logger getLogger() {
        
        return logger;
    }


    /**
     * Answer an instance for the following arguments
     */
    public ExampleRestService() {
        super();
    }

    
    /**
     * Answer my records -http://localhost:9998/records
     * @return String
     */
    @GetMapping("/records")
    public String getAllRecords() {
        
        RecordList tempResult;
        
        tempResult = this.produceRecordList();
        this.accessProperties();
        return Arrays.asList(tempResult.getRecords()).toString();
        
    }

    /**
     * Access properties
     */
    protected void accessProperties() {
        
        getLogger().debug("State of properties injection: " + (this.getProperties() != null));
        
    }
    
    
    /**
     * Answer my records by the following parameters-- http://localhost:9998/records/byBounds?lowerBound=0&upperBound=10&inclusive=false
     * @param aLowerbound Integer
     * @param anUpperBound Integer
     * @param anInclusive Boolean
     * @return String
     */
    @GetMapping("/records/byBounds")
    public List<Record> getRecordsBy(@RequestParam("lowerBound") Integer aLowerBound, 
                                     @RequestParam("upperBound") Integer anUpperBound, 
                                     @RequestParam("inclusive") Boolean anInclusive) 
                                             throws Exception {
        
        RecordList      tempResult;
        List<Record>    tempRecords;
        
        this.performByBoundsValidation(aLowerBound, anUpperBound, anInclusive);
        
        //Produce result
        tempResult = this.produceRecordList();
        tempRecords = tempResult.getRecordsInBoundaries(aLowerBound.intValue(), 
                                                        anUpperBound.intValue(), 
                                                        anInclusive.booleanValue());

        return tempRecords;
        
    }


    /**
     * Perform validation for "byBounds" record request
     * @param aLowerBound
     * @param anUpperBound
     * @param anInclusive
     */
    protected void performByBoundsValidation(Integer aLowerBound,
                                             Integer anUpperBound, 
                                             Boolean anInclusive) throws Exception {
        
        this.validateNotNull("lowerBound" , aLowerBound);
        this.validateNotNull("upperBound" , anUpperBound);
        this.validateNotNull("inclusive" , anInclusive);
        
    }
    
    
    /**
     * Produce record list
     * @return RecordList
     */
    protected RecordList produceRecordList() {
        RecordList      tempResult;
        
        
        tempResult = this.createRecordList();
        tempResult = tempResult.mergeSort();
        return tempResult;
    }
    
    /**
     * Create record list
     * @return RecordList
     */
    protected RecordList createRecordList() {
        
        int[]       tempValues = {20, 18, 102, 30, 40, 10, 55, 28, 6, 3, 2, 8, 1, 15, 17, 13, 27};
        RecordList  tempResult;
        
        tempResult = RecordList.createFrom(tempValues);
        
        return tempResult;
        
    }
    
    /**
     * Validate parameter not null
     * @param aValue Object
     */
    protected void validateNotNull(String aVariableName,
                                   Object anObject) 
                            throws Exception {
        
        
    }


    /**
     * Answer my properties
     * @return ExampleApplicationProperties
     */
    protected ExampleApplicationProperties getProperties() {
        return properties;
    }


    /**
     * Set my properties
     * @param properties ExampleApplicationProperties
     */
    protected void setProperties(ExampleApplicationProperties properties) {
        this.properties = properties;
    }
    
    
}
