package edu.epam.threads.main;

import com.google.gson.Gson;
import edu.epam.threads.entity.Truck;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    static final Logger logger = LogManager.getLogger();
    private static final String DEFAULT_FILE_NAME = "src/resources/data.json";
    public static void main(String[] args) {
        Gson gson = new Gson();
        try {
            Truck[] trucks = gson.fromJson(new FileReader(DEFAULT_FILE_NAME), Truck[].class);
            ExecutorService service = Executors.newFixedThreadPool(trucks.length);
            Arrays.stream(trucks).forEach(service::execute);
            service.shutdown();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
