package com.oracle.utils;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 *
 */
public class ReflectionUtils {

    
    private static ReflectionUtils instance;
    
    /**
     * Answer my default instance
     * @return ReflectionUtils
     */
    public synchronized static ReflectionUtils getInstance() {
        
        if (instance == null) {
            instance = new ReflectionUtils();
        }
        
        return instance;
    }
    
    /**
     * Answer an instance of me for the following arguments
     */
    protected ReflectionUtils() {
       super();
    }

   
   
    
    /**
     * Safely invoke aMethod for aTarget with anArguments
     * @param aTarget Object
     * @param aMethod Method
     * @param anArguments List<Object>
     * @return Object
     */
    public Object invokeMethod(Object aTarget,
                                Method aMethod, 
                                List<Object> anArguments) 
                throws Throwable {

        Object[]    tempArgs;
        Object      tempResult;

        try {

            tempArgs = anArguments.toArray(new Object[anArguments.size()]);
            tempResult = aMethod.invoke(aTarget, tempArgs);
            return tempResult;

        }
        catch (InvocationTargetException ie) {

            //Real class has logging here
            throw ie.getCause();
        }
        catch (Exception e) {

           //Real class has logging here
            
            throw e;
            
        }

    }
    
    


    /**
     * Set aValue for aField in anObject
     * @param anObject Object
     * @param aField Field
     * @param aValue Object
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public void setValue(Object anObject, Field aField, Object aValue)
            throws IllegalArgumentException, IllegalAccessException {

        aField.setAccessible(true);
        aField.set(anObject,aValue);

    }


    /**
     * Answer the value for aField in anObject
     * @param anObject Object
     * @param aField Field
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public Object getValue(Object anObject, Field aField)
            throws IllegalArgumentException, IllegalAccessException {

        aField.setAccessible(true);
        return aField.get(anObject);

    }


    
    
    
    /**
     * Answer the property part of aKey
     * @param aKey String
     * @return Field
     * @throws SecurityException
     * @throws NoSuchFieldException
     */
    public Field getFieldForKey(Class<?> aClass, String aKey) throws NoSuchFieldException, SecurityException {

        Field    tempField;


        tempField = this.getDeclaredField(aClass, aKey);
        tempField.setAccessible(true);

        return tempField;
    }


    /**
     * Answer the field for aFieldType (exact class match) or null
     * @param aClass Class<?>
     * @param aFieldType Class<?>
     * return Field
     */
    public Field getDeclaredFieldForExactType(Class<?> aClass, 
                                             Class<?> aFieldType) {

        Field                tempResult = null;
        Iterator<Field>        tempFields;
        Field                tempCurrentField;

        tempFields = this.getAllDeclaredFieldsFor(aClass).iterator();
        while (tempFields.hasNext() && tempResult == null) {

            tempCurrentField = tempFields.next();
            if (tempCurrentField.getType().equals(aFieldType)) {
                tempResult = tempCurrentField;
            }
        }

        return tempResult;

    }
    
    
    /**
     * Answer the field for aKey
     * @param aClass Class<?>
     * @param aKey String
     * return Field
     */
    public Field getDeclaredField(Class<?> aClass, String aKey) {

        Field                tempResult = null;
        Iterator<Field>        tempFields;
        Field                tempCurrentField;

        tempFields = this.getAllDeclaredFieldsFor(aClass).iterator();
        while (tempFields.hasNext() && tempResult == null) {

            tempCurrentField = tempFields.next();
            if (tempCurrentField.getName().equals(aKey)) {
                tempResult = tempCurrentField;
            }
        }

        return tempResult;

    }


    /**
     * Answer all fields for aClass
     * @param aClass Class<?>
     * @return List<Field>
     */
    protected List<Field> getAllDeclaredFieldsFor(Class<?> aClass) {

        List<Field> tempFields = new ArrayList<Field>();

        this.getAllDeclaredFieldsFor(aClass, tempFields);

        return tempFields;
    }


    /**
     * Answer all declared fields for aClass
     * @param aClass Class<?>
     * @return List<Field>
     */
    protected List<Field> getAllDeclaredFieldsFor(Class<?> aClass, List<Field> aFields) {

        for (Field aField : aClass.getDeclaredFields()) {
            aFields.add(aField);
        }

        if (aClass.getSuperclass() != null) {
            this.getAllDeclaredFieldsFor(aClass.getSuperclass(), aFields);
        }

        return aFields;

    }
    
    /**
     * Answer the class from aValue
     * @param aValue String
     * @return Class<?>
     * @throws ClassNotFoundException
     */
    public Class<?> getClassFor(String aValue) throws ClassNotFoundException {

        return Class.forName(aValue);
    }
    
    
    /**
     * Answer a method on me for aMethodName with the standard set of parameters
     * @param aMethodName String
     * @return Method
     */
    public Method getMethodFor(Class<?> aClass,
                                  String aMethodName,
                                  List<Class<?>> anArgumentTypes) 
                    throws Exception {

        Method  tempMethod = null;
        Class<?>[] tempArgumentTypes;

        try {
            
            tempArgumentTypes = anArgumentTypes.toArray(new Class[anArgumentTypes.size()]);
            tempMethod = aClass.getDeclaredMethod(aMethodName, tempArgumentTypes);
            tempMethod.setAccessible(true);

        }
        catch (Exception e) {
            throw e;

        }

        return tempMethod;
        
    }
    
    /**
     * Answer a method on me for aMethodName with the standard set of parameters
     * @param aMethodName String
     * @return Method
     */
    public Method getMethodFor(Class<?> aClass,
                                  String aMethodName,
                                  Class<?>[] anArgumentTypes) 
                    throws Exception {

        Method  tempMethod = null;

        try {
            
            tempMethod = aClass.getDeclaredMethod(aMethodName, anArgumentTypes);
            tempMethod.setAccessible(true);

        }
        catch (Exception e) {
            throw e;

        }

        return tempMethod;
        
    }  
    
}

