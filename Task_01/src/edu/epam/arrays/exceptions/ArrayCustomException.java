package edu.epam.arrays.exceptions;

public class ArrayCustomException extends Exception{
    public ArrayCustomException(){
        super();
    }
    public ArrayCustomException(String message){
        super(message);
    }
    public ArrayCustomException(String message, Throwable cause){
        super(message, cause);
    }
    public ArrayCustomException(Throwable cause){
        super(cause);
    }
}
