package org.glsid3.blockchainservice;

import org.glsid3.blockchainservice.dto.BlockChainRequestDto;
import org.glsid3.blockchainservice.services.IBlockChainService;
import org.glsid3.blockchainservice.services.IBlockService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class BlockchainServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockchainServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(IBlockChainService blockService)
    {
        return args -> {
            System.out.println("heeey : ");
            blockService.ajouter(new BlockChainRequestDto("blockC1",1 ,200.0,new ArrayList<>()));
            System.out.println("heeey : ");
        };
    }
}
