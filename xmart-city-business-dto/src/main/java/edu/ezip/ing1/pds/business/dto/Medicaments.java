package edu.ezip.ing1.pds.business.dto;

import java.util.LinkedHashSet;
import java.util.Set;

public class Medicaments {
    private Set<Medicament> medicaments = new LinkedHashSet<Medicament>();

    public Set<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(Set<Medicament> medicaments) {
        this.medicaments = medicaments;
    }

    public final Medicaments add (final Medicament medicament) {
        medicaments.add(medicament);
        return this;
    }

    @Override
    public String toString() {
        return "Medicaments{" +
                "medicaments=" + medicaments +
                '}';
    }
}
