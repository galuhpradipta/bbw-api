package net.galuhpradipta.bbwapi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseVO {
    private String rc;
    private String message;
    private Object data;
}
