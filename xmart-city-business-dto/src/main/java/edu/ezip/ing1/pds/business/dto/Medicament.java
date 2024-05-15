package edu.ezip.ing1.pds.business.dto;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Medicament {
    private Integer code_barre ;
    private String nom;
    private Integer categorie;
    private Integer id_profession;
    public Medicament (){}


    public final Medicament build(final ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        setFieldsFromResulset(resultSet, "code_barre", "nom","categorie","id_profession");

        return this;
    }

    public final PreparedStatement build(PreparedStatement preparedStatement)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        return buildPreparedStatement(preparedStatement,  nom);
    }

    public Medicament(Integer code_barre, String nom, Integer categorie, Integer id_profession) {
        this.code_barre = code_barre;
        this.nom = nom;
        this.categorie = categorie;
        this.id_profession = id_profession;
    };

    public Integer getCode_barre() {
        return code_barre;
    }
    public String getNom() {
        return nom;
    }
    public Integer getCategorie() {
        return categorie;
    }
    public Integer getId_profession() {
        return id_profession;
    }
    public void setCode_barre(Integer code_barre) {
        this.code_barre = code_barre;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setCategorie(Integer categorie) {
        this.categorie = categorie;
    }
    public void setId_profession(Integer id_profession) {
        this.id_profession = id_profession;
    }

    private void setFieldsFromResulset(final ResultSet resultSet, final String ...fieldNames )
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        for(final String fieldName :  fieldNames ) {
            if (fieldName.equals("code_barre") || fieldName.equals("categorie") || fieldName.equals("id_profession")){
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

        for(final String fieldName : fieldNames ) {
            preparedStatement.setString(++ix, fieldName);
        }

//àààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààà
        preparedStatement.setInt(++ix, categorie);
//àààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààà

//àààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààààà

        return preparedStatement;
    }

    public String toString() {
        return "Student{" +
                "code_barre='" + code_barre + '\'' +
                ", nom='" + nom + '\'' +
                ", categorie='" + categorie + '\'' +
                ", id_profession='" + id_profession + '\'' +
                '}';
    }
}
