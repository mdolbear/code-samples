package com.cracking.mapreduce;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
/**
 *
 *
 */
public class MapReduceTests {

    /**
     * Answer an instance for the following arguments
     */
    public MapReduceTests() {
        super();
    }
    
    
    /**
     * CityYearAverageMap reduce
     */
    @Test
    public void cityYearAverageMapReduceTest() {
        
        MapReduceCityYear   tempReduce = new MapReduceCityYear();
        Map<String, Double> tempResults;
        
        tempResults = tempReduce.performMapReduceOn(this.createTemperatureReadings());
        System.out.println("Temperature readings: " + tempResults.toString());
        
    }
    
    
    
    /**
     * Answer a list of temperature readings
     */
    protected List<TemperatureReading> createTemperatureReadings() {
        
       List<TemperatureReading> tempReadings = new ArrayList<TemperatureReading>();
       
       tempReadings.add(new TemperatureReading(LocalDate.of(2014, 3, 25),
                                               "Boston",
                                               50.0));
       
       tempReadings.add(new TemperatureReading(LocalDate.of(2014, 6, 25),
                                               "Boston",
                                               100.0));
       
       tempReadings.add(new TemperatureReading(LocalDate.of(2014, 3, 25),
                                               "New Orleans",
                                               80.0));

       tempReadings.add(new TemperatureReading(LocalDate.of(2014, 6, 25),
                                               "New Orleans",
                                               90.0));
       
       tempReadings.add(new TemperatureReading(LocalDate.of(2016, 3, 25),
                                               "Boston",
                                               80.0));

       tempReadings.add(new TemperatureReading(LocalDate.of(2016, 6, 25),
                                               "Boston",
                                               90.0));
        
        tempReadings.add(new TemperatureReading(LocalDate.of(2016, 3, 25),
                                                "New Orleans",
                                                90.0));
        
        tempReadings.add(new TemperatureReading(LocalDate.of(2016, 6, 25),
                                                "New Orleans",
                                                110.0));
        
        return tempReadings;
       
    }
    
    /**
     * words from files map reduce
     */
    @Test
    public void wordsFromFilesMapReduceTest() throws Exception {
        
        MapReduceWordsFromFiles tempWordsFromFiles;
        Map<String, Long>       tempWordCounts;
        
        tempWordsFromFiles = new MapReduceWordsFromFiles();
        tempWordCounts = 
                tempWordsFromFiles.performMapReduceOn("D:\\code\\Projects\\sample-project\\src\\main\\resources\\streamFiles");
        System.out.println("Word counts: " + tempWordCounts.toString());
        
    }

}
