package net.galuhpradipta.bbwapi.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionVO {

    @JsonProperty("transaction_number")
    private String transactionNumber;
}
