package com.epam.restaurant.model;

import org.codehaus.jackson.annotate.*;

@JsonAutoDetect
public class Visitor {

    private final int id;
    private final String name;
    private int orderSum;

    public Visitor(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("orderSum") int orderSum) {

        this.id = id;
        this.name = name;
        this.orderSum = orderSum;

    }

    public int getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public int getOrderSum() {

        return orderSum;
    }

    public void setOrderSum(int orderSum) {

        this.orderSum = orderSum;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + orderSum;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Visitor visitor = (Visitor) o;

        if (id != visitor.id) {
            return false;
        }
        if (orderSum != visitor.orderSum) {
            return false;
        }
        return name != null ? name.equals(visitor.name) : visitor.name == null;
    }

    @Override
    public String toString() {

        return this.getClass().getSimpleName() + " [id=" + id + ", name=" + name + ", orderSum=" + orderSum + "]";
    }

}