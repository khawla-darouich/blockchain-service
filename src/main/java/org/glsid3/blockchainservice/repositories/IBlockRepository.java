package org.glsid3.blockchainservice.repositories;

import org.glsid3.blockchainservice.entities.Block;
import org.glsid3.blockchainservice.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBlockRepository extends JpaRepository<Block,String> {
    
}
