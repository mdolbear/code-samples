package com.oracle.regex;

/**
 *
 *
 */
public class RegexUtils {

    private static RegexUtils instance;
    
    /**
     * Answer my default instance
     * @return RegexUtils
     */
    public synchronized static RegexUtils getInstance() {
        
        if (instance == null) {
            
            instance = new RegexUtils();
        }
        
        return instance;
        
    }
    
    
    /**
     * Answer an instance for the following arguments
     */
    protected RegexUtils() {
       super();
    }

}
