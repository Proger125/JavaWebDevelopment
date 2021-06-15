package edu.epam.threads.entity;

import edu.epam.threads.util.IdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Terminal {
    static final Logger logger = LogManager.getLogger();
    private static final int MIN_WAITING_TIME = 10;
    private static final int MAX_WAITING_TIME = 100;
    private final long id;

    public Terminal() {
        this.id = IdGenerator.createId();
    }

    public long getId() {
        return id;
    }

    public void operation(Truck truck){
        long truckId = truck.getId();
        logger.log(Level.INFO, "Terminal " + id + " serves truck " + truckId);
        int waitingTime = new Random().nextInt(MAX_WAITING_TIME - MIN_WAITING_TIME) + MIN_WAITING_TIME;
        try{
            TimeUnit.MILLISECONDS.sleep(waitingTime);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Exception: " + e);
            Thread.currentThread().interrupt();
        }
        logger.log(Level.INFO, "Terminal " + id + " finished with truck " + truckId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Terminal terminal = (Terminal) o;
        return id == terminal.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
