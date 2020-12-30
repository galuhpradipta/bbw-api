package net.galuhpradipta.bbwapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "clients")
public class Client extends Base {
    private String clientId;
    private String clientKey;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "client")
    private List<VirtualAccount> virtualAccounts = new ArrayList<>();

    public void addVirtualAccount(VirtualAccount virtualAccount) {
        virtualAccount.setClient(this);
        virtualAccounts.add(virtualAccount);
    }
}
