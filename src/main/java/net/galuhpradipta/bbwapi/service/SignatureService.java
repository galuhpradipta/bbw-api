package net.galuhpradipta.bbwapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import net.galuhpradipta.bbwapi.entity.Client;
import net.galuhpradipta.bbwapi.exception.BBWException;
import net.galuhpradipta.bbwapi.repository.ClientRepository;
import net.galuhpradipta.bbwapi.util.SignatureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

@Slf4j
@Service
public class SignatureService {

    @Autowired
    private SignatureUtil signatureUtil;

    @Autowired
    private ClientRepository clientRepository;

    public void validateSignature(String authToken, Object bodyRequest, String timestamp, String reqSignature, String clientID) {

        try {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;
            TemporalAccessor accessor = timeFormatter.parse(timestamp);
        } catch (DateTimeParseException e) {
            throw BBWException.generalError();
        }


        authToken = authToken.replace("Bearer ", "");

        log.info("object {}", bodyRequest);

        String jsonStr;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, false);
            ObjectWriter ow = objectMapper.writer();

            jsonStr = ow.writeValueAsString(bodyRequest);
        } catch (JsonProcessingException e) {
            throw BBWException.generalError();
        }



        String bodyHex = signatureUtil.generateHash(jsonStr);
        String stringToSign = String.format("%s:%s:%s", authToken, bodyHex, timestamp);

        Client client = clientRepository.findByClientId(clientID);
        if (client == null) throw BBWException.clientIDNotFound();

        String signature;
        try {
            signature = signatureUtil.encode(client.getClientKey(), stringToSign);
        } catch (Exception e) {
            throw BBWException.generalError();
        }

        if (!signature.equals(reqSignature)) throw BBWException.signatureNotValid();
    }
}
