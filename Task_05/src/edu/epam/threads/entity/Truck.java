package edu.epam.threads.entity;

import edu.epam.threads.util.IdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Truck extends Thread{
    static final Logger logger = LogManager.getLogger();
    private final long id;
    private boolean perishable;
    private Task task;

    public Truck(long id, boolean perishable, Task task) {
        this.id = id;
        this.perishable = perishable;
        this.task = task;
    }

    public long getId() {
        return id;
    }

    public boolean isPerishable() {
        return perishable;
    }

    public void setPerishable(boolean perishable) {
        this.perishable = perishable;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        LogisticBase base = LogisticBase.getInstance();
        Terminal terminal = base.removeTerminal(perishable);
        logger.log(Level.INFO, "Terminal " + terminal.getId() + " was occupied by truck " + id);
        terminal.operation(this);

        base.addTerminal(terminal);
        logger.log(Level.INFO, "Terminal " + terminal.getId() + " was released by truck " + id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Truck truck = (Truck) o;
        return id == truck.id &&
                perishable == truck.perishable &&
                task == truck.task;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id) + Boolean.hashCode(perishable) + task.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Truck{").append(" ");
        builder.append("id: ").append(id).append(" ");
        builder.append("perishable: ").append(perishable).append(" ");
        builder.append("task: ").append(task).append(" }");
        return builder.toString();
    }
}
