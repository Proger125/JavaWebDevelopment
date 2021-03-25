package edu.epam.arrays.entity;

import edu.epam.arrays.exceptions.ArrayCustomException;

public class Array {
    private final Integer[] data;
    private final Integer size;
    public Array(int size){
        this.size = size;
        this.data = new Integer[size];
    }
    public Array(Integer[] array){
        this.size = array.length;
        this.data = new Integer[this.size];
        System.arraycopy(array, 0, this.data, 0, this.size);
    }
    public void setElementAt(Integer pos, Integer element) throws ArrayCustomException {
        if (pos < 0 || pos > this.data.length - 1){
            throw new ArrayCustomException("Position is out of range");
        }
        this.data[pos] = element;
    }
    public Integer getElementAt(Integer pos) throws ArrayCustomException {
        if (pos < 0 || pos > this.data.length - 1){
            throw new ArrayCustomException("Position is out of range");
        }
        return this.data[pos];
    }
    public Integer getSize(){
        return this.size;
    }
    public Integer[] getData(){
        Integer[] array = new Integer[this.size];
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
        if (this.size.equals(array.size)){
            for (int i = 0; i < this.size; i++){
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
        builder.append("Array:").append('\n');
        builder.append("Size: ").append(this.size).append('\n');
        builder.append("Elements:").append('\n');
        for (int i = 0; i < this.size; i++){
            builder.append(this.data[i]).append(" ");
        }
        return builder.toString();
    }
}
