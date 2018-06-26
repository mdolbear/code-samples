package com.oracle.proxyexample;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.oracle.utils.ReflectionUtils;

/**
 * Example Use....Whatever is being wrapped by this wrapper would need to wrap the original object. Here is an example of doing this:
 *
  * protected TargetInterface getProxiedInstance() {
 *       
 *     List<Class<?>> tempExceptionClasses = new ArrayList<Class<?>>();
 *       
 *      tempExceptionClasses.add(IllegalArgumentException.class);
 *      return (TargetInterface) Proxy.newProxyInstance(this.getClass().getClassLoader(), 
 *                                                      new Class[] {TargetInterface.class}, 
 *                                                      new ProxyRetryWrapper(this.getTarget(),
 *                                                                            3,
 *                                                                            tempExceptionClasses));
 *   }
 * 
 * 
 * 
 * So the client caller of the original server interface would add a method similar to the style above to create the actual proxy.
 * 
 */
public class ProxyRetryWrapper implements InvocationHandler {

    
    private Object target;
    private Map<String, Method> methods;
    private int numberOfRetries;
    private  List<Class<?>> exceptionsClassesToRetry;
    
    /**
     * Answer an instance for the following arguments
     */
    public ProxyRetryWrapper(Object  aTarget,
                             int     aNumberOfRetries,
                             List<Class<?>> anExceptionClassesToRetry) {
       
        this.setTarget(aTarget);
        this.setNumberOfRetries(aNumberOfRetries);
        this.setExceptionsClassesToRetry(anExceptionClassesToRetry);
        this.initializeTargetMethodsMap(aTarget);
        
    }

    /**
     * Initialize target methods map
     * @param aTarget Object
     */
    protected void initializeTargetMethodsMap(Object aTarget) {
        
        this.setMethods(new HashMap<String, Method>());
        for (Method aMethod: aTarget.getClass().getDeclaredMethods()) {
            
            this.getMethods().put(aMethod.getName(), aMethod);
        }
        
    }

    /* (non-Javadoc)
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
     */
    @Override
    public Object invoke(Object proxy, 
                         Method method, 
                         Object[] args)
            throws Throwable {
        
        List<Object> tempList;
        
        tempList = (args != null) ? Arrays.asList(args) : new ArrayList<Object>();
        
        return this.invokeMethod(this.getTarget(), 
                                 method.getName(), 
                                 tempList);
    }
    
    /**
     * Invoke method with retries for anArguments. If there are any exceptions defined that shouldn't do retries, those will be thrown
     * immediately. Answer an Object result
     * @param aTarget Object
     * @param anArguments List<Object>
     * @return Object
     */
    public Object invokeMethod(Object aTarget,
                              String aTargetMethodName,
                              List<Object> anArguments)
                    throws Throwable {
        
        Object  tempResult = null;
        int     tempNumberOfRetries = 0;
        boolean tempSuccess = false;
        
        while (!tempSuccess 
                && tempNumberOfRetries < this.getNumberOfRetries()) {
            
            try {
                
                tempResult = 
                        ReflectionUtils.getInstance().invokeMethod(aTarget, 
                                                                   this.getMethods().get(aTargetMethodName),
                                                                   anArguments);
                tempSuccess = true;

            }
            catch (Throwable t) {
                
                tempNumberOfRetries++;
                
                this.allowRetryOrThrowException(aTarget, tempNumberOfRetries, t);
                
            }
            
        }
        
        return tempResult;
                
    }

    /**
     * Allow retry or throw exception
     * @param aTarget
     * @param aNumberOfRetries
     * @param t
     * @throws Throwable
     */
    protected void allowRetryOrThrowException(Object aTarget, 
                                              int aNumberOfRetries, 
                                              Throwable t) throws Throwable {
        
        if (aNumberOfRetries >= this.getNumberOfRetries()
                ||
            !this.shouldHandle(t, this.getExceptionsClassesToRetry())) {
            
            
            throw t;
            
        }
        else {
            
            //Put some logging here -- number of retries, etc
            System.out.println("Retrying method on: " + aTarget.toString() + " Number of retries: " + aNumberOfRetries);
        }
        
    }
    
    /**
     * Answer whether anException is of the classes contained in anExceptionClassesToHandle or whether it wraps one of
     * these
     * @param anException Throwable
     * @param anExceptionClassesToHandle List<Class<?>
     * @return boolean
     */
    protected boolean shouldHandle(Throwable anException, 
                                   List<Class<?>> anExceptionClassesToHandle) {
        
        boolean tempShouldHandle = false;
        
        if (anException != null) {
            
            tempShouldHandle = this.isAnInstanceOrSubclassOf(anExceptionClassesToHandle,anException);  
            if (!tempShouldHandle) {
                
                tempShouldHandle = this.shouldHandle(anException.getCause(), anExceptionClassesToHandle);
            }
            
        }
        
        
        
        return tempShouldHandle;
        
    }
    
    /**
     * Answer whether or not anException is a subclass of one of anExceptionClassesToHandle
     * @param anException Throwable
     * @param anExceptionClassesToHandle List<Class<?>>
     */
    protected boolean isAnInstanceOrSubclassOf(List<Class<?>> anExceptionClassesToHandle,
                                               Throwable aThrowable) {
        
        Iterator<Class<?>> tempExceptionClassesToHandle;
        boolean            tempShouldHandle = false;
        Class<?>           tempClass;
        
        tempExceptionClassesToHandle = anExceptionClassesToHandle.iterator();
        while (tempExceptionClassesToHandle.hasNext() && !tempShouldHandle) {
            
            tempClass = tempExceptionClassesToHandle.next();
            tempShouldHandle = tempClass.isAssignableFrom(aThrowable.getClass());
        }
        
        return tempShouldHandle;
        
    }

    /**
     * Answer my target
     * @return Object
     */
    protected Object getTarget() {
        return target;
    }

    /**
     * Set my target
     * @param target Object
     */
    protected void setTarget(Object target) {
        this.target = target;
    }

    /**
     * Answer my methods
     * @return Map<String,Method>
     */
    protected Map<String, Method> getMethods() {
        return methods;
    }

    /**
     * Set my methods
     * @param methods Map<String,Method>
     */
    protected void setMethods(Map<String, Method> methods) {
        this.methods = methods;
    }

    /**
     * Answer my numberOfRetries
     * @return int
     */
    protected int getNumberOfRetries() {
        return numberOfRetries;
    }

    /**
     * Set my numberOfRetries
     * @param numberOfRetries int
     */
    protected void setNumberOfRetries(int numberOfRetries) {
        this.numberOfRetries = numberOfRetries;
    }

    /**
     * Answer my exceptionsClassesToRetry
     * @return List<Class<?>>
     */
    protected List<Class<?>> getExceptionsClassesToRetry() {
        return exceptionsClassesToRetry;
    }

    /**
     * Set my exceptionsClassesToRetry
     * @param exceptionsClassesToRetry List<Class<?>>
     */
    protected void setExceptionsClassesToRetry(
            List<Class<?>> exceptionsClassesToRetry) {
        this.exceptionsClassesToRetry = exceptionsClassesToRetry;
    }
    
    
    
    

}
