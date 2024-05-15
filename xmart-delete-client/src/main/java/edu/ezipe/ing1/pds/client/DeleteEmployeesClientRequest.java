package edu.ezipe.ing1.pds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.Student;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;
import java.util.Map;

public class DeleteEmployeesClientRequest extends ClientRequest<Student,String> {

    public DeleteEmployeesClientRequest(NetworkConfig networkConfig, int myBirthDate, Request request, Student info, byte[] bytes) throws  IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }
    @Override
    public String readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Map<String, Integer> studentIdMap = mapper.readValue(body, Map.class);
        final String result  = studentIdMap.get("employee_id").toString();
        return result;
    }
}
