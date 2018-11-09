package com.oracle.samples;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.oracle.tictactoe.gamemodel.Player;
import com.oracle.tictactoe.gamemodel.TicTacToeGame;
import org.junit.Test;

import java.util.*;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;


/**
 *
 *
 */
public class CodeExperimentTest {

    /**
     * Answer an instance for the following arguments
     */
    public CodeExperimentTest() {
        super();
    }
    
    /**
     * Test of superclass with generic
     */
    @Test
    public void testGenerics1() {
        
        List<? extends Number> tempNumberList;
        List<? extends Number> tempNewNumberList;
        List<Integer>          tempIntegerList;
        
        tempIntegerList = new ArrayList<Integer>();
        
        tempIntegerList.add(new Integer(1));
        
        tempNumberList = tempIntegerList;
        
        System.out.println("superclass List<? extends Number>: " + tempNumberList);
        tempIntegerList.add(new Integer(2));
        
        tempIntegerList.add(new Integer(10));
        tempIntegerList.add(new Integer(20));
        System.out.println("superclass List<? extends Number>: " + tempNumberList);
        
        tempNewNumberList = this.handleNumbers(tempNumberList, new Integer(2));
        System.out.println("superclass List<? extends Number> after filter: " + tempNewNumberList);
        
        tempNewNumberList = this.handleNumbersNegated(tempNumberList, new Integer(2));
        System.out.println("superclass List<? extends Number> after filter negated: " + tempNewNumberList);
        
    }
    
    /**
     * Handle numbers
     * @param aNumbers List<? extends Number>
     */
    protected List<? extends Number> handleNumbers(List<?extends Number> aNumbers,
                                                   Number anUpperBound) {
        
        Predicate<Number>       tempFilter = (Number n) -> (n.intValue() < anUpperBound.intValue());
        List<? extends Number>  tempResult;
        
        tempResult = aNumbers.stream()
                             .filter(tempFilter)
                             .collect(Collectors.toList());
        
        return tempResult;
        
    }
    
    /**
     * Handle numbers
     * @param aNumbers List<? extends Number>
     */
    protected List<? extends Number> handleNumbersNegated(List<?extends Number> aNumbers,
                                                          Number anUpperBound) {
        
        Predicate<Number>       tempFilter = (Number n) -> (n.intValue() < anUpperBound.intValue());
        List<? extends Number>  tempResult;
        
        tempFilter = tempFilter.negate();
        tempResult = aNumbers.stream()
                             .filter(tempFilter)
                             .collect(Collectors.toList());
        
        return tempResult;
        
    }
    
    /**
     * Test of superclass with generic
     */
    @Test
    public void testGenerics2() {
        
        List<? super Integer> tempNumberList;
        List<Number>          tempIntegerList;
        
        tempIntegerList = new ArrayList<Number>();
        
        tempIntegerList.add(new Integer(1));
        
        tempNumberList = tempIntegerList;
        
        System.out.println("superclass List<? super Integer>: " + tempNumberList);
    }
    
    /**
     * Two d array initialization
     */
    @Test
    public void twoDArrayInitTest() {
        
        int[][] tempTwoDArray;
        
        tempTwoDArray = new int[5][5];
        
        for (int i = 0; i < 5; i ++) {
            
            for (int j = 0; j < 5; j++) {
                
                System.out.println("[" + i + "]" + "[" + j + "]=" + tempTwoDArray[i][j]);
            }
        }
        
    }
    
