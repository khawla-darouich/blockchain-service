package org.glsid3.blockchainservice.services;

import org.glsid3.blockchainservice.dto.BlockRequestDto;
import org.glsid3.blockchainservice.dto.BlockResponseDto;
import org.glsid3.blockchainservice.entities.Block;

public interface IBlockService {
    BlockResponseDto createBlock(BlockRequestDto blockRequestDto);
    String calculHash(Block block);
    void minerBlock(Block block);
}
