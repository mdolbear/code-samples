package com.cracking.animalshelter;

import com.cracking.Queue;

/**
 *
 *
 */
public class AnimalShelter {

    private Queue<Animal> dogs;
    private Queue<Animal> cats;
    
    /**
     * Answer an instance for the following arguments
     */
    public AnimalShelter() {
        
        super();
        this.setCats(new Queue<Animal>());
        this.setDogs(new Queue<Animal>());
        
    }
    
    /**
     * Answer whether or not the shelter is empty
     * @return boolean
     */
    public boolean isShelterEmpty() {
        
        return this.getDogs().isEmpty() &&
                    this.getCats().isEmpty();
    }
    
    
    /**
     * Enqueue anAnimal
     * @param anAnimal Animal
     */
    public void enqueue(Animal anAnimal) {
        
        this.validateAnimalType(anAnimal);
        
        if (anAnimal.isCat()) {
            
            this.getCats().add(anAnimal);
        }
        else {
            
            this.getDogs().add(anAnimal);
            
        }
        
    }
    
    /**
     * Validate an animal type that we accept
     * @param anAnimal Animal
     */
    protected void validateAnimalType(Animal anAnimal) {
        
        if (!anAnimal.isCat() && !anAnimal.isDog()) {
            
            throw new IllegalArgumentException("We only accept cats or dogs");
        }
        
    }
    
    
    /**
     * Dequeue a dog that has been at the shelter the longest
     * @return Animal
     */
    public Animal dequeueDog() {
        
        return this.getDogs().remove();
    }
    
    /**
     * Dequeue a cat that has been at the shelter the longest
     * @return Animal
     */
    public Animal dequeueCat() {
        
        return this.getCats().remove();
    }
    
    /**
     * Dequeue the animal that has been at the shelter the longest
     * @return Animal
     */
    public Animal dequeueAny() {
        
        Animal  tempDog;
        Animal  tempCat;
        Animal  tempResult;
        
        this.validateShelterHasAnimalsToAdopt();
        
        tempDog = this.safelyPeekDog();
        tempCat = this.safelyPeekCat();
 
        
        tempResult = this.chooseLongestResident(tempDog, tempCat);
        
        return tempResult;
        
    }

    /**
     * Choose longest resident based on what type of animal is avaible.
     * @param aDog Animal
     * @param aCat Animal
     * @return Animal
     */
    public Animal chooseLongestResident(Animal aDog, 
                                        Animal aCat) {
        
        Animal tempResult;
        Animal tempLongestResident;
        
        if (aDog == null) {
            
            tempResult = this.dequeueCat();
        }
        else if (aCat == null) {
            
            tempResult = this.dequeueDog();
        }
        else {
            
            tempLongestResident = (aCat.arrivedBefore(aDog.getArrivalTime())) ? aCat : aDog;
            
            if (tempLongestResident.isCat()) {
                
                tempResult = this.dequeueCat();
            }
            else {
                
                tempResult = this.dequeueDog();
            }
            
        }
        
        return tempResult;
        
    }
    
    /**
     * Safely peek dog. Answer a dog or null
     * @return Animal
     */
    protected Animal safelyPeekDog() {
        
        Animal  tempDog = null;
        
        if (!this.getDogs().isEmpty()) {
            
            tempDog = this.getDogs().peek();
        }
        
        return tempDog;
        
    }
    
    /**
     * Safely peek cat. Answer a cat or null
     * @return Animal
     */
    protected Animal safelyPeekCat() {
        
        Animal  tempDog = null;
        
        if (!this.getCats().isEmpty()) {
            
            tempDog = this.getCats().peek();
        }
        
        return tempDog;
        
    }
    
    

    /**
     * Validate that the shelter has animals to adopt
     */
    protected void validateShelterHasAnimalsToAdopt() {
        
        if (this.isShelterEmpty()) {
            
            throw new IllegalStateException("We currently have now animals");
        }
        
    }
    
    
    
    /**
     * Add aDog to me
     * @param aDog Animal
     */
    protected void addDog(Animal aDog) {
        
        this.getDogs().add(aDog);
    }
    
    /**
     * Add aCat to me
     * @param aCat Animal
     */
    protected void addCat(Animal aCat) {
        
        this.getCats().add(aCat);
    }
    
    

    /**
     * Answer my dogs
     * @return Queue<Animal>
     */
    protected Queue<Animal> getDogs() {
        return dogs;
    }

    /**
     * Set my dogs
     * @param dogs Queue<Animal>
     */
    protected void setDogs(Queue<Animal> dogs) {
        this.dogs = dogs;
    }

    /**
     * Answer my cats
     * @return Queue<Animal>
     */
    protected Queue<Animal> getCats() {
        return cats;
    }

    /**
     * Set my cats
     * @param cats Queue<Animal>
     */
    protected void setCats(Queue<Animal> cats) {
        this.cats = cats;
    }
    
    

}
