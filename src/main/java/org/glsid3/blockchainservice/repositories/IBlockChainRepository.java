package org.glsid3.blockchainservice.repositories;

import org.glsid3.blockchainservice.entities.BlockChain;
import org.glsid3.blockchainservice.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface IBlockChainRepository extends JpaRepository<BlockChain,String> {
    
}
