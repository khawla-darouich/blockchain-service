package org.glsid3.blockchainservice.services;

import org.glsid3.blockchainservice.dto.BlockRequestDto;
import org.glsid3.blockchainservice.dto.BlockResponseDto;
import org.glsid3.blockchainservice.entities.Block;
import org.glsid3.blockchainservice.entities.Transaction;

import java.util.List;

public interface IBlockService {
    Block createBlock(List<Transaction> transactions);
    String calculHash(Block block);
    void minerBlock(int difficulty, Block block);
}