    /**
     * Find all maximums
     * 
     */
    @Test
    public void findMaximumDiffsTest() {
        
        int[]                   tempStockPrices = {100, 180, 260, 310, 40, 535, 695};
        int                     i;
        int                     j;
        List<BuySellInterval>   tempIntervals = new ArrayList<BuySellInterval>();
        int                     tempNumberOfIntervals;
        List<BuySellInterval>   tempCurrentPaths;
        int                     tempSum;
        int                     tempCurrentMax;
        
        for (i = 0; i < tempStockPrices.length; i++ ) {
            
            for (j = i+1; j < tempStockPrices.length; j++) {
                
                tempIntervals.add( new BuySellInterval(i, j, tempStockPrices[j] - tempStockPrices[i]));

            }
        }
        
        tempCurrentMax = 0;        
        while (!tempIntervals.isEmpty()) {
            
            tempCurrentPaths = new ArrayList<BuySellInterval>();
            tempSum = 0;
            tempNumberOfIntervals = tempIntervals.size();
            for (int k = 0; k < tempNumberOfIntervals; k++) {
                
                if (!tempIntervals.get(k).overlapsWithAny(tempCurrentPaths)) {
                    
                    tempCurrentPaths.add(tempIntervals.get(k));
                }
            }
            

            for (BuySellInterval anInterval: tempCurrentPaths) {
                
                tempSum += anInterval.getProfit();
            }

            if (tempSum > tempCurrentMax) {
                
                tempCurrentMax = tempSum;
                System.out.println();
                System.out.println("Path through array: " + tempCurrentPaths.toString());
                System.out.println("Current Max Profit: " + tempSum);
                System.out.println();
                
                
            }
            
            tempIntervals.removeAll(tempCurrentPaths);
            
        }
        
    }
    
    /**
     * Product of the digits == n test
     */
    @Test
    public void productOfDigitsTest() {
        
        int   tempDigits;
        int   n = 21;
        int   tempProduct;
        
        tempDigits = 1;
        tempProduct = this.computeProduct(tempDigits);
        
        while (n != tempProduct) {
            
            tempDigits++;
            tempProduct = this.computeProduct(tempDigits);
        }
        
        System.out.println("Answer: " + tempDigits + " product:" + tempProduct);
        
        
    }
    
    /**
     * Answer the product of the digits
     * @param aDigits int
     */
    protected int computeProduct(int aDigits) {
        
        int tempProduct = 1;
        int tempCurrentDigit;
        
        while (aDigits > 0) {
            
            tempCurrentDigit = aDigits % 10;
            aDigits = aDigits / 10;
            
            tempProduct = tempProduct * tempCurrentDigit;
        }
        
        return tempProduct;
        
    }
    
    
    /**
     * Find number in infinite sorted array
     */
    @Test
    public void testFindFirstIndexOfOne() {
        
        int[]   tempArray = {0,0,0,0,1};
        int     tempIndex = -1;
        int     i = 0;
        
        
        while (tempIndex == -1) {
            
            if (tempArray[i] == 1) {
                
                tempIndex = i;
            }
            
            i++;
            
        }
        
        System.out.println("Index found: " + tempIndex);
        
    }
    
    
    /**
     * Moving average test
     */
    @Test
    public void movingAverageTest() {
        
        Double          tempResult;
        MovingAverage   tempAverage = new MovingAverage(3);
        
        tempResult = tempAverage.computeMovingAverage(new Double(30.5));
        System.out.println("Current Average: " + tempResult);
        
        tempResult = tempAverage.computeMovingAverage(new Double(40.5));
        System.out.println("Current Average: " + tempResult);
        
        tempResult = tempAverage.computeMovingAverage(new Double(40.5));
        System.out.println("Current Average: " + tempResult);
        
        tempResult = tempAverage.computeMovingAverage(new Double(30.5));
        System.out.println("Current Average: " + tempResult);  
        
        tempResult = tempAverage.computeMovingAverage(new Double(40.5));
        System.out.println("Current Average: " + tempResult);
        
        tempResult = tempAverage.computeMovingAverage(new Double(40.5));
        System.out.println("Current Average: " + tempResult);
        
        tempResult = tempAverage.computeMovingAverage(new Double(40.5));
        System.out.println("Current Average: " + tempResult);
        
    }
    
