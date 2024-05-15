package edu.ezip.ing1.pds.business.dto;

import java.util.LinkedHashSet;
import java.util.Set;

public class Stocks {

    private Set<Stock> stocks = new LinkedHashSet<Stock>();

    public Set<Stock> getStudents() {
        return stocks;
    }

    public void setStudents(Set<Stock> stocks) {
        this.stocks = stocks;
    }

    public final Stocks add (final Stock stock) {
        stocks.add(stock);
        return this;
    }

    @Override
    public String toString() {
        return "Students{" +
                "students=" + stocks +
                '}';
    }

}
