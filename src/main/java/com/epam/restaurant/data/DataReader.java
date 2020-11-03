package com.epam.restaurant.data;

import java.util.List;

public interface DataReader {

    List<String> readLines(String fileName) throws DataException;

}