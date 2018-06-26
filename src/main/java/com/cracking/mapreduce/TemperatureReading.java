package com.cracking.mapreduce;

import java.time.LocalDate;

/**
 *
 *
 */
public class TemperatureReading {

    private LocalDate date;
    private String city;
    private double temperature;
    
    /**
     * Answer an instance for the following arguments
     */
    public TemperatureReading() {
       super();
    }
    
    /**
     * Answer an instance for the following arguments
     * @param aDate LocalDate
     * @param aCity String
     * @param aTemperature double
     */
    public TemperatureReading(LocalDate aDate,
                              String aCity,
                              double aTemperature) {
        
       this();
       this.setDate(aDate);
       this.setCity(aCity);
       this.setTemperature(aTemperature);
       
    }
    
    

    /**
     * Answer my date
     * @return LocalDate
     */
    protected LocalDate getDate() {
        return date;
    }

    /**
     * Set my date
     * @param date LocalDate
     */
    protected void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Answer my city
     * @return String
     */
    protected String getCity() {
        return city;
    }

    /**
     * Set my city
     * @param city String
     */
    protected void setCity(String city) {
        this.city = city;
    }

    /**
     * Answer my temperature
     * @return double
     */
    protected double getTemperature() {
        return temperature;
    }

    /**
     * Set my temperature
     * @param temperature double
     */
    protected void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    
    

}