    /**
     * Moving average test
     */
    @Test
    public void movingAverageTestWithQueue() {
        
        Double                   tempResult;
        MovingAverageWithQueue   tempAverage = new MovingAverageWithQueue(3);
        
        tempResult = tempAverage.computeMovingAverage(new Double(30.5));
        System.out.println("Current Average: " + tempResult);
        
        tempResult = tempAverage.computeMovingAverage(new Double(40.5));
        System.out.println("Current Average: " + tempResult);
        
        tempResult = tempAverage.computeMovingAverage(new Double(40.5));
        System.out.println("Current Average: " + tempResult);
        
        tempResult = tempAverage.computeMovingAverage(new Double(30.5));
        System.out.println("Current Average: " + tempResult);  
        
        tempResult = tempAverage.computeMovingAverage(new Double(40.5));
        System.out.println("Current Average: " + tempResult);
        
        tempResult = tempAverage.computeMovingAverage(new Double(40.5));
        System.out.println("Current Average: " + tempResult);
        
        tempResult = tempAverage.computeMovingAverage(new Double(40.5));
        System.out.println("Current Average: " + tempResult);
        
    }
    
    
    
    
    
    /**
     * Fibonnaci recursive
     */
    @Test
    public void fibonacciTest() {
        
        int tempResult;
        FibonacciSeries tempSeries = new FibonacciSeries();
        
        tempResult = tempSeries.compute(0);
        System.out.println("Fibonacci[0] = " + tempResult);
        
        tempResult = tempSeries.compute(1);
        System.out.println("Fibonacci[1] = " + tempResult);
        
        tempResult = tempSeries.compute(2);
        System.out.println("Fibonacci[2] = " + tempResult);
        
        for (int i = 3; i < 11; i++) {
            
            tempResult = tempSeries.compute(i);
            System.out.println("Fibonacci[" + i + "] = " + tempResult);
            
        }
        
        assertTrue("Compare computations 0 failed", tempSeries.compute(0) == tempSeries.computeIteratively(0));
        
        assertTrue("Compare computations 1 failed", tempSeries.compute(1) == tempSeries.computeIteratively(1));
        
        assertTrue("Compare computations 2 failed", tempSeries.compute(2) == tempSeries.computeIteratively(2));
        
        for (int i = 3; i < 11; i++) {
            
            assertTrue("Compare computations 1 failed", 
                    tempSeries.compute(i) == tempSeries.computeIteratively(i));
            
        }
        
    }
    
    /**
     * Optimize position of pipeline
     */
    @Test
    public void optimizePipelinePositionTest() {
        
        Pipeline    tempLine = new Pipeline();
        int         tempOptimizedPipelineYPosition;
        
        this.initializeWellsFor(tempLine);
        tempOptimizedPipelineYPosition = tempLine.optimizePipelinePosition();
        assertTrue("Pipeline y-position not returned", tempOptimizedPipelineYPosition != 0);
        
    }

    
    
    /**
     * Initialize wells for aPipeline
     * @param aPipeline Pipeline
     */
    protected void initializeWellsFor(Pipeline aPipeline) {
        
        OilWell tempWell;
        Random  tempRandom = new Random();
        int     tempNextY;
        
        for (int i = 0; i < 1000; i++) {
            
            tempNextY = tempRandom.nextInt(100);
            tempWell = new OilWell(i, tempNextY);
            aPipeline.addWell(tempWell);
        }
        
    }
    
