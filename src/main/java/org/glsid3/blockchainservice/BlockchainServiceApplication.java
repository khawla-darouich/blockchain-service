package org.glsid3.blockchainservice;

import org.glsid3.blockchainservice.dto.BlockChainRequestDto;
import org.glsid3.blockchainservice.services.BlockChainApplicationService;
import org.glsid3.blockchainservice.services.IBlockChainService;
import org.glsid3.blockchainservice.services.IBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class BlockchainServiceApplication implements CommandLineRunner {

    @Autowired
    BlockChainApplicationService blockChainApplicationService;
    public static void main(String[] args) {
        SpringApplication.run(BlockchainServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception{
        blockChainApplicationService.createBlockChain();
        blockChainApplicationService.createTransactions();
        blockChainApplicationService.minerBlock();
    }
}
