package org.glsid3.blockchainservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Block {
    @Id
    private String id;
    private Date creationDate;
    private String hash;
    private String lastHash;
    private int nonce;
    @OneToMany
    private List<Transaction> transactions;
    @ManyToOne
    private BlockChain blockchain;
}
