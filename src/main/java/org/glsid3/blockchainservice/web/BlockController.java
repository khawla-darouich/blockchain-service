package org.glsid3.blockchainservice.web;

import org.glsid3.blockchainservice.dto.BlockChainRequestDto;
import org.glsid3.blockchainservice.dto.BlockChainResponseDto;
import org.glsid3.blockchainservice.dto.BlockRequestDto;
import org.glsid3.blockchainservice.entities.Block;
import org.glsid3.blockchainservice.entities.BlockChain;
import org.glsid3.blockchainservice.repositories.IBlockChainRepository;
import org.glsid3.blockchainservice.services.IBlockChainService;
import org.glsid3.blockchainservice.services.IBlockService;
import org.glsid3.blockchainservice.Exceptions.BlockChainInvalidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlockController {
    private IBlockChainRepository blockChainRepository;
    private IBlockService blockService;
    private IBlockChainService blockChainService;
    public BlockController(IBlockChainRepository blockChainRepository, IBlockService blockService, IBlockChainService blockChainService) {
        this.blockChainRepository = blockChainRepository;
        this.blockService = blockService;
        this.blockChainService = blockChainService;
    }
    @PostMapping("blocks")
    public Block create(@RequestBody BlockRequestDto blockRequestDto){
        return blockService.createBlock(blockRequestDto.getTransactions());
    }

    @GetMapping("blockchains")
    public List<BlockChain> get(){
        return blockChainRepository.findAll();
    }

    @PostMapping("blockchains")
    public BlockChainResponseDto add(@RequestBody BlockChainRequestDto blockChainRequestDto){
         return blockChainService.ajouter(blockChainRequestDto);
    }

    @PutMapping("blockchains/miner/{id}")
    public void miner(@RequestBody BlockRequestDto blockRequestDto, @PathVariable String id ) throws BlockChainInvalidException {
        blockChainService.miner(id,blockRequestDto);
    }

}
