package edu.epam.threads.util;

public final class IdGenerator {
    private static long id = 0;
    private IdGenerator(){}
    public static long createId(){
        return ++id;
    }
}
