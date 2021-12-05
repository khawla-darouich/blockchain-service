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
   // @Autowired
    private IBlockService blockService;
    private IBlockRepository blockRepository;

    public IBlockServiceImpl(IBlockMapper blockMapper, IBlockRepository blockRepository) {
        this.blockMapper = blockMapper;
       // this.blockService = blockService;
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
        String zeros="";
        for(int i=0;i<difficulty;i++)
            zeros.concat("0");
        while(true){
            String hash=calculHash(block);
            block.setNonce(block.getNonce()+1);
            if(hash.substring(difficulty).equals(zeros)){
                block.setHash(hash);
                return ;
            }
        }

    }
}
