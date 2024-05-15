package edu.ezip.ing1.pds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.Profession;
import edu.ezip.ing1.pds.business.dto.Professions;
import edu.ezip.ing1.pds.business.dto.Students;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class SelectProfessionClientRequest extends ClientRequest<Object, Professions> {


    public SelectProfessionClientRequest(NetworkConfig networkConfig, int myBirthDate, Request request, Object info, byte[] bytes) throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Professions readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Professions professions = mapper.readValue(body, Professions.class);
        return professions;

    }
}