    /**
     * Hash map experiments
     */
    @Test
    public void hashMapExperiments() {
        
        Map<String, Integer>    tempMap = new java.util.HashMap<String, Integer>();
        List<Integer>           tempSortedValues;
        
        tempMap.put(null, new Integer(1));
        assertTrue("Could not retreive key null", tempMap.get(null).intValue()==1);
        
        tempMap.put(null, null);
        assertTrue("Could not retreive value null", tempMap.get(null)==null);
        
        tempMap.put("one", new Integer(1));
        tempMap.put("two", new Integer(2));
        tempMap.put("three", new Integer(3));
        System.out.println("Map: " + tempMap);
        
        tempSortedValues = tempMap.values().stream()
                                           .sorted(this.createSortComparator())
                                           .collect(Collectors.toList());
        System.out.println("Sorted values: " + tempSortedValues);
        
    }
    
    
    /**
     * Create sort comparator
     * @return Comparator<Integer>
     */
    protected Comparator<Integer> createSortComparator() {
        
        return  (Integer n1, Integer n2) -> ((n1 != null && n2 != null) ? (n1.intValue() - n2.intValue()) : 1);
        
    }
    
    /**
     * Test find number of ways to complete distance
     */
    @Test
    public void testFindNumberOfWaysToCompleteDistance() {
        
        StringBuilder               tempBuilder;
        Map<Integer, Integer>        tempMemo;


        tempMemo = new HashMap<Integer, Integer>();
        tempBuilder = new StringBuilder();
        this.findNumberOfWaysToCompleteDistance(tempBuilder, 3, tempMemo);
        System.out.println("Nubmer of ways to complete distance 3: " + tempMemo.size());
        System.out.println(tempBuilder.toString());
        
        tempMemo = new HashMap<Integer, Integer>();
        tempBuilder = new StringBuilder();
        this.findNumberOfWaysToCompleteDistance(tempBuilder, 4, tempMemo);
        System.out.println("Nubmer of ways to complete distance 4: " + tempMemo.size());
        System.out.println(tempBuilder.toString());
        
        tempMemo = new HashMap<Integer, Integer>();
        tempBuilder = new StringBuilder();
        this.findNumberOfWaysToCompleteDistance(tempBuilder, 6, tempMemo);
        System.out.println("Nubmer of ways to complete distance 6: " + tempMemo.size());
        System.out.println(tempBuilder.toString());
        
        
    }
    
    
    /**
     * Find the number of ways to complete a distance in up to 3 steps
     * @param aBuilder StringBuilder
     * @param aDistance int
     */
    protected void findNumberOfWaysToCompleteDistance(StringBuilder aBuilder,
                                                      int aDistance,
                                                      Map<Integer, Integer> aMap) {
        
        int[] tempSteps = new int[3];
        
        for (int i = 0; i <= aDistance; i++) {
            
            for (int j = 0; j <=  aDistance; j++) {
                
                for (int k = 0; k <=  aDistance; k++) {
                    
                    tempSteps[0] = i;
                    tempSteps[1] = j;
                    tempSteps[2] = k;
                    
                    if ((aMap.get(this.convertToInteger(tempSteps)) == null) &&
                            (this.computeArraySum(tempSteps) == aDistance)) {
                        
                        aMap.put(this.convertToInteger(tempSteps), new Integer(aDistance));
                        
                        aBuilder.append("Found combo: ");
                        for (int r = 0; r < tempSteps.length; r++) {
                            
                            aBuilder.append(" " + tempSteps[r] + " ");
                        }
                        
                        aBuilder.append(System.getProperty("line.separator"));
                        
                    }
                }
            }
        }
        
        
    }
    
    /**
     * Current array value to integer
     * @param anArray int[]
     * @return Integer
     */
    protected Integer convertToInteger(int[] anArray) {
        
        String  tempConcatenated = new String();
        Integer tempResult = new Integer(0);
        
        for (int i = 0; i < anArray.length; i++) {
            
            if (anArray[i] != 0) {
                
                tempConcatenated += String.valueOf(anArray[i]);
            }
        }
        
        if (!tempConcatenated.isEmpty()) {
            
            tempResult = Integer.valueOf(tempConcatenated);
        }
        
        return tempResult;
        
    }
    
    
    
    
    /**
     * Compute current array sum
     * @param anArray int[]
     * @return int
     */
    protected int computeArraySum(int[] anArray) {
        
        int tempSum = 0;
        
        for (int i = 0; i < anArray.length; i++) {
            
            tempSum += anArray[i];
        }
        
        return tempSum;
        
    }
    
