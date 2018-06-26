package com.oracle.proxyexample;

/**
 *
 *
 */
public class TargetClass implements TargetInterface {

    /**
     * Answer an instance for the following arguments
     */
    public TargetClass() {
        super();
    }

    /**
     * Say hello to me
     */
    public void sayHelloToMe() {
        
        System.out.println("Hello to you");
    }
    
    
    /* (non-Javadoc)
     * @see com.oracle.proxyexample.TargetInterface#sayHello()
     */
    @Override
    public void sayHello() {
        
        System.out.println("Hello");
    }
    
    /* (non-Javadoc)
     * @see com.oracle.proxyexample.TargetInterface#sayHelloAgain()
     */
    @Override
    public void sayHelloAgain() {
        
        System.out.println("Hello Again");
    }
    
    /* (non-Javadoc)
     * @see com.oracle.proxyexample.TargetInterface#sayHelloAgainWithException()
     */
    @Override
    public void sayHelloAgainWithException() {
        
        throw new RuntimeException("Some Exception");
    }
    
 
    /* (non-Javadoc)
     * @see com.oracle.proxyexample.TargetInterface#sayHelloAgainWithWappedJmsException()
     */
    @Override
    public void sayHelloAgainWithWappedJmsException() {
        
        throw new RuntimeException(new IllegalArgumentException("Some Exception on the inside"));
    }
    
}
