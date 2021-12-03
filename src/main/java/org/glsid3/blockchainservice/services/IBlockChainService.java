package org.glsid3.blockchainservice.services;

import org.glsid3.blockchainservice.dto.BlockChainRequestDto;
import org.glsid3.blockchainservice.dto.BlockChainResponseDto;
import org.glsid3.blockchainservice.dto.BlockRequestDto;
import org.glsid3.blockchainservice.dto.BlockResponseDto;
import org.glsid3.blockchainservice.entities.Block;

public interface IBlockChainService {
    BlockChainResponseDto ajouter(BlockChainRequestDto blockChainRequestDto);
}
