package com.oracle.tictactoe;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 *
 */
@Component
@ConfigurationProperties("app")
public class TicTacToeApplicationProperties {

    private String applicationName;
    private String applicationDescription;
    
    /**
     * Answer an instance for the following arguments
     */
    public TicTacToeApplicationProperties() {
        super();
    }

    /**
     * Answer my applicationName
     * @return String
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * Set my applicationName
     * @param applicationName String
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    /**
     * Answer my applicationDescription
     * @return String
     */
    public String getApplicationDescription() {
        return applicationDescription;
    }

    /**
     * Set my applicationDescription
     * @param applicationDescription String
     */
    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }
    
    

}
