package edu.ezip.ing1.pds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.Medicaments;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class SelectAllMedicamentClientRequest extends ClientRequest<Object, Medicaments> {

    public SelectAllMedicamentClientRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Object info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Medicaments readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Medicaments medicaments = mapper.readValue(body, Medicaments.class);
        return medicaments;
    }

}
