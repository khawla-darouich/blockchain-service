package org.glsid3.blockchainservice.services;

import com.google.common.hash.Hashing;
import org.glsid3.blockchainservice.dto.BlockRequestDto;
import org.glsid3.blockchainservice.dto.BlockResponseDto;
import org.glsid3.blockchainservice.entities.Block;
import org.glsid3.blockchainservice.entities.Transaction;
import org.glsid3.blockchainservice.mappers.IBlockMapper;
import org.glsid3.blockchainservice.repositories.IBlockRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class IBlockServiceImpl implements IBlockService {
    private IBlockMapper blockMapper;
    private IBlockRepository blockRepository;

    public IBlockServiceImpl(IBlockMapper blockMapper, IBlockRepository blockRepository) {
        this.blockMapper = blockMapper;
        this.blockRepository = blockRepository;
    }

    @Override
    public Block createBlock(List<Transaction> transactions) {
        Block block=new Block();
        block.setTransactions(transactions);
        block.setCreationDate(new Date());
        block.setNonce(0);
        block.setId(UUID.randomUUID().toString());
        blockRepository.save(block);
        return block;

    }

    @Override
    public String calculHash(Block block) {

        String toHash=block.getLastHash()+block.getNonce()+block.getTransactions().hashCode();
        return  Hashing.sha256()
                .hashString(toHash, StandardCharsets.UTF_8)
                .toString();

    }

    @Override
    public void minerBlock(int difficulty,Block block) {
        String zeros=new String(new char[difficulty]).replace('\0','0');
        while(true){
            String hash=calculHash(block);
            block.setNonce(block.getNonce()+1);
            if(hash.substring(0,difficulty).equals(zeros)){
                block.setHash(hash);
                return ;
            }
        }

    }
}
