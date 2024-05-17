package edu.ezip.ing1.pds.business.dto;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Gestion {
    private Integer medicament_id;
    private String nom_medicament;
    private String description;
    private String effet_secondaire;
    private Integer id_similaire;


    public Gestion() {
    }
    public final Gestion build(final ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        setFieldsFromResultSet(resultSet, "medicament_id", "nom_medicament", "description", "effet_secondaire", "id_similaire");
        return this;
    }

    public final PreparedStatement build(PreparedStatement preparedStatement)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        return buildPreparedStatement(preparedStatement, nom_medicament, description, effet_secondaire );
    }


    public Gestion(Integer medicament_id,String nom_medicament, String description, String effet_secondaire ,Integer id_similaire) {
        this.medicament_id = medicament_id;
        this.nom_medicament = nom_medicament;
        this.description = description;
        this.effet_secondaire = effet_secondaire;
        this.id_similaire = id_similaire ;
    }

    public Integer getMedicament_id() {
        return medicament_id;
    }

    public String getNom_medicament() {
        return nom_medicament;
    }

    public String getDescription() {
        return description;
    }

    public String getEffet_secondaire() {
        return effet_secondaire;
    }
    public Integer getId_Similaire() {
        return id_similaire;
    }




    public void setMedicament_id(Integer medicament_id) {
        this.medicament_id = medicament_id;
    }

    public void setNom_medicament(String nom_medicament) {
        this.nom_medicament = nom_medicament;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEffet_secondaire(String effet_secondaire) {
        this.effet_secondaire = effet_secondaire;
    }
    public void setId_Similaire(Integer id_similaire) {
        this.id_similaire = id_similaire;
    }


    private void setFieldsFromResultSet(final ResultSet resultSet, final String... fieldNames)
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        for(final String fieldName : fieldNames ) {
            if(fieldName.equals("medicament_id") || fieldName.equals("id_similaire")){
                final Field field = this.getClass().getDeclaredField(fieldName);
                field.set(this, resultSet.getObject(fieldName, Integer.class));}
            else {
                final Field field = this.getClass().getDeclaredField(fieldName);
                field.set(this, resultSet.getObject(fieldName, String.class));
            }

        }
    }
    private final PreparedStatement buildPreparedStatement(PreparedStatement preparedStatement, final String ... fieldNames )
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        int ix = 0;

        for(final String fieldName : fieldNames ) {
            preparedStatement.setString(++ix, fieldName);
        }

        return preparedStatement;
    }

    @Override
    public String toString() {
        return "Student{" +
                "medicament_id='" + medicament_id  + '\'' +
                ", nom_medicament='" + nom_medicament + '\'' +
                ", description='" + description + '\'' +
                ", effet_secondaire='" + effet_secondaire + '\'' +
                ", id_Similaire='" + id_similaire + '\'' +

                '}';
    }

    private void setFieldsFromResultSet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}
