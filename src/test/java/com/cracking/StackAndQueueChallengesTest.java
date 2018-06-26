package com.cracking;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.cracking.animalshelter.Animal;
import com.cracking.animalshelter.AnimalShelter;
import com.cracking.animalshelter.AnimalType;

/**
 *
 *
 */
public class StackAndQueueChallengesTest {

    
    //Constants
    protected static final long DAY_IN_MILLISECONDS = 24*60*60*1000l;
    
    
    /**
     * Answer an instance for the following arguments
     */
    public StackAndQueueChallengesTest() {
        super();
    }
    
    /**
     * HashTable test
     */
    @Test
    public void hashTableTest() {
        
        HashTable<String, String> tempTable = new HashTable<String, String>(20);
        String                    tempValue;
        
        tempTable.put("someValue", "a");
        assertTrue("Key not in map", tempTable.hasKey("someValue"));
        assertTrue("Key not in map on get", tempTable.get("someValue") != null);
        assertTrue("Key not in map on size", tempTable.getKeys().size() == 1);
        assertTrue("Key not in map on size", tempTable.getValues().size() == 1);
        assertTrue("Nubmer of elements not correct", tempTable.getNumberOfElements() == 1);
        
        tempValue = tempTable.remove("someValue");
        assertTrue("Value not correct", tempValue.equals("a"));
        assertTrue("Key not removed", tempTable.get("someValue") == null);
        
    }
    
    /**
     * Queue test
     */
    @Test
    public void basicQueueTest() {
        
        Queue<String> tempQueue = new Queue<String>();
        String        tempCurrentElement;
        
        assertTrue("Element not in queue", tempQueue.isEmpty());
        tempQueue.add("a");
        assertTrue("Element not in queue", !tempQueue.isEmpty());
        tempQueue.add("b");
        assertTrue("Number of elements not correct", tempQueue.size() == 2);
        
        tempCurrentElement = tempQueue.peek();
        assertTrue("Element not correct", tempCurrentElement.equals("a"));

        tempCurrentElement = tempQueue.remove();
        assertTrue("Element not correct", tempCurrentElement.equals("a") &&
                                            tempQueue.size() == 1);
        
        tempCurrentElement = tempQueue.remove();
        assertTrue("Element not correct", tempCurrentElement.equals("b") &&
                                            tempQueue.size() == 0 &&
                                                tempQueue.isEmpty());
        
    }
    
    /**
     * Stack test
     * 
     */
    @Test
    public void basicStackTest() {
        
        Stack<String>   tempStack = new Stack<String>();
        String          tempCurrentElement;
        
        assertTrue("Element not in stack", tempStack.isEmpty());
        tempStack.push("a");
        assertTrue("Element not in stack", !tempStack.isEmpty());
        tempStack.push("b");
        assertTrue("Number of elements not correct", tempStack.size() == 2);
        
        tempCurrentElement = tempStack.peek();
        assertTrue("Element not correct", tempCurrentElement.equals("b"));

        tempCurrentElement = tempStack.pop();
        assertTrue("Element not correct", tempCurrentElement.equals("b") &&
                                            tempStack.size() == 1);
        
        tempCurrentElement = tempStack.pop();
        assertTrue("Element not correct", tempCurrentElement.equals("a") &&
                                            tempStack.size() == 0 &&
                                                tempStack.isEmpty());
        
    }
    
    /**
     * Test minimum stack
     * 
     */
    @Test
    public void minimumStackTest() {
        
        MinStack<Integer>   tempStack = new MinStack<Integer>(this.createIntegerComparator());
        Integer             tempCurrentElement;
        
        assertTrue("Element not in stack", tempStack.isEmpty());
        tempStack.push(new Integer(0));
        assertTrue("Element not in stack", !tempStack.isEmpty());
        tempStack.push(new Integer(1));
        assertTrue("Number of elements not correct", tempStack.size() == 2 &&
                                                        tempStack.getMinimum().intValue() == 0);
        
        tempCurrentElement = tempStack.peek();
        assertTrue("Element not correct", tempCurrentElement.equals(new Integer(1)));

        tempCurrentElement = tempStack.pop();
        assertTrue("Element not correct", tempCurrentElement.equals(new Integer(1)) &&
                                            tempStack.size() == 1 &&
                                                tempStack.getMinimum().intValue() == 0);
        
        tempStack.push(new Integer(-1));
        assertTrue("Number of elements not correct", tempStack.size() == 2 &&
                tempStack.getMinimum().intValue() == -1);
        
        tempCurrentElement = tempStack.pop();
        assertTrue("Element not correct", tempCurrentElement.equals(new Integer(-1)) &&
                                            tempStack.size() == 1 &&
                                                    tempStack.getMinimum().intValue() == 0);
        
        tempCurrentElement = tempStack.pop();
        assertTrue("Element not correct", tempCurrentElement.equals(new Integer(0)) &&
                                                    tempStack.size() == 0 );
        
        
    }
    
