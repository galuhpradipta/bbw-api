package net.galuhpradipta.bbwapi.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "clients")
public class Transaction extends Base {

    @Column(name = "transaction_number")
    private String transactionNumber;

    @Column(name = "reference_number")
    private String referenceNumber;

    private Double amount;

    private String note;
}
