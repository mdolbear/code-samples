package com.cracking.mapreduce;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 *
 */
public class MapReduceCityYear {

    /**
     * Answer an instance for the following arguments
     */
    public MapReduceCityYear() {
       super();
    }
    
    
    
    /**
     * Perform map-reduce on aTemperatureReadings
     * @param aTemperatureReadings
     * @return  Map<String, Double>
     * 
     */
    public Map<String, Double> performMapReduceOn(List<TemperatureReading> aReadings) {
        
        Map<String,Double>                   tempResults;
        
        tempResults = aReadings.stream()
                              .collect(Collectors.groupingBy(this.createGroupingMainFunction(),
                                                             Collectors.averagingDouble(TemperatureReading::getTemperature)));
        
        return tempResults;
        
    }
    


    /**
     * Create grouping by main function
     * @return  Function<? super CityYearAverage, ? extends String>
     */
    protected Function<? super TemperatureReading, ? extends String> createGroupingMainFunction() {
        
        return (avg)->avg.getCity() + String.valueOf(avg.getDate().getYear());
    }

}
