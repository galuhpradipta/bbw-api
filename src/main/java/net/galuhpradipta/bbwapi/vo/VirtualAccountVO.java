package net.galuhpradipta.bbwapi.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VirtualAccountVO {

    @JsonProperty("account_name")
    private String accountName;
}
