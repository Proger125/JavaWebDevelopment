package edu.epam.handling.reader;

import edu.epam.handling.exception.HandlerException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.stream.Stream;

public class TextReader {
    private static Logger logger = LogManager.getLogger();

    public String read(String filename) throws HandlerException {
        if (filename == null){
            throw new HandlerException("Filename is null");
        }
        File file = new File(filename);
        if (!file.exists()){
            throw new HandlerException("File doesn't exist " + filename);
        }
        if (!file.canRead()){
            throw new HandlerException("Can't read file " + filename);
        }
        if (file.length() == 0){
            throw new HandlerException("File is empty " + filename);
        }
        StringBuilder builder = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Stream<String> lines = reader.lines();
            lines.forEach(str -> builder.append(str).append('\n'));
        } catch (FileNotFoundException e) {
            throw new HandlerException("File not found");
        } catch (IOException exception) {
            throw new HandlerException("Something wrong with file...");
        }
        logger.log(Level.INFO, "Function read all text");
        return builder.toString();
    }
}
