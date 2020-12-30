package net.galuhpradipta.bbwapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "virtual_accounts")
public class VirtualAccount extends Base {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_number")
    private String accountNumber;


}
