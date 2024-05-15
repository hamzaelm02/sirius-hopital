package edu.ezip.ing1.pds.business.dto;

import java.util.LinkedHashSet;
import java.util.Set;

public class Professions {
    private Set<Profession> professions = new LinkedHashSet<Profession>();

    public Set<Profession> getProfessions() {
        return professions;
    }

    public void setProfession(Set<Profession> professions) {
        this.professions = professions;
    }

    public final Professions add (final Profession profession) {
        professions.add(profession);
        return this;
    }

    @Override
    public String toString() {
        return "Professions{" +
                "Professions=" + professions +
                '}';
    }
}
