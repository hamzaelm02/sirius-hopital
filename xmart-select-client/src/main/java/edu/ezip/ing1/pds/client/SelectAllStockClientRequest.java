package edu.ezip.ing1.pds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.Stocks;
import edu.ezip.ing1.pds.business.dto.Students;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class SelectAllStockClientRequest extends ClientRequest<Object, Stocks> {
    public SelectAllStockClientRequest(NetworkConfig networkConfig, int myBirthDate, Request request, Object info, byte[] bytes) throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Stocks readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Stocks stocks = mapper.readValue(body, Stocks.class);
        return stocks;
    }
}
