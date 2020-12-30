package net.galuhpradipta.bbwapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "auth_tokens")
public class AuthToken extends Base {


    @Column(name = "auth_token", nullable = false)
    private String authToken;

    @Column(name = "expired_at", nullable = false)
    private Date expiredAt;
}
