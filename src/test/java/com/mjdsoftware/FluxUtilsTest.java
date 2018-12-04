package com.mjdsoftware;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.Arrays;
import static junit.framework.TestCase.assertTrue;

public class FluxUtilsTest {

    private boolean testCompleted;
    private Semaphore testCompletedSignal;
    private int numberOfExpectedIterations;
    private int numberOfActualIterations;

    /**
     * Setup
     */
    @Before
    public void setup() {

        this.setTestCompletedSignal(new Semaphore(0));
    }

    /**
     * Tear down
     */
    @After
    public void tearDown() {

    }


    /**
     * Test of integer stream
     */
    @Test
    public void integerStreamTest() {

        Flux<Integer>   testFlux;
        FluxUtils       tempUtils = new FluxUtils();
        Integer[]       tempInts = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        this.setNumberOfExpectedIterations(10);
        this.setNumberOfActualIterations(0);
        testFlux = tempUtils.createFluxFrom(Arrays.asList(tempInts).stream());
        testFlux.subscribe(this.createFakeSubscriber());

        this.safelyWaitForSignal(3000);
        assertTrue("Did not get correct number of integers",
                                                this.getNumberOfExpectedIterations()
                                                            == this.getNumberOfActualIterations());

    }

    /**
     * Test of flux interval
     */
    @Test
    public void numberOfTicksTest() {

        Flux<Long>   testFlux;
        FluxUtils       tempUtils = new FluxUtils();

        this.setNumberOfExpectedIterations(2);
        this.setNumberOfActualIterations(0);
        testFlux = tempUtils.createFluxFor(Duration.ofSeconds(2));
        testFlux.subscribe(this.createFakeSubscriberForNumberOfTicks());

        this.safelyWaitForSignal(10000);
        assertTrue("Did not get correct duration",
                this.getNumberOfExpectedIterations()
                        == this.getNumberOfActualIterations());
    }

    /**
     * Safely wait for signal
     */
    private void safelyWaitForSignal(long aNumberOfMilliseconds) {

        try {
            this.getTestCompletedSignal().tryAcquire(1,
                                                     aNumberOfMilliseconds,
                                                     TimeUnit.MILLISECONDS);
        }
        catch(InterruptedException e) {

            //Do nothing
        }
    }

    /**
     * Create fake subscriber
     * @return Consumer
     */
    private Consumer<Integer> createFakeSubscriber() {

        return (p) -> {

            this.setNumberOfActualIterations(p.intValue() + 1);
            System.out.println("Received " + p.toString());

            if (p.intValue() == this.getNumberOfExpectedIterations()) {

                this.setTestCompleted(true);
                this.getTestCompletedSignal().release();
            }

        };
    }

    /**
     * Create fake subscriber for number of ticks
     * @return Consumer
     */
    private Consumer<Long> createFakeSubscriberForNumberOfTicks() {

        return (p) -> {

            if (!this.isTestCompleted()) {

                this.setNumberOfActualIterations(p.intValue());
                System.out.println("Received " + p.toString());

                if (p.intValue() == this.getNumberOfExpectedIterations()) {

                    this.setTestCompleted(true);
                    this.getTestCompletedSignal().release();
                }

            }

        };
    }


    /**
     * Answer whether my test has completed
     * @return boolean
     */
    private boolean isTestCompleted() {
        return testCompleted;
    }

    /**
     * Set that my test has completed
     * @param testCompleted
     */
    private void setTestCompleted(boolean testCompleted) {
        this.testCompleted = testCompleted;
    }

    /**
     * Set my semaphore
     * @param testCompletedSignal
     */
    private void setTestCompletedSignal(Semaphore testCompletedSignal) {
        this.testCompletedSignal = testCompletedSignal;
    }

    /**
     * Answer my signal
     * @return Semaphore
     */
    private Semaphore getTestCompletedSignal() {
        return testCompletedSignal;
    }

    /**
     * Answer my number of expected iterations
     * @return int
     */
    private int getNumberOfExpectedIterations() {
        return numberOfExpectedIterations;
    }

    /**
     * Set my expected number of iterations
     * @param numberOfExpectedIterations
     */
    private void setNumberOfExpectedIterations(int numberOfExpectedIterations) {
        this.numberOfExpectedIterations = numberOfExpectedIterations;
    }

    /**
     * Answer my actual number of iterations
     * @return int
     */
    private int getNumberOfActualIterations() {
        return numberOfActualIterations;
    }

    /**
     * Set my actual number of iterations
     * @param numberOfActualIterations
     */
    private void setNumberOfActualIterations(int numberOfActualIterations) {
        this.numberOfActualIterations = numberOfActualIterations;
    }

}
