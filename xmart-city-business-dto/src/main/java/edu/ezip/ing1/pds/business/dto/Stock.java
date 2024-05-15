package edu.ezip.ing1.pds.business.dto;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Stock {
    private Integer medicament_id;
    private Integer quantite;
    private String date_expiration;
    private Integer seuil;
    private String nom;
    private String seuil_date;


    public Stock() {
    }


    public final Stock build(final ResultSet resultSet)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        setFieldsFromResulset(resultSet, "medicament_id", "quantite","date_expiration", "seuil", "nom", "seuil_date");
        return this;
    }


    public final PreparedStatement build(PreparedStatement preparedStatement)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        return buildPreparedStatement(preparedStatement, nom);
    }
    public Stock(Integer medicament_id, Integer quantite, String date_expiration, Integer seuil, String nom, String seuil_date) {
        this.medicament_id = medicament_id;
        this.quantite = quantite;
        this.date_expiration = date_expiration;
        this.seuil = seuil;
        this.nom = nom;
        this.seuil_date = seuil_date;
    }

    public Integer getMedicament_id() {
        return medicament_id;
    }
    public Integer getQuantite() {
        return quantite;
    }
    public String getDate_expiration() {
        return date_expiration;
    }
    public Integer getSeuil() {
        return seuil;
    }
    public String getNom() {
        return nom;
    }
    public String getSeuil_date() {
        return seuil_date;
    }
    public void setMedicament_id(Integer medicament_id) {
        this.medicament_id = medicament_id;
    }
    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
    public void setDate_expiration(String date_expiration) {
        this.date_expiration = date_expiration;
    }
    public void setSeuil(Integer seuil) {
        this.seuil = seuil;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setSeuil_date(String seuil_date) {
        this.seuil_date = seuil_date;
    }


    private void setFieldsFromResulset(final ResultSet resultSet, final String ... fieldNames )
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        for(final String fieldName : fieldNames ) {
            if(fieldName.equals("medicament_id") || fieldName.equals("quantite") || fieldName.equals("seuil") ){
                final Field field = this.getClass().getDeclaredField(fieldName);
                field.set(this, resultSet.getObject(fieldName, Integer.class));
            } else if (fieldName.equals("date_expiration") || fieldName.equals("seuil_date")){
                final Field field = this.getClass().getDeclaredField(fieldName);
                Timestamp tt = resultSet.getTimestamp(fieldName);
                String ttString = tt.toString();
                field.set(this, ttString);
            }else {
                final Field field = this.getClass().getDeclaredField(fieldName);
                field.set(this, resultSet.getObject(fieldName, String.class));
            }

        }
    }
    private final PreparedStatement buildPreparedStatement(PreparedStatement preparedStatement, final String ... fieldNames )
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        int ix = 0;

        preparedStatement.setInt(++ix, quantite);
        Timestamp tt = Timestamp.valueOf(date_expiration);
        preparedStatement.setTimestamp(++ix, tt);
        preparedStatement.setInt(++ix, seuil);


        Timestamp seuilDateTimestamp = Timestamp.valueOf(seuil_date);
        preparedStatement.setTimestamp(++ix, seuilDateTimestamp);


        for(final String fieldName : fieldNames ) {
            preparedStatement.setString(++ix, fieldName);
        }

        return preparedStatement;
    }

    @Override
    public String toString() {
        return "Student{" +
                "medicament_id='" + medicament_id  + '\'' +
                ", quantite='" + quantite + '\'' +
                ", date_expiration='" + date_expiration + '\'' +
                ", seuil='" + seuil + '\'' +
                ", nom='" + nom + '\'' +
                ", seuil_date='" + seuil_date + '\'' +
                '}';
    }
}
