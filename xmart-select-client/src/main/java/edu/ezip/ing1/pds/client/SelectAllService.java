package edu.ezip.ing1.pds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.ezip.ing1.pds.business.dto.Stock;
import edu.ezip.ing1.pds.business.dto.Stocks;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;
import java.util.UUID;

public class SelectAllService {
    private final static String networkConfigFile = "network.yaml";
    private static final String requestOrder = "SELECT_ALL_STOCKS";
    private static final Deque<ClientRequest> clientRequests = new ArrayDeque<>();

    public static Set<Stock> getAllMedic() throws IOException, InterruptedException, SQLException {
        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        int birthdate = 0;
        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestOrder(requestOrder);
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte[] requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        final SelectAllStockClientRequest clientRequest = new SelectAllStockClientRequest(
                networkConfig,
                birthdate++, request, null, requestBytes);
        clientRequests.push(clientRequest);

        Set<Stock> allStocks = null;

        while (!clientRequests.isEmpty()) {
            final ClientRequest joinedClientRequest = clientRequests.pop();
            joinedClientRequest.join();
            final Stocks stocks = (Stocks) joinedClientRequest.getResult();

            allStocks = stocks.getStudents();
        }
        return allStocks;
    }
}