package edu.epam.shapes.reader;

import edu.epam.shapes.exception.ShapeException;
import edu.epam.shapes.validator.StringValidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RectangleReader {
    public List<String> readAllCorrectLines(String filename, String separator) throws ShapeException {
        if (filename == null){
            throw new ShapeException("Filename is null");
        }
        File file = new File(filename);
        if (!file.exists()){
            throw new ShapeException("File doesn't exist");
        }
        if (file.length() == 0){
            throw new ShapeException("File is empty");
        }
        List<String> result;
        try (Stream<String> stream = Files.lines(Paths.get(filename))){
            result = stream.filter(str -> StringValidator.validateString(str, separator)).collect(Collectors.toList());
        } catch (IOException exception) {
            throw new ShapeException(exception);
        }
        return result;
    }
}
