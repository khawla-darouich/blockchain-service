package org.glsid3.blockchainservice.services;

import org.glsid3.blockchainservice.dto.BlockChainRequestDto;
import org.glsid3.blockchainservice.dto.BlockChainResponseDto;
import org.glsid3.blockchainservice.dto.BlockResponseDto;
import org.glsid3.blockchainservice.entities.Block;
import org.glsid3.blockchainservice.entities.BlockChain;
import org.glsid3.blockchainservice.mappers.IBlockChainMapper;
import org.glsid3.blockchainservice.repositories.IBlockChainRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class IBlockChainServiceImpl implements IBlockChainService {

    private IBlockChainRepository blockChainRepository;
    private IBlockChainMapper blockChainMapper;

    public IBlockChainServiceImpl(IBlockChainRepository blockChainRepository, IBlockChainMapper blockChainMapper) {
        this.blockChainRepository = blockChainRepository;
        this.blockChainMapper = blockChainMapper;
    }

    @Override
    public BlockChainResponseDto ajouter(BlockChainRequestDto blockChainRequestDto) {
        BlockChain blockChain=blockChainMapper.blockChainRequestDtoToBlockChain(blockChainRequestDto);
       // List<Block> blocks=new ArrayList<>();
        blockChain.setId(UUID.randomUUID().toString());
        blockChainRepository.save(blockChain);
       // blocks.add(new Block(null,blockChainRepository.findById(blockChain.getId()).get()));
        return blockChainMapper.blockChainToBlockChainResponseDto(blockChain);
    }
}
