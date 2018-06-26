package com.oracle.proxyexample;

public interface TargetInterface extends BaseInterface {

    /**
     * Say hello
     */
    void sayHello();

    /**
     * Say hello again
     */
    void sayHelloAgain();

    /**
     * Say hello again
     */
    void sayHelloAgainWithException();

    /**
     * Say hello again
     */
    void sayHelloAgainWithWappedJmsException();

}