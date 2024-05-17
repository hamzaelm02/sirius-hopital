package edu.ezip.ing1.pds.business.dto;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Gestions {
    private Set<Gestion> students = new LinkedHashSet<Gestion>();

    public Set<Gestion> getStudents() {
        return students;
    }

    public void setStudents(Set<Gestion> students) {
        this.students = students;
    }

    public final Gestions add ( Gestion student) {
        students.add(student);
        return this;
    }

    @Override
    public String toString() {
        return "Gestions{" +
                "Gestions=" + students +
                '}';
    }

}
