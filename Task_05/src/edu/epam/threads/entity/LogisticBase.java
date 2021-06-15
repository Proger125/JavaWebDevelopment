package edu.epam.threads.entity;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticBase {
    static final Logger logger = LogManager.getLogger();
    private static LogisticBase instance;
    private static final AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static final int TERMINAL_AMOUNT;
    private static final int CAPACITY;
    private static final int RESET_COUNT;
    private static int currentCount;
    private final ReentrantLock addRemoveLock = new ReentrantLock();
    private final Deque<Terminal> freeTerminals = new ArrayDeque<>();
    private final Deque<Condition> waitingTrucks = new ArrayDeque<>();
    static {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("resources/Base");
            TERMINAL_AMOUNT = Integer.parseInt(bundle.getString("TerminalAmount"));
            CAPACITY = Integer.parseInt(bundle.getString("Capacity"));
            RESET_COUNT = Integer.parseInt(bundle.getString("ResetCount"));
            currentCount = Integer.parseInt(bundle.getString("CurrentCount"));
        }catch (MissingResourceException e){
            throw new ExceptionInInitializerError("Properties file not found");
        }
    }
    private LogisticBase(){
        for (int i = 0; i < TERMINAL_AMOUNT; i++){
            freeTerminals.push(new Terminal());
        }
    }
    public static LogisticBase getInstance(){
        while (instance == null){
            if (isInitialized.compareAndSet(false, true)){
                instance = new LogisticBase();
            }
        }
        return instance;
    }
    public Terminal removeTerminal(boolean perishable){
        try{
            addRemoveLock.lock();
            if (freeTerminals.isEmpty()){
                try{
                    Condition condition = addRemoveLock.newCondition();
                    if (perishable){
                        waitingTrucks.addFirst(condition);
                    }else{
                        waitingTrucks.addLast(condition);
                    }
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return freeTerminals.removeFirst();
        }finally {
            addRemoveLock.unlock();
        }
    }
    public void addTerminal(Terminal terminal){
        try{
            addRemoveLock.lock();
            freeTerminals.push(terminal);

            Condition condition = waitingTrucks.pollFirst();
            if (condition != null){
                condition.signal();
            }
        }finally {
            addRemoveLock.unlock();
        }
    }

}
