package org.glsid3.blockchainservice.repositories;

import org.glsid3.blockchainservice.entities.BlockChain;
import org.glsid3.blockchainservice.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBlockChainRepository extends JpaRepository<BlockChain,String> {
    
}
