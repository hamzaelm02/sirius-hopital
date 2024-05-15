package edu.ezip.ing1.pds.business.dto;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Profession {
    private Integer id_profession ;
    private String nom_profession;

    public Profession (){}


    public final Profession build(final ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        setFieldsFromResulset(resultSet, "id_profession", "nom_profession");

        return this;
    }

    public final PreparedStatement build(PreparedStatement preparedStatement)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        return buildPreparedStatement(preparedStatement,  nom_profession);
    }

    public Profession(Integer id_profession, String nom_profession) {
        this.id_profession = id_profession;
        this.nom_profession = nom_profession;
    };

    public Integer getId_profession() {
        return id_profession;
    }

    public String getNom_profession() {
        return nom_profession;
    }

    public void setId_profession(Integer id_profession) {
        this.id_profession = id_profession;
    }

    public void setNom_profession(String nom_profession) {
        this.nom_profession = nom_profession;
    }

    private void setFieldsFromResulset(final ResultSet resultSet, final String ...fieldNames )
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        for(final String fieldName :  fieldNames ) {
            if (fieldName.equals("id_profession")){
                final Field field = this.getClass().getDeclaredField(fieldName);
                field.set(this, resultSet.getObject(fieldName, Integer.class));
            }  else {
                final Field field = this.getClass().getDeclaredField(fieldName);
                field.set(this, resultSet.getObject(fieldName, String.class));
            }

        }
    }

    private final PreparedStatement buildPreparedStatement(PreparedStatement preparedStatement, final String ...fieldNames )
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        int ix = 0;
        preparedStatement.setInt(++ix, id_profession);
        for(final String fieldName : fieldNames ) {
            preparedStatement.setString(++ix, fieldName);
        }



        return preparedStatement;
    }

    public String toString() {
        return "Student{" +
                "id_profession='" + id_profession + '\'' +
                "nom_profession='" + nom_profession + '\'' +

                '}';
    }

}
