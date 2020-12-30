package net.galuhpradipta.bbwapi.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestPaymentVAVO {


    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("reference_number")
    private String referenceNumber;

    @JsonProperty("virtual_account")
    private String virtualAccount;

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("note")
    private String note;
}
