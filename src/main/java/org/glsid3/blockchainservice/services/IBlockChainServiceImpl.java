package org.glsid3.blockchainservice.services;

import org.glsid3.blockchainservice.dto.BlockChainRequestDto;
import org.glsid3.blockchainservice.dto.BlockChainResponseDto;
import org.glsid3.blockchainservice.dto.BlockResponseDto;
import org.glsid3.blockchainservice.entities.Block;
import org.glsid3.blockchainservice.entities.BlockChain;
import org.glsid3.blockchainservice.entities.Transaction;
import org.glsid3.blockchainservice.mappers.IBlockChainMapper;
import org.glsid3.blockchainservice.repositories.IBlockChainRepository;
import org.glsid3.blockchainservice.repositories.IBlockRepository;
import org.glsid3.blockchainservice.repositories.ITransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class IBlockChainServiceImpl implements IBlockChainService {

    private IBlockChainRepository blockChainRepository;
    private IBlockChainMapper blockChainMapper;
    private IBlockService blockService;
    private IBlockRepository blockRepository;
    private ITransactionRepository transactionRepository;
    public IBlockChainServiceImpl(IBlockChainRepository blockChainRepository, IBlockChainMapper blockChainMapper, IBlockService blockService, IBlockRepository blockRepository, ITransactionRepository transactionRepository) {
        this.blockChainRepository = blockChainRepository;
        this.blockChainMapper = blockChainMapper;
        this.blockService = blockService;
        this.blockRepository = blockRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public BlockChainResponseDto ajouter(BlockChainRequestDto blockChainRequestDto) {
        BlockChain blockChain=blockChainMapper.blockChainRequestDtoToBlockChain(blockChainRequestDto);
        blockChain.setId(UUID.randomUUID().toString());
        Block genesisBlock= new Block(UUID.randomUUID().toString(),new Date(),null,new String(new char[blockChain.getDifficulte()]).replace('\0','0'),0,null);
        blockService.minerBlock(blockChain.getDifficulte(),genesisBlock);
        blockRepository.save(genesisBlock);
        blockChain.getBlocks().add(genesisBlock);
        blockChainRepository.save(blockChain);
        return blockChainMapper.blockChainToBlockChainResponseDto(blockChain);
    }

    @Override
    public void miner(String BlockChainId,Block block, String mineur) {
        BlockChain blockChain=blockChainRepository.findById(BlockChainId).get();
        block.setLastHash(getLastBlock(BlockChainId).getHash());
        String transactionId=UUID.randomUUID().toString();
        Transaction transaction=new Transaction(transactionId,new Date(),null,mineur,blockChain.getRecompense());
        transactionRepository.save(transaction);
        block.getTransactions().add(transaction);
    }

    @Override
    public Block getLastBlock(String BlockChainId) {
        List<Block> blocks=blockChainRepository.findById(BlockChainId).get().getBlocks();
        return blocks.get(blocks.size()-1);
    }

    @Override
    public boolean isValid(String blockChainId) {
        BlockChain blockChain=blockChainRepository.findById(blockChainId).get();
        Block currentBlock;
        Block lastBlock;
        for(int i=1; i<blockChain.getBlocks().size();i++){
            currentBlock=blockChain.getBlocks().get(i);
            lastBlock=blockChain.getBlocks().get(i-1);
            if(!currentBlock.getLastHash().equals(lastBlock.getHash()))
                return false;
            if(!currentBlock.getHash().equals(blockService.calculHash(currentBlock)))
                return false;
        }

        return true;
    }
}
