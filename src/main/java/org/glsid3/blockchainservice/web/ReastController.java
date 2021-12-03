package org.glsid3.blockchainservice.web;

import org.glsid3.blockchainservice.dto.BlockRequestDto;
import org.glsid3.blockchainservice.dto.BlockResponseDto;
import org.glsid3.blockchainservice.services.IBlockService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReastController {
    private IBlockService blockService;

    public ReastController(IBlockService blockService) {
        this.blockService = blockService;
    }
    @PostMapping("blocks")
    public BlockResponseDto create(@RequestBody BlockRequestDto blockRequestDto){
        return blockService.createBlock(blockRequestDto);
    }
}