    /**
     * Filter example with Streams
     */
    @Test
    public void filterExampleWithStreamsTest() {
        
        String[]            tempInitArgs = {"aaaab", "aaasaa", "bbbbb", "ccccc", "ddddd", "esese"};
        List<String>        tempInitList;
        List<String>        tempResults;
        
        tempInitList = Arrays.asList(tempInitArgs);
        tempResults = tempInitList.stream()
                                  .filter(this.getSPredicate())
                                  .collect(Collectors.toList());
        
        System.out.println("Initial List: " + tempInitList.toString());
        System.out.println("Results : " + tempResults.toString());
        
        
    }
    
    /**
     * Answer a predicate to filter elements with "s"
     * @return Predicate<String>
     */
    protected Predicate<String> getSPredicate() {
        
        return (aVal) -> (aVal.indexOf("s") != -1);
    }
    
    /**
     * Filter example with Streams
     */
    @Test
    public void findFirstExampleWithStreamsTest() {
        
        String[]            tempInitArgs = {"aaaab", "aaasaa", "bbbbb", "ccccc", "ddddd", "esese"};
        List<String>        tempInitList;
        Optional<String>    tempResult;
        
        tempInitList = Arrays.asList(tempInitArgs);
        tempResult = tempInitList.stream()
                                  .filter(this.getSPredicate())
                                  .findFirst();
        assertTrue("No element", tempResult.get() != null && tempResult.get().equals("aaasaa"));
        
        System.out.println("Initial List: " + tempInitList.toString());
        System.out.println("Result : " + tempResult.get().toString());

        
        
    }
    
    
    /**
     * Map example with Streams
     */
    @Test
    public void mapExampleWithStreamsIntTest() {
        
        int[]           tempInputArgs = {1,2,3,4,5};
        IntStream       tempInputStream;
        Stream<Record>  tempMappedStream;
        List<Record>    tempResults;
        
        
        tempInputStream = Arrays.stream(tempInputArgs);
        System.out.print("Initial List: ");        
        tempInputStream.forEach(this.createIntConsumerToPrintInt());
        System.out.println();
        
        tempInputStream = Arrays.stream(tempInputArgs);
        tempMappedStream = tempInputStream.mapToObj(this.createIntToRecordMapper());
        tempResults = tempMappedStream.collect(Collectors.toList());
        
        
        System.out.println("Results : " + tempResults.toString());
        
    }
    
    /**
     * Map example with Streams
     */
    @Test
    public void mapExampleWithStreamsTest() {
        
        Integer[]           tempInputArgs = {new Integer(1),
                                             new Integer(2),
                                             new Integer(3),
                                             new Integer(4),
                                             new Integer(5)};
        
        List<Integer>   tempInputList;
        List<Record>    tempResults;
        
        tempInputList = Arrays.asList(tempInputArgs);
        System.out.println("Initial List: " + tempInputList.toString());
        
        tempResults = tempInputList.stream()
                                   .map(this.createIntegerToRecordConversionFunction())
                                   .collect(Collectors.toList());
        
        
        System.out.println("Results : " + tempResults.toString());
        
    }

    /**
     * Create Integer to record conversion function
     * @return
     */
    protected Function<? super Integer, ? extends Record> createIntegerToRecordConversionFunction() {
        
        
        return (s)->new Record(s.intValue());
        
    }
    
    

    /**
     * Create int consumer that will print an int
     * @return IntConsumer
     */
    protected IntConsumer createIntConsumerToPrintInt() {
        
        return (p)->System.out.print(p + ",");
        
    }

    /**
     * Answer a function that maps an int to a Record
     * @return
     */
    protected IntFunction<? extends Record> createIntToRecordMapper() {
        
        return (s)->new Record(s);
    }

    
   
