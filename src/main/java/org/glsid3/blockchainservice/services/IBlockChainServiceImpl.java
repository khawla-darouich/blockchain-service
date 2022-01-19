package org.glsid3.blockchainservice.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.glsid3.blockchainservice.dto.BlockChainRequestDto;
import org.glsid3.blockchainservice.dto.BlockChainResponseDto;
import org.glsid3.blockchainservice.dto.BlockRequestDto;
import org.glsid3.blockchainservice.entities.Block;
import org.glsid3.blockchainservice.entities.BlockChain;
import org.glsid3.blockchainservice.entities.Transaction;
import org.glsid3.blockchainservice.mappers.IBlockChainMapper;
import org.glsid3.blockchainservice.repositories.IBlockChainRepository;
import org.glsid3.blockchainservice.repositories.IBlockRepository;
import org.glsid3.blockchainservice.repositories.ITransactionRepository;
import org.glsid3.blockchainservice.xceptions.BlockChainInvalidException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
@Data
public class IBlockChainServiceImpl implements IBlockChainService {

    private IBlockChainRepository blockChainRepository;
    private IBlockChainMapper blockChainMapper;
    private IBlockService blockService;
    private IBlockRepository blockRepository;
    private ITransactionRepository transactionRepository;
    private List<Transaction> pendingTransactions;

    @Override
    public BlockChainResponseDto ajouter(BlockChainRequestDto blockChainRequestDto) {
        BlockChain blockChain=blockChainMapper.blockChainRequestDtoToBlockChain(blockChainRequestDto);
        blockChain.setId(UUID.randomUUID().toString());
        Block genesisBlock= new Block(UUID.randomUUID().toString(),new Date(),null,new String(new char[blockChain.getDifficulte()]).replace('\0','0'),0,new ArrayList<>());
        blockService.minerBlock(blockChain.getDifficulte(),genesisBlock);
        blockRepository.save(genesisBlock);
        blockChain.setBlocks(new ArrayList<>());
        blockChain.getBlocks().add(genesisBlock);
        blockChainRepository.save(blockChain);
        return blockChainMapper.blockChainToBlockChainResponseDto(blockChain);
    }

    @Override
    public void miner(String BlockChainId, BlockRequestDto blockRequestDto) throws BlockChainInvalidException {
        BlockChain blockChain=blockChainRepository.findById(BlockChainId).get();
        System.out.println(blockChain);
        if(!isValid(blockChain)) throw new BlockChainInvalidException("Impossible de miner les transactions : BlockChain Invalid");
        Block block=blockService.createBlock(blockRequestDto.getTransactions());
        block.setLastHash(getLastBlock(BlockChainId).getHash());
        blockService.minerBlock(blockChain.getDifficulte(),block);
        blockRepository.save(block);
        blockChain.getBlocks().add(block);
        blockChainRepository.save(blockChain);
        String transactionId=UUID.randomUUID().toString();
       // Transaction transaction=new Transaction(transactionId,new Date(),blockChain.getNom(),mineur,blockChain.getRecompense());
        //transaction.setId(UUID.randomUUID().toString());
        //transactionRepository.save(transaction);
        //pendingTransactions=new ArrayList<>();
        //pendingTransactions.add(transaction);
    }

    @Override
    public Block getLastBlock(String BlockChainId) {
        List<Block> blocks=blockChainRepository.findById(BlockChainId).get().getBlocks();
        return blocks.get(blocks.size()-1);
    }

    @Override
    public boolean isValid(BlockChain blockChain) {
        Block currentBlock;
        Block lastBlock;
        for(int i=1; i<blockChain.getBlocks().size();i++){
            System.out.println(blockChain.getBlocks().get(i));
            currentBlock=blockChain.getBlocks().get(i);
            lastBlock=blockChain.getBlocks().get(i-1);
            if(!currentBlock.getLastHash().equals(lastBlock.getHash())) {
                System.out.println("lastHash******************************************");
                return false;
            }
            if(!currentBlock.getHash().equals(blockService.calculHash(currentBlock))) {
                System.out.println("hash******************************************");
                return false;
            }
        }

        return true;
    }

    @Override
    public double solde(String address) {
        List<Transaction> transactions=transactionRepository.findByDestinationAddress(address);
        double solde=0;
        for (Transaction transaction:transactions)
            solde+=transaction.getMontant();
        return solde;
    }
}
