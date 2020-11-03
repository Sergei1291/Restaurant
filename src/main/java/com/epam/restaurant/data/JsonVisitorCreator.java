package com.epam.restaurant.data;

import com.epam.restaurant.model.Visitor;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonVisitorCreator implements VisitorCreator {

    private ObjectMapper objectMapper = new ObjectMapper();
    private DataReader dataReader;

    public JsonVisitorCreator(DataReader dataReader) {

        this.dataReader = dataReader;

    }

    @Override
    public List<Visitor> create(String fileName) throws DataException {

        try {

            List<String> lines = dataReader.readLines(fileName);

            List<Visitor> visitors = new ArrayList<>();

            for (String line : lines) {
                Visitor visitor = objectMapper.readValue(line, Visitor.class);
                visitors.add(visitor);
            }

            return visitors;

        } catch (IOException e) {
            throw new DataException(e);
        }

    }

}