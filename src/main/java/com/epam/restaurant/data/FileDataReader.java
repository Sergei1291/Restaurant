package com.epam.restaurant.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileDataReader implements DataReader {

    @Override
    public List<String> readLines(String fileName) throws DataException {

        try {

            Path path = Paths.get(fileName);

            return Files.readAllLines(path);

        } catch (IOException e) {
            throw new DataException(e);
        }

    }

}