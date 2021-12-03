package org.glsid3.blockchainservice.mappers;

import org.glsid3.blockchainservice.dto.BlockRequestDto;
import org.glsid3.blockchainservice.dto.BlockResponseDto;
import org.glsid3.blockchainservice.dto.TransactionRequestDto;
import org.glsid3.blockchainservice.dto.TransactionResponseDto;
import org.glsid3.blockchainservice.entities.Block;
import org.glsid3.blockchainservice.entities.Transaction;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface IBlockMapper {
    BlockResponseDto blockToBlockResponseDto(Block block);
    Block blockRequestDtoToBlock(BlockRequestDto blockRequestDto);
}