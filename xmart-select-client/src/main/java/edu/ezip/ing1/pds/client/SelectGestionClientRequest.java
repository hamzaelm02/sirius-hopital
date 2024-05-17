package edu.ezip.ing1.pds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.Gestions;
import edu.ezip.ing1.pds.business.dto.Stocks;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class SelectGestionClientRequest extends ClientRequest<Object, Gestions> {
    public SelectGestionClientRequest(NetworkConfig networkConfig, int myBirthDate, Request request, Object info, byte[] bytes) throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Gestions readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Gestions gestions = mapper.readValue(body, Gestions.class);
        return gestions;
    }
}
