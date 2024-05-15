package edu.ezip.ing1.pds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.ezip.ing1.pds.business.dto.Student;
import edu.ezip.ing1.pds.business.dto.Students;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;

public class MainInsertClient { 

    private final static String LoggingLabel = "I n s e r t e r - C l i e n t";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String studentsToBeInserted = "students-to-be-inserted.yaml";
    private final static String networkConfigFile = "network.yaml";
    private static final String threadName = "inserter-client";
    private static final String requestOrder = "INSERT_EMPLOYEE";
    private static final Deque<ClientRequest> clientRequests = new ArrayDeque<ClientRequest>();

    public static void addEmployee(Students students) throws IOException, InterruptedException, SQLException {


        final NetworkConfig networkConfig =  ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        logger.debug("Load Network config file : {}", networkConfig.toString());

        int birthdate = 0;
        for(final Student guy : students.getStudents()) {
            final ObjectMapper objectMapper = new ObjectMapper();
            final String jsonifiedGuy = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(guy);
            logger.trace("Student with its JSON face : {}", jsonifiedGuy);
            final String requestId = UUID.randomUUID().toString();
            final Request request = new Request();
            request.setRequestId(requestId);
            request.setRequestOrder(requestOrder);
            request.setRequestContent(jsonifiedGuy);
            objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
            final byte []  requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);

            final InsertStudentsClientRequest clientRequest = new InsertStudentsClientRequest (
                                                                        networkConfig,
                                                                        birthdate++, request, guy, requestBytes);
           System.out.println("0000000000000000000000000000000000000000000000000000000000");
                                                                        clientRequests.push(clientRequest);
        }

        while (!clientRequests.isEmpty()) {
            final  ClientRequest clientRequest = clientRequests.pop();
            clientRequest.join();
            final Student guy = (Student)clientRequest.getInfo();
            logger.debug("Thread {} complete :  {} {} {} {} {} {} {} {}  --> {}",
                                    clientRequest.getThreadName(),
                                     guy.getNom(), guy.getPrenom(), guy.getAdresse(), guy.getEmail(), guy.getBirthdate(), guy.getTaille(), guy.getStartingdate(),guy.getId_profession(),
                                    clientRequest.getResult());
                                    System.out.println("00000000000000000000000000000000000000000000000000000000000000000000");
        }
       System.out.println("yuyuyuyuyuuyuyu "+clientRequests);
    }
}
