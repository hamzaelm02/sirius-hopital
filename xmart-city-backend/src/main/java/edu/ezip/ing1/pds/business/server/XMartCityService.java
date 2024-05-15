package edu.ezip.ing1.pds.business.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.*;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.commons.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMartCityService {
    private final static String LoggingLabel = "B u s i n e s s - S e r v e r";
    private final Logger logger = LoggerFactory.getLogger(LoggingLabel);

    private enum Queries {
        SELECT_ALL_EMPLOYEES("SELECT t.id_employee, t.nom, t.prenom, t.adresse, t.email, t.birthdate, t.taille, t.startingdate, t.id_profession FROM \"public\".employee AS t"),
        SELECT_ALL_PROFESSIONS("SELECT t.id_profession, t.nom_profession FROM \"public\".profession t"),
        INSERT_EMPLOYEE("INSERT into \"public\".employee (\"nom\", \"prenom\", \"adresse\", \"email\", \"birthdate\", \"taille\", \"startingdate\",\"id_profession\") values (?, ?, ?, ?, ?, ?, ?, ?)"),
        DELETE_EMPLOYEE("DELETE FROM public.employee WHERE id_employee = ? "),
        SELECT_PROFESSIONS_BY_DOMAINE("SELECT id_profession FROM public.profession  WHERE nom_profession = ?"),
        SELECT_MEDICAMENTS_BY_DOMAINE("SELECT t.code_barre, t.nom, t.categorie, t.id_profession FROM \"public\".medicament t WHERE id_profession = ?"),
        UPDATE_EMPLOYEE("UPDATE public.employee SET email = ?, adresse = ? WHERE id_employee = ?"),
        SELECT_ALL_STOCKS("SELECT t.medicament_id, t.quantite, t.date_expiration, t.seuil, t.nom, t.seuil_date FROM \"public\".stock t");
        private final String query;

        private Queries(final String query) {
            this.query = query;
        }
    }

    public static XMartCityService inst = null;

    public static final XMartCityService getInstance() {
        if (inst == null) {
            inst = new XMartCityService();
        }
        return inst;
    }

    private XMartCityService() {

    }

    public final Response dispatch(final Request request, final Connection connection)
            throws InvocationTargetException, IllegalAccessException, SQLException, NoSuchFieldException,
            IOException {
        try {
            if (request.getRequestOrder().trim().equals("SELECT_ALL_EMPLOYEES")) {
                System.out.println("SELECT EMPLOYEES REQUEST");

                final PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_ALL_EMPLOYEES.query);
                ResultSet resultat = preparedStatement.executeQuery();
                Students listStudents = new Students();

                while (resultat.next()) {
                    final Student student = new Student().build(resultat);
                    listStudents.add(student);
                }
                ObjectMapper mapper = new ObjectMapper();
                System.out.println("yessssssssssssssssssssssssssssssssssss");

                System.out.println("====================================================");
                System.out.println(listStudents);
                System.out.println("====================================================");


                return new Response(request.getRequestId(), mapper.writeValueAsString(listStudents));

            }
            else if (request.getRequestOrder().trim().equals("INSERT_EMPLOYEE")) {
                final PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_EMPLOYEE.query,Statement.RETURN_GENERATED_KEYS);
                ObjectMapper mapper = new ObjectMapper();
                Student student = mapper.readValue(request.getRequestBody(), Student.class);
                int line =student.build(preparedStatement).executeUpdate();
                Response response = new Response();

                response.setResponseBody("{\"employee_id\": " + line + "}");
                response.setRequestId(request.getRequestId());
                return response;
            }
            else if (request.getRequestOrder().trim().equals("SELECT_ALL_PROFESSIONS")){
                System.out.println("Select Medication request");
                final PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_ALL_PROFESSIONS.query);
                ResultSet resultat = preparedStatement.executeQuery();
                Professions profList = new Professions();
                while (resultat.next()) {
                    final Profession profession = new Profession().build(resultat);
                    profList.add(profession);
                }
                ObjectMapper mapper = new ObjectMapper();
                return new Response(request.getRequestId(), mapper.writeValueAsString(profList));
            }
            else if (request.getRequestOrder().trim().equals("DELETE_EMPLOYEE")){
                final PreparedStatement preparedStatement = connection.prepareStatement(Queries.DELETE_EMPLOYEE.query);
                ObjectMapper mapper = new ObjectMapper();
                Student student = mapper.readValue(request.getRequestBody(), Student.class);
                preparedStatement.setInt(1, student.getId_employee());
                preparedStatement.executeUpdate();

                return  new Response(request.getRequestId(),mapper.writeValueAsString(student));

            }
            else if(request.getRequestOrder().trim().equals("SELECT_PROFESSIONS_BY_DOMAINE")){
                final PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_PROFESSIONS_BY_DOMAINE.query);
                Profession prof = new ObjectMapper().readValue(request.getRequestBody(), Profession.class);
                System.out.println("000000000000 " +prof);
                preparedStatement.setString(1, prof.getNom_profession());

                ResultSet resultat = preparedStatement.executeQuery();

                int idP = 0;
                while (resultat.next()) {
                    idP = resultat.getInt("id_profession");
                    System.out.println("0000000000000 " + idP);
                  }
               ObjectMapper mapper = new ObjectMapper();
                return new Response(request.getRequestId(), mapper.writeValueAsString(idP));

            }
            else if(request.getRequestOrder().trim().equals("SELECT_MEDICAMENTS_BY_DOMAINE")){
                PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_MEDICAMENTS_BY_DOMAINE.query);
                ObjectMapper mapper = new ObjectMapper();
                Medicament medicament = mapper.readValue(request.getRequestBody(),Medicament.class);
                preparedStatement.setInt(1, medicament.getId_profession());

                ResultSet resultSet = preparedStatement.executeQuery();
                Medicaments lMedicaments = new Medicaments();
                while(resultSet.next()){
                    medicament = new Medicament().build(resultSet);
                    lMedicaments.add(medicament);
                }
                Response response = new Response(request.getRequestId(),mapper.writeValueAsString(lMedicaments));
                return response;

            }else if (request.getRequestOrder().trim().equals("UPDATE_EMPLOYEE")){

                PreparedStatement preparedStatement = connection.prepareStatement(Queries.UPDATE_EMPLOYEE.query);
               Student student = new ObjectMapper().readValue(request.getRequestBody(),Student.class);
                preparedStatement.setString(1,student.getEmail());
                preparedStatement.setString(2, student.getAdresse());
                System.out.println("J'ai sauté cette ligne ");
                preparedStatement.setInt(3,  student.getId_employee());
                System.out.println("Je me trouvé dans cette ligne");

                int row = preparedStatement.executeUpdate();
                if(row>0){
                    System.out.println("Update successful. " + row + " row(s) affected.");
                } else {
                    System.out.println("Update failed. No rows affected.");
                }
            } else if (request.getRequestOrder().trim().equals("SELECT_ALL_STOCKS")) {
                PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_ALL_STOCKS.query);
                ResultSet resultSet = preparedStatement.executeQuery();
                Stocks listStocks = new Stocks();

                while(resultSet.next()){
                    final Stock stock = new Stock().build(resultSet);
                    listStocks.add(stock);
                }
                ObjectMapper mapper = new ObjectMapper();
                return new Response(request.getRequestId(), mapper.writeValueAsString(listStocks));

            }else {
                System.out.println("################################################################");
                System.out.println("Je n'ai rien executé ");
                System.out.println("################################################################");

                return null;
            }

        } catch (Exception e) {
            System.out.println("oooooooooooooooooooooooooooooo");
            e.printStackTrace();
            System.out.println("oooooooooooooooooooooooooooooo");

        }
        System.out.println("################################################################");
        System.out.println("Je n'ai toujours rien executé ");
        System.out.println("################################################################");
        return null;

    }

}
