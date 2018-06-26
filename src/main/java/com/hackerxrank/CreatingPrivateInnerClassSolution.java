package com.hackerxrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.Permission;


public class CreatingPrivateInnerClassSolution {

    public static void main(String[] args) throws Exception {
        DoNotTerminate.forbidExit();    

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int num = Integer.parseInt(br.readLine().trim());
            Object o;// Must be used to hold the reference of the instance of the class Solution.Inner.Private
            
           
            Object     tempOuterClassInstance;
            
            Class<?>[] tempMethodParamTypes = {int.class};
            Object[] tempMethodParams;
            
            tempOuterClassInstance = new CreatingPrivateInnerClassSolution.Inner();
            
            Constructor<?> tempConstructor = 
                CreatingPrivateInnerClassSolution.Inner.Private.class.getDeclaredConstructors()[0];
            tempConstructor.setAccessible(true);
            
            o = tempConstructor.newInstance(tempOuterClassInstance);

            Method tempMethod = CreatingPrivateInnerClassSolution.Inner.Private.class.getDeclaredMethod("powerof2", tempMethodParamTypes);
            tempMethod.setAccessible(true);

            tempMethodParams = new Object[1];
            tempMethodParams[0] = num;
            tempMethod.invoke(o, tempMethodParams);
            System.out.println("An instance of class: " + o.getClass().getCanonicalName() + " has been created");
            
            }//end of try
            
            catch (DoNotTerminate.ExitTrappedException e) {
                System.out.println("Unsuccessful Termination!!");
            }
        }//end of main
        static class Inner{
            private class Private{
                private String powerof2(int num){
                    return ((num&num-1)==0)?"power of 2":"not a power of 2";
                }
            }
        }//end of Inner
        
    }//end of Solution

    class DoNotTerminate { //This class prevents exit(0)
         
        public static class ExitTrappedException extends SecurityException {

            private static final long serialVersionUID = 1L;
        }
     
        public static void forbidExit() {
            final SecurityManager securityManager = new SecurityManager() {
                @Override
                public void checkPermission(Permission permission) {
                    if (permission.getName().contains("exitVM")) {
                        throw new ExitTrappedException();
                    }
                }
            };
            System.setSecurityManager(securityManager);
        }
        
 }   
            