package edu.epam.arrays.entity;

import edu.epam.arrays.exceptions.ArrayCustomException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Array {
    static Logger logger = LogManager.getLogger();
    private Integer[] data;
    public Array(){}
    public Array(int size){
        this.data = new Integer[size];
        logger.log(Level.INFO, "Array was created");
    }
    public Array(Integer...values){
        this.data = values;
        logger.log(Level.INFO, "Array was created");
    }
    public int getSize(){
        return this.data.length;
    }
    public void setElementAt(Integer pos, Integer element) throws ArrayCustomException {
        if (pos < 0 || pos > this.data.length - 1){
            throw new ArrayCustomException("Position is out of range");
        }
        this.data[pos] = element;
        logger.log(Level.INFO, "Element in position " + pos + " was changed");
    }

    public Integer getElementAt(Integer pos) throws ArrayCustomException {
        if (pos < 0 || pos > this.data.length - 1){
            throw new ArrayCustomException("Position is out of range");
        }
        return this.data[pos];
    }
    public Integer[] getData(){
        Integer[] array = new Integer[this.data.length];
        System.arraycopy(this.data, 0, array, 0, array.length);
        return array;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        if (obj.getClass() != this.getClass()){
            return false;
        }
        Array array = (Array) obj;
        if (this.data.length == array.data.length){
            for (int i = 0; i < this.data.length; i++){
                if (!this.data[i].equals(array.data[i])){
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[ ");
        for (int i = 0; i < this.data.length; i++){
            builder.append(this.data[i]).append(" ");
        }
        builder.append("]");
        return builder.toString();
    }
}
