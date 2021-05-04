package edu.epam.shapes.reader;

import edu.epam.shapes.exception.ShapeException;
import edu.epam.shapes.validator.StringValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RectangleReader {
    private static Logger logger = LogManager.getLogger();
    public List<String> readAllCorrectLines(String filename, String separator) throws ShapeException {
        if (filename == null){
            logger.log(Level.ERROR, "Filename is null");
            throw new ShapeException("Filename is null");
        }
        File file = new File(filename);
        if (!file.exists()){
            logger.log(Level.ERROR, "File doesn't exist");
            throw new ShapeException("File doesn't exist");
        }
        if (file.length() == 0){
            logger.log(Level.ERROR, "File is empty");
            throw new ShapeException("File is empty");
        }
        List<String> result;
        try (Stream<String> stream = Files.lines(Paths.get(filename))){
            result = stream.filter(str -> StringValidator.validateString(str, separator)).collect(Collectors.toList());
        } catch (IOException exception) {
            throw new ShapeException(exception);
        }
        logger.log(Level.INFO, "Reader read all lines");
        return result;
    }
}