    /**
     * Test println formats....don't remember these from C
     */
    @Test
    public void printlnFormatTests() {
        
        int  tempInt = 10;        
        String  tempString = " Java";
        String  tempFormatted;
        
        tempFormatted = String.format("%-15s %03d", tempString, tempInt);
        System.out.println(tempFormatted);
    }
    
    /**
     * Dump available locales
     * 
     */
    @Test
    public void dumpAvailabelLocales() {
       
        for (Locale aLocale: Locale.getAvailableLocales()) {
            System.out.println("Country: " + aLocale.getDisplayCountry() + " locale symbol: " + aLocale.toString());
        }
       
    }
    
    /**
     * 
     */
    @Test
    public void dequeueTest() {
        
        Iterator<Integer>               in;
        Deque<Integer>                  deque = new ArrayDeque<Integer>();
        int                             n = 6;
        int                             m = 3;
        Integer[]                       tempValues = {5,3,5,2,3,2};
        int                             tempMaxUniques = Integer.MIN_VALUE;
        
        in = Arrays.asList(tempValues).iterator();
        
        for (int i = 0; i < n; i++) {
            
            int num = in.next();
            deque.addLast(num);
            
            if (deque.size() >= m) {
                
                tempMaxUniques = this.determineUniqueNumbers(deque, tempMaxUniques);
                System.out.println("Current set of numbers: " + deque.toString());
                System.out.println("Current Max: " + tempMaxUniques);
                deque.remove();
                
            }
            
        }
        
    }
    
    /**
     * Determine unique numbers. Method is only called when we aQueue contains
     * the window size
     */
    protected int determineUniqueNumbers(Deque<Integer> aQueue,
                                        int aCurrentMax) {
        
        int             tempResult;
        Set<Integer>    tempUniques;
        
        tempResult = aCurrentMax;
        tempUniques = new HashSet<Integer>(aQueue);
        
        if (tempUniques.size() > tempResult) {
            tempResult = tempUniques.size();
        }
        
        return tempResult;
    }
    
    /**
     * Produce unique consecutive subarrays
,     */
    @Test
    public void produceAllSubarrays( ) {
        
        Integer[]                       tempValues = {5,3,5,2,3,2};
        List<List<Integer>>             tempSubarrays = new ArrayList<List<Integer>>();
        
        for (int i = 1; i <= tempValues.length; i++) {
            
            this.produceUniqueConsecutiveSubarrays(tempValues, i, tempSubarrays);
        }
        
        System.out.println("Subarrays: " + tempSubarrays);
    }
    
    
    /**
     * Produce the subarrays for a given choice size
     * @param aValues
     * @param aChoiceSize
     * @param aSubarrays
     */
    protected void produceUniqueConsecutiveSubarrays(Integer[] aValues,
                                                                    int aChoiceSize,
                                                                    List<List<Integer>> aSubarrays) {
        
        List<Integer>                   tempIntList;
        java.util.LinkedList<Integer>   tempQueue;
        List<Integer>                   tempCurrentSubarray;
        
        tempIntList = Arrays.asList(aValues);
        
        tempQueue = new java.util.LinkedList<Integer>(tempIntList);
        while (!tempQueue.isEmpty()) {
            
            if (tempQueue.size() >= aChoiceSize) {
                
                tempCurrentSubarray = new ArrayList<Integer>();
                for (int i = 0; i < aChoiceSize; i++) {
                    
                    tempCurrentSubarray.add(tempQueue.get(i));
                }
                
                aSubarrays.add(tempCurrentSubarray);
                
            }
            
            tempQueue.remove();
        }
        
        
    }
    
