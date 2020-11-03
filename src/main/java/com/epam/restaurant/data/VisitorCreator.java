package com.epam.restaurant.data;

import com.epam.restaurant.model.Visitor;

import java.util.List;

public interface VisitorCreator {

    List<Visitor> create(String fileName) throws DataException;

}