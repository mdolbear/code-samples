package com.oracle.proxyexample;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class Client {

    private TargetInterface target;
    
    
    
    /**
     * Answer an instance for the following arguments
     */
    public Client() {
        super();
        this.setTarget(new TargetClass());
    }
    
    
    /**
     * Say hello to me
     */
    public void sayHelloToMe() {
        
        this.getTarget().sayHelloToMe();
    }
    
    /**
     * Call target method
     */
    public void sayHello() {
        
        this.getProxiedInstance().sayHello();
    }
    
    /**
     * Call target method
     */
    public void sayHelloAgain() {
        
        this.getProxiedInstance().sayHelloAgain();
    }
    
    /**
     * Call tartet method
     */
    public void sayHelloAgainWithException() {
        
       try {
           
           this.getProxiedInstance().sayHelloAgainWithException();
       }
       catch (RuntimeException e) {
           
           System.out.println("This should have failed immediately");
       }
       
    }
    
 
    /**
     * Call target method
     */
    public void sayHelloAgainWithWappedJmsException() {
        
        try {
            
            this.getProxiedInstance().sayHelloAgainWithWappedJmsException();
        }
        catch (RuntimeException e) {
            
            System.out.println("This should have failed 3 times and then given up");
        }
        
    }
    
    
    /**
     * Answer my wrapped target instance
     */
    protected TargetInterface getProxiedInstance() {
        
        List<Class<?>> tempExceptionClasses = new ArrayList<Class<?>>();
        
        tempExceptionClasses.add(IllegalArgumentException.class);
        return (TargetInterface) Proxy.newProxyInstance(this.getClass().getClassLoader(), 
                                                        new Class[] {TargetInterface.class}, 
                                                        new ProxyRetryWrapper(this.getTarget(),
                                                                              3,
                                                                              tempExceptionClasses));
    }

    /**
     * Answer my target
     * @return TargetInterface
     */
    protected TargetInterface getTarget() {
        return target;
    }

    /**
     * Set my target
     * @param target TargetInterface
     */
    protected void setTarget(TargetInterface target) {
        this.target = target;
    }
    
    /**
     * Put this in a main so it can be simply seen
     * @param args
     */
    public static void main(String[] args) {
        
        Client  tempClient;
        
        tempClient = new Client();
        
        tempClient.sayHelloToMe();
        tempClient.sayHello();
        tempClient.sayHelloAgain();
        tempClient.sayHelloAgainWithException();
        tempClient.sayHelloAgainWithWappedJmsException();
        
    }

}