    /**
     * Produce all substrings for a given choice size
     * @param aValue
     * @param aChoiceSize
     * @return List<String>
     */
    protected List<String> produceUniqueConsecutiveSubstrings(String aValue,
                                                                     int aChoiceSize) {
        
        List<String>         tempSubStrings = new ArrayList<String>();
        String               tempValue;
        
        tempValue = new String(aValue);

        while (tempValue.length() >= aChoiceSize) {
            
   
            tempSubStrings.add(tempValue.substring(0, aChoiceSize));
            tempValue = tempValue.substring(1); //Remove the first element in the string every time

            
        }
        
        return tempSubStrings;
        
        
    }
    
    /**
     * Show that two words are anagrams or not based on the following rule: the frequency of letters is exactly
     * the same in both words.
     */
    @Test
    public void anagramTest() {
        
        boolean tempTestResult;
        
        tempTestResult = this.isAnagram("anagram","margana");
        assertTrue("Should be an anagram", tempTestResult);
        
        tempTestResult = this.isAnagram("x","xx");
        assertTrue("Should not be an anagram", !tempTestResult);
        
        tempTestResult = this.isAnagram("anagramm","marganaa");
        assertTrue("Should not be an anagram", !tempTestResult);
        
    }
    
    /**
     * Answer whether aWord0 and a Word1 are anagrams
     * @param aWord0 String
     * @param aWord1 String
     * @return boolean
     */
    protected boolean isAnagram(String aWord0, String aWord1) {
        
        Map<String, Integer[]> tempAnagramMap = new HashMap<String, Integer[]>();
        String                 tempWord0;
        String                 tempWord1;
        boolean                tempResult;
        
        tempWord0 = (new String(aWord0)).toUpperCase();
        tempWord1 = (new String(aWord1)).toUpperCase();
        
        //Add first word
        for (int i = 0; i < aWord0.length(); i++) {
            
            this.addLetter(tempWord0, 0, 2, tempAnagramMap);
            tempWord0 = this.removeFirstLetter(tempWord0);
        }
        
        //Add second word
        for (int i = 0; i < aWord1.length(); i++) {
            
            this.addLetter(tempWord1, 1, 2, tempAnagramMap);
            tempWord1 = this.removeFirstLetter(tempWord1);
        }
        
        tempResult = this.areFrequenceOfLettersTheSame(tempAnagramMap);
        
        return tempResult;
        
    }
    
    /**
     * Remove first letter from aValue. Answer a new string or null if aValue is not big enough
     * @param aValue String
     * @return String
     */
    protected String removeFirstLetter(String aValue) {
        
        String  tempResult = null;
        
        if (aValue.length() > 1) {
            
            tempResult  = aValue.substring(1);
        }
        
        return tempResult;
        
    }
    
    /**
     * Are frequency of letters the same
     * @param aMap Map<String, Integer[]>
     * @return boolean
     */
    protected boolean areFrequenceOfLettersTheSame(Map<String, Integer[]> aMap) {
        
        Iterator<Integer[]> tempValues;
        boolean             tempWhileEqual = true;
        Integer[]           tempCurrentArray;
        Integer             tempCurrentValue;
        int                 tempArraySize;
        int                 i;
        
        tempValues = aMap.values().iterator();
        while (tempValues.hasNext() && tempWhileEqual) {
            
            tempCurrentArray = tempValues.next();
            tempArraySize = tempCurrentArray.length;
            tempCurrentValue = tempCurrentArray[0];
            i = 1;
            
            while ((i < tempArraySize ) &&
                    (tempWhileEqual) ) {
                
                if (tempCurrentValue.intValue() != tempCurrentArray[i].intValue()) {
                    tempWhileEqual = false;
                }
                i++;
                
            }
            
        }
        
        return tempWhileEqual;
        
    }
    
