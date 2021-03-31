package edu.epam.arrays.reader;

import edu.epam.arrays.exception.ArrayCustomException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ArrayReader {
    private FileReader reader;
    public ArrayReader(String fileName) throws ArrayCustomException {
        try {
            reader = new FileReader(fileName);
        }catch (FileNotFoundException e){
            throw new ArrayCustomException("File not found");
        }
    }
    public String readLine() throws ArrayCustomException {
        try {
            StringBuilder builder = new StringBuilder();
            int symbol;
            while (true){
                symbol = reader.read();
                if ((char)symbol == '\n' || symbol == -1){
                    return builder.toString();
                }else{
                    builder.append((char)symbol);
                }
            }
        }catch (IOException e){
            throw new ArrayCustomException("File is empty");
        }
    }
    public String[] readAllLines() throws ArrayCustomException{
        try{
            StringBuilder builder = new StringBuilder();
            int symbol;
            while (true){
                symbol = reader.read();
                if (symbol == -1){
                    String result = builder.toString();
                    return result.split("\n");
                }else{
                    builder.append((char) symbol);
                }
            }
        }catch (IOException e){
            throw new ArrayCustomException("File is empty");
        }
    }
}
