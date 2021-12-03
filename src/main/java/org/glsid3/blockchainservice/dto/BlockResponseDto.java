package org.glsid3.blockchainservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.glsid3.blockchainservice.entities.BlockChain;
import org.glsid3.blockchainservice.entities.Transaction;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockResponseDto {
    private Date creationDate;
    private String hash;
    private String lastHash;
    private int nonce;
    private Collection<Transaction> transactions;
   // private BlockChain blockchain;
}
