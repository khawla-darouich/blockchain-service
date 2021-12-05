package org.glsid3.blockchainservice.repositories;

import org.glsid3.blockchainservice.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITransactionRepository extends JpaRepository<Transaction,String> {
    List<Transaction> findByDestinationAddress(String address);
}
