package org.glsid3.blockchainservice.mappers;

import org.glsid3.blockchainservice.dto.BlockChainRequestDto;
import org.glsid3.blockchainservice.dto.BlockChainResponseDto;
import org.glsid3.blockchainservice.dto.BlockRequestDto;
import org.glsid3.blockchainservice.dto.BlockResponseDto;
import org.glsid3.blockchainservice.entities.Block;
import org.glsid3.blockchainservice.entities.BlockChain;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface IBlockChainMapper {
    BlockChainResponseDto blockChainToBlockChainResponseDto(BlockChain blockChain);
    BlockChain blockChainRequestDtoToBlockChain(BlockChainRequestDto blockChainRequestDto);
}