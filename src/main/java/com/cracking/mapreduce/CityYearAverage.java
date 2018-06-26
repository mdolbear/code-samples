package com.cracking.mapreduce;

/**
 *
 *
 */
public class CityYearAverage {

    private String city;
    private int year;
    private double averageTemperature;
    private int numberOfDataPoints;
    
    
    /**
     * Answer an instance for the following arguments
     */
    public CityYearAverage() {
        super();
    }
    
    /**
     * Answer an instance for the following arguments
     * @param aCity String
     * @param aYear int
     * @param aNubmerOfDataPoints int
     * @param anAverage double
     */
    public CityYearAverage(String aCity,
                           int aYear,
                           int aNumberOfDataPoints,
                           double anAverage) {
        
        super();
        this.setCity(aCity);
        this.setYear(aYear);
        this.setNumberOfDataPoints(aNumberOfDataPoints);
        this.setAverageTemperature(anAverage);
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
     * Answer my year
     * @return int
     */
    protected int getYear() {
        return year;
    }


    /**
     * Set my year
     * @param year int
     */
    protected void setYear(int year) {
        this.year = year;
    }


    /**
     * Answer my averageTemperature
     * @return double
     */
    protected double getAverageTemperature() {
        return averageTemperature;
    }


    /**
     * Set my averageTemperature
     * @param averageTemperature double
     */
    protected void setAverageTemperature(double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }


    /**
     * Answer my numberOfDatePoints
     * @return int
     */
    protected int getNumberOfDataPoints() {
        return numberOfDataPoints;
    }


    /**
     * Set my numberOfDatePoints
     * @param numberOfDatePoints int
     */
    protected void setNumberOfDataPoints(int numberOfDataPoints) {
        this.numberOfDataPoints = numberOfDataPoints;
    }
    
    
    /**
     * Compute average for city and year with anotherCityYearAverage
     * @param anotherCityYearAverage CityYearAverage
     */
    public CityYearAverage computeAverage(CityYearAverage anotherCityYearAverage) {
        
        CityYearAverage tempValue = null;
        int             tempNumberOfDataPoints;
        double          tempAverage;
        
        if (this.getCity().equals(anotherCityYearAverage.getCity()) &&
                this.getYear() == anotherCityYearAverage.getYear()) {
                    
            tempNumberOfDataPoints = this.getNumberOfDataPoints() + anotherCityYearAverage.getNumberOfDataPoints();
            tempAverage = ((this.getAverageTemperature() * this.getNumberOfDataPoints()) 
                                + (anotherCityYearAverage.getAverageTemperature() * anotherCityYearAverage.getNumberOfDataPoints()))/tempNumberOfDataPoints;
            
            tempValue =    new CityYearAverage(this.getCity(),
                                               this.getYear(),
                                               tempNumberOfDataPoints,
                                               tempAverage);
        }
        
        return tempValue;
                
    }
    
    
}
