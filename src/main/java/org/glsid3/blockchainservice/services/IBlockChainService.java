package org.glsid3.blockchainservice.services;

import org.glsid3.blockchainservice.dto.BlockChainRequestDto;
import org.glsid3.blockchainservice.dto.BlockChainResponseDto;
import org.glsid3.blockchainservice.dto.BlockRequestDto;
import org.glsid3.blockchainservice.entities.Block;
import org.glsid3.blockchainservice.entities.BlockChain;
import org.glsid3.blockchainservice.Exceptions.BlockChainInvalidException;

public interface IBlockChainService {
    BlockChainResponseDto ajouter(BlockChainRequestDto blockChainRequestDto);
    void miner(String BlockChainId,BlockRequestDto blockRequestDto) throws BlockChainInvalidException;
    Block getLastBlock(String BlockChainId);
    boolean isValid(BlockChain blockChain);
    double solde(String address);
}
