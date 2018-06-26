package com.cracking.animalshelter;

import java.util.Date;

/**
 *
 *
 */
public class Animal {

    private Date arrivalTime;
    private AnimalType animalType;
    
    
    /**
     * Answer an instance for the following arguments
     * @param aType AnimalType
     */
    public Animal(AnimalType aType) {
        
        super();
        this.setArrivalTime(new Date());
        this.setAnimalType(aType);
        
    }
    
    /**
     * Answer an instance for the following arguments
     * @param aType AnimalType
     * @param anArrivalTime Date
     */
    public Animal(AnimalType aType,
                  Date anArrivalTime) {
        
        super();
        this.setArrivalTime(anArrivalTime);
        this.setAnimalType(aType);
        
    }
    
    

    
    /**
     * Answer whether or not I arrived before anotherTime
     * @param anotherTime Date
     * @return boolean
     */
    public boolean arrivedBefore(Date anotherTime) {
        
        return this.getArrivalTime().before(anotherTime);
        
    }

    /**
     * Answer my arrivalTime
     * @return Date
     */
    protected Date getArrivalTime() {
        return arrivalTime;
    }


    /**
     * Set my arrivalTime
     * @param arrivalTime Date
     */
    protected void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    
    /**
     * Answer whether or not I am a dog
     * @return boolean
     */
    public boolean isDog() {
        
        return this.getAnimalType() != null && 
                    this.getAnimalType().equals(AnimalType.DOG);
    }
    
    /**
     * Answer whether or not I am a cat
     * @return boolean
     */
    public boolean isCat() {
        
        return this.getAnimalType() != null && 
                    this.getAnimalType().equals(AnimalType.CAT);
    }
    
    

    /**
     * Answer my animalType
     * @return AnimalType
     */
    protected AnimalType getAnimalType() {
        return animalType;
    }


    /**
     * Set my animalType
     * @param animalType AnimalType
     */
    protected void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }
    
    

}
