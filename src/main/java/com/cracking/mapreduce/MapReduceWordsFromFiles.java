package com.cracking.mapreduce;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 *
 */
public class MapReduceWordsFromFiles {

    /**
     * Answer an instance for the following arguments
     */
    public MapReduceWordsFromFiles() {
       super();
    }
    
    /**
     * Perform map-reduce on aTemperatureReadings
     * @param aTemperatureReadings
     * @return Map<String, Integer>
     * 
     */
    public Map<String, Long> performMapReduceOn(String aSubdirectoryPath) 
                throws Exception {
        
        Stream<File>                tempStream;
        Stream<String>              tempLinesStream;
        Stream<String>              tempWordsStream;
        File                        tempRootFile;
        Map<String, Long>           tempMap = new HashMap<String, Long>();
        
        tempRootFile = new File(aSubdirectoryPath);

        if (tempRootFile.exists()) {
            
            tempStream = Arrays.stream(tempRootFile.listFiles());
            tempLinesStream = tempStream.flatMap(this.createFilesToLinesFunction());
            tempWordsStream = tempLinesStream.flatMap(this.createLinesToWordsFunction());
            
            tempMap = tempWordsStream.collect(Collectors.groupingBy(w->w.toLowerCase(),
                                                                    Collectors.counting()));
            
        }
        
        return tempMap;
        
    }

    /**
     * Answer a function to go from files to lines of the file
     * @return
     */
    protected Function<? super File, ? extends Stream<? extends String>> createFilesToLinesFunction() {
        
        return f -> {
            
                        Stream<String> tempStream = null;
                        
                        try {
                            tempStream = Files.lines(f.toPath());
                        }
                        catch(IOException e) {
                            
                            e.printStackTrace();
                        }
                        
                        return tempStream;
                    
        };
        
    }
    
    
    /**
     * Answer a function to go from files to lines of the file
     * @return
     */
    protected Function<? super String, ? extends Stream<? extends String>> createLinesToWordsFunction() {
        
        return line -> Arrays.stream(line.split("\\W+"));

        
    }
    
    

}