    /**
     * Add a letter from aWord with aWordIndex to aMap. The current letter is always assumed to
     * be at index 0 in aWord
     * @param aWord String
     * @param aNumberOfWords int
     * @param aWordIndex int
     * @param aMap Map<String, Integer[]>
     */
    protected void addLetter(String aWord, 
                             int aWordIndex,
                             int aNumberOfWords,
                             Map<String, Integer[]> aMap) {
        
        Integer[]   tempLetterArray;
        String      tempLetter;
        
        tempLetter = aWord.substring(0, 1);
        tempLetterArray = aMap.get(tempLetter);
        if (tempLetterArray == null) {
            
            tempLetterArray = new Integer[aNumberOfWords];
            this.initializeIntegerArray(tempLetterArray);
            aMap.put(tempLetter, tempLetterArray);
            
        }
        
        tempLetterArray[aWordIndex] = new Integer(tempLetterArray[aWordIndex].intValue() + 1);
        
    }
    
    /**
     * Initialize integer array
     * @param anArray Integer[]
     */
    protected void initializeIntegerArray(Integer[] anArray) {
        
        for (int i = 0; i < anArray.length; i++) {
            
            anArray[i] = new Integer(0);
        }
        
    }
  
    
    /**
     * Test of finding pairs
     */
    @Test
    public void findArrayPairsWhoseSumIsKTest() {
        
        int[]                   tempArray1 = {0, 5, 10, 20, 25, 27};
        int[]                   tempArray2 = {15, 20, 25, 30, 35, 40};
        int                     tempSum = 40;
        ArrayChallenges         tempChallenges;
        List<List<Integer>>     tempResult;
        
        
        tempChallenges = new ArrayChallenges();
        tempResult = tempChallenges.findPairsWhoSumEqualsFrom(tempArray1, tempArray2, tempSum);
        System.out.println(tempResult.toString());
        
        
    }

    /**
     * Object mapper test
     */
    @Test
    public void objectMapperTest() throws Exception {

        ObjectMapper    tempMapper = new ObjectMapper();
        TicTacToeGame   tempGame = new TicTacToeGame();
        byte[]          tempData;
        TicTacToeGame   tempNewGame;


        tempMapper =
            tempMapper
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        tempGame.startNewGame();
        this.setUpPlayersForNonPersistentTest(tempGame);
        System.out.println("Game before: " + tempGame.toString());

        tempData = tempMapper.writeValueAsBytes(tempGame);
        System.out.println("Bytes written: " + new String(tempData));
        tempNewGame = tempMapper.readValue(tempData, TicTacToeGame.class);

        System.out.println("Game after: " + tempNewGame.toString());

    }

    /**
     * Set up players for non-persistent test
     * @param aGame TicTacToeGame
     */
    private void setUpPlayersForNonPersistentTest(TicTacToeGame aGame) {

        int tempValue = 1;

        for (Player aPlayer: aGame.getPlayers()) {

            aPlayer.setId(new Long(tempValue++));
        }
    }


    /**
     * Dozer mapper test
     */
    @Test
    public void dozerMapperTest() {

        TicTacToeGame   tempGame = new TicTacToeGame();
        TicTacToeGame   tempNewGame;
        Mapper          tempDozerMapper;


        this.setUpPlayersForNonPersistentTest(tempGame);
        System.out.println("Game before: " + tempGame.toString());

        tempDozerMapper = DozerBeanMapperBuilder.buildDefault();
        tempNewGame = tempDozerMapper.map(tempGame, TicTacToeGame.class);
        System.out.println("Game after: " + tempNewGame.toString());

        assertTrue("Instance are the same hashcode or different number of players",
                !this.instancesAreTheSame(tempGame, tempNewGame)
                 && (tempGame.getPlayers().size() ==
                            tempNewGame.getPlayers().size()));

    }

    /**
     * Answer true if anObjectInstance1 and anObjectInstance2 are the same
     * hashcode. Answer false otherwise
     * @param anObjectInstance1 Object
     * @param anObjectInstance2 Object
     * @return boolean
     */
    private boolean instancesAreTheSame(Object anObjectInstance1,
                                        Object anObjectInstance2) {

        return System.identityHashCode(anObjectInstance1)
                    == System.identityHashCode(anObjectInstance2);

    }


}