    /**
     * Answer an integer comparator
     * @return Comparator<Integer>
     */
    public Comparator<Integer> createIntegerComparator() {
        
        return (Integer i1, Integer i2) -> ( i1.intValue() - i2.intValue());
    }
    
    /**
     * Queue test. Note that this only currently works for queues that have unique values...If I have duplicate elements, this will blow up
     */
    @Test
    public void basicQueueOfStacksTest() {
        
        QueueOfStacks<String> tempQueue = new QueueOfStacks<String>();
        String                tempCurrentElement;
        
        assertTrue("Element not in queue", tempQueue.isEmpty());
        tempQueue.add("a");
        assertTrue("Element not in queue", !tempQueue.isEmpty());
        tempQueue.add("b");
        assertTrue("Number of elements not correct", tempQueue.size() == 2);
        
        tempCurrentElement = tempQueue.peek();
        assertTrue("Element not correct", tempCurrentElement.equals("a"));

        tempCurrentElement = tempQueue.remove();
        assertTrue("Element not correct", tempCurrentElement.equals("a") &&
                                            tempQueue.size() == 1);
        
        tempQueue.add("c");
        assertTrue("Number of elements not correct", tempQueue.size() == 2);
        
        tempCurrentElement = tempQueue.remove();
        assertTrue("Element not correct", tempCurrentElement.equals("b") &&
                                            tempQueue.size() == 1 &&
                                                !tempQueue.isEmpty());
        
        tempCurrentElement = tempQueue.remove();
        assertTrue("Element not correct", tempCurrentElement.equals("c") &&
                                            tempQueue.size() == 0 &&
                                                tempQueue.isEmpty());
        
    }
    
    /**
     * Stack test
     * 
     */
    @Test
    public void basicSortedStackTest() {
        
        SortedStack<Integer>   tempStack = new SortedStack<Integer>(this.createIntegerComparator());
        Integer                tempCurrentElement;
        
        assertTrue("Element not in stack", tempStack.isEmpty());
        tempStack.push(new Integer(1));
        assertTrue("Element not in stack", !tempStack.isEmpty());
        tempStack.push(new Integer(4));
        assertTrue("Number of elements not correct", tempStack.size() == 2);
        
        tempStack.push(new Integer(7));
        tempStack.push(new Integer(0));
        tempStack.push(new Integer(20));
        assertTrue("Number of elements not correct", tempStack.size() == 5);
        
        tempCurrentElement = tempStack.peek();
        assertTrue("Element not correct", tempCurrentElement.equals(new Integer(0)));

        tempCurrentElement = tempStack.pop();
        assertTrue("Element not correct", tempCurrentElement.equals(new Integer(0)));
        
        tempCurrentElement = tempStack.pop();
        assertTrue("Element not correct", tempCurrentElement.equals(new Integer(1)));
        
        System.out.println("Remaing stack: " + tempStack.toString());
        
    }
    
