package edu.epam.arrays.exceptions;

public class ArrayCustomException extends Exception{
    private String message;
    public ArrayCustomException(String message){
        this.message = message;
    }
    public void logMessage(){
        // TODO Log message
    }
}
