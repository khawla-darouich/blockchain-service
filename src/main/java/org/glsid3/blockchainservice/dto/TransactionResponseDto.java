package org.glsid3.blockchainservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.glsid3.blockchainservice.entities.Block;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {
    private Date date;
    private String sourceAddress;
    private String destinationAddress;
    private double montant;

}
