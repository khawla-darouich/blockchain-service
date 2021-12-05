package org.glsid3.blockchainservice.services;

import org.glsid3.blockchainservice.dto.BlockChainRequestDto;
import org.glsid3.blockchainservice.dto.BlockChainResponseDto;
import org.glsid3.blockchainservice.dto.BlockRequestDto;
import org.glsid3.blockchainservice.dto.BlockResponseDto;
import org.glsid3.blockchainservice.entities.Block;
import org.glsid3.blockchainservice.entities.BlockChain;

public interface IBlockChainService {
    BlockChainResponseDto ajouter(BlockChainRequestDto blockChainRequestDto);
    void miner(String BlockChainId,Block block,String mineur);
    Block getLastBlock(String BlockChainId);
    boolean isValid(String blockChainId);
}
