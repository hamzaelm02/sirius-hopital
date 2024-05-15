package edu.ezip.ing1.pds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.Profession;
import edu.ezip.ing1.pds.business.dto.Professions;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class SelectAllProfessionClientRequest extends ClientRequest<Object, Integer> {
    public SelectAllProfessionClientRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Object info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Integer readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Integer idProf = mapper.readValue(body, Integer.class);
        return idProf;
    }
}

