package by.epam.vladlitvin.reader;

import by.epam.vladlitvin.exception.FileReadException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created by vlad_ on 3/18/2017.
 */
public class TextReader {
    private final static Logger LOGGER = LogManager.getLogger(TextReader.class.getName());

    public static String readTextFromFile(String fileName) throws FileReadException {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader =  new FileReader(fileName)){
            int c;
            while((c = reader.read())!= -1){
                stringBuilder.append((char) c);
            }
            LOGGER.log(Level.INFO,"file: \"" + fileName + "\" was read. File contain:\n"
                    + stringBuilder.toString());
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.FATAL,"This file not found " + e);
            throw new RuntimeException(e.getMessage(), e);

        } catch (IOException e) {
            throw new FileReadException("The file is unreadable", e);
        }

        return stringBuilder.toString();
    }

}
