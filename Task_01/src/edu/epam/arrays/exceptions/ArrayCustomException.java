package edu.epam.arrays.exceptions;

import java.lang.management.ThreadInfo;

public class ArrayCustomException extends Exception{
    private String message;
    public ArrayCustomException(){
        super();
    }
    public ArrayCustomException(String message){
        super(message);
        this.message = message;
    }
    public ArrayCustomException(String message, Throwable cause){
        super(message, cause);
    }
    public ArrayCustomException(Throwable cause){
        super(cause);
    }
    public void logMessage(){
        // TODO Log message
    }
}
