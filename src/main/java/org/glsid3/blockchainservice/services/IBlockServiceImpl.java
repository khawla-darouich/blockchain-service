package org.glsid3.blockchainservice.services;

import com.google.common.hash.Hashing;
import org.glsid3.blockchainservice.dto.BlockRequestDto;
import org.glsid3.blockchainservice.dto.BlockResponseDto;
import org.glsid3.blockchainservice.entities.Block;
import org.glsid3.blockchainservice.mappers.IBlockMapper;
import org.glsid3.blockchainservice.repositories.IBlockRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
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
    public BlockResponseDto createBlock(BlockRequestDto blockRequestDto) {
        Block block=blockMapper.blockRequestDtoToBlock(blockRequestDto);
        block.setCreationDate(new Date());
        block.setId(UUID.randomUUID().toString());
        blockRepository.save(block);
        return blockMapper.blockToBlockResponseDto(block);
    }

    @Override
    public String calculHash(Block block) {

        String toHash=block.getLastHash()+block.getNonce()+block.getCreationDate()+block.getTransactions().hashCode();
        return  Hashing.sha256()
                .hashString(toHash, StandardCharsets.UTF_8)
                .toString();

    }

    @Override
    public void minerBlock(int difficulty,Block block) {
        String zeros=new String(new char[difficulty]).replace('\0','0');

        System.out.println("before"+zeros);
        while(true){
            String hash=calculHash(block);
          // System.out.println(hash.substring(0,difficulty));
            block.setNonce(block.getNonce()+1);
            if(hash.substring(0,difficulty).equals(zeros)){
                System.out.println("if statement");
                block.setHash(hash);
                return ;
            }
        }

    }
}