    /**
     * Stack test
     * 
     */
    @Test
    public void basicSetOfStackTest() {
        
        SetOfStacks<String>   tempStack = new SetOfStacks<String>(2);
        String          tempCurrentElement;
        
        assertTrue("Element should not be in stack", tempStack.isEmpty());
        
        tempStack.push("a");        
        assertTrue("Element not in stack", !tempStack.isEmpty());
        assertTrue("Number of elements not correct", tempStack.size() == 1);
        
        tempStack.push("b");
        assertTrue("Number of elements not correct", tempStack.size() == 2);
        
        tempStack.push("c");
        assertTrue("Number of elements not correct", tempStack.size() == 3);
        
        tempStack.push("d");
        assertTrue("Number of elements not correct", tempStack.size() == 4);
        
        tempStack.push("e");
        assertTrue("Number of elements not correct", tempStack.size() == 5);
        
        tempStack.push("f");
        assertTrue("Number of elements not correct", tempStack.size() == 6);
        
        
        tempCurrentElement = tempStack.peek();
        assertTrue("Element not correct", tempCurrentElement.equals("f"));

        tempCurrentElement = tempStack.pop();
        assertTrue("Element not correct", tempCurrentElement.equals("f") &&
                                            tempStack.size() == 5);
        
        tempCurrentElement = tempStack.pop();
        assertTrue("Element not correct", tempCurrentElement.equals("e") &&
                                                            tempStack.size() == 4);
        
        tempCurrentElement = tempStack.pop();
        assertTrue("Element not correct", tempCurrentElement.equals("d") &&
                                                    tempStack.size() == 3);
        
        tempCurrentElement = tempStack.pop();
        assertTrue("Element not correct", tempCurrentElement.equals("c") &&
                                                    tempStack.size() == 2);
        
        tempCurrentElement = tempStack.pop();
        assertTrue("Element not correct", tempCurrentElement.equals("b") &&
                                                    tempStack.size() == 1);
        
        tempCurrentElement = tempStack.pop();
        assertTrue("Element not correct", tempCurrentElement.equals("a") &&
                                                    tempStack.size() == 0);
        assertTrue("Element should not be in stack", tempStack.isEmpty());
        
        tempStack.push("a");        
        assertTrue("Element not in stack", !tempStack.isEmpty());
        assertTrue("Number of elements not correct", tempStack.size() == 1);
        
        tempStack.push("b");
        assertTrue("Number of elements not correct", tempStack.size() == 2);
        
        tempStack.push("c");
        assertTrue("Number of elements not correct", tempStack.size() == 3);
        
        tempStack.push("d");
        assertTrue("Number of elements not correct", tempStack.size() == 4);
        
    }
    
    
    /**
     * Animal shelter test
     */
    @Test
    public void animalShelterTest() {
        
        AnimalShelter   tempShelter = new AnimalShelter();
        Animal          tempCurrentAnimal;
        
        //Add all of the animals
        for (Animal anAnimal: this.createCatsAndDogs()) {
            
            tempShelter.enqueue(anAnimal);
        }
        
        tempCurrentAnimal = tempShelter.dequeueAny();
        assertTrue("Should be a dog", tempCurrentAnimal.isDog());
        
        tempCurrentAnimal = tempShelter.dequeueAny();
        assertTrue("Should be a cat", tempCurrentAnimal.isCat());
        
        tempCurrentAnimal = tempShelter.dequeueCat();
        assertTrue("Should be a cat", tempCurrentAnimal.isCat());
        
        tempCurrentAnimal = tempShelter.dequeueCat();
        assertTrue("Should be a cat", tempCurrentAnimal.isCat());
        
        tempCurrentAnimal = tempShelter.dequeueAny();
        assertTrue("Should be a cat", tempCurrentAnimal.isCat());
        
        tempCurrentAnimal = tempShelter.dequeueAny();
        assertTrue("Should be a cat", tempCurrentAnimal.isCat());
        
        tempCurrentAnimal = tempShelter.dequeueAny();
        assertTrue("Should be a dog", tempCurrentAnimal.isDog());
        assertTrue("Shelter should be empty", tempShelter.isShelterEmpty());
        
        
        
    }
    
    /**
     * Create a list of dogs and cats
     * @return List<Animal>
     */
    protected List<Animal> createCatsAndDogs() {
        
        List<Animal>    tempAnimals = new ArrayList<Animal>();
        Date            tempNow = new Date();
        
        tempAnimals.add(new Animal(AnimalType.DOG, 
                                   new Date(tempNow.getTime() - 10*DAY_IN_MILLISECONDS)));
        
        tempAnimals.add(new Animal(AnimalType.CAT, 
                                   new Date(tempNow.getTime() - 9*DAY_IN_MILLISECONDS)));
        
        tempAnimals.add(new Animal(AnimalType.CAT, 
                                   new Date(tempNow.getTime() - 8*DAY_IN_MILLISECONDS)));
        
        tempAnimals.add(new Animal(AnimalType.CAT, 
                                   new Date(tempNow.getTime() - 7*DAY_IN_MILLISECONDS)));
        
        tempAnimals.add(new Animal(AnimalType.CAT, 
                                   new Date(tempNow.getTime() - 6*DAY_IN_MILLISECONDS)));
        
        tempAnimals.add(new Animal(AnimalType.CAT, 
                                   new Date(tempNow.getTime() - 3*DAY_IN_MILLISECONDS)));
        
        tempAnimals.add(new Animal(AnimalType.DOG, 
                                   tempNow));
        
        return tempAnimals;
    }

}
