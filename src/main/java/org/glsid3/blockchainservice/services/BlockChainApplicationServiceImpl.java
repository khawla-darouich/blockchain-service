package org.glsid3.blockchainservice.services;

import lombok.AllArgsConstructor;
import org.glsid3.blockchainservice.dto.BlockChainRequestDto;
import org.glsid3.blockchainservice.entities.Transaction;
import org.glsid3.blockchainservice.repositories.IBlockChainRepository;
import org.glsid3.blockchainservice.repositories.TransactionRepository;
import org.glsid3.blockchainservice.xceptions.BlockChainInvalidException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class BlockChainApplicationServiceImpl implements BlockChainApplicationService {

    private IBlockService blockService;
    private IBlockChainService blockChainService;
    private TransactionRepository transactionRepository;
    private IBlockChainRepository blockChainRepository;
    @Override
    public void createBlockChain() {
        blockChainService.ajouter(new BlockChainRequestDto("blockC1",1 ,200.0,new ArrayList<>()));
       // blockChainService.ajouter(new BlockChainRequestDto("blockC2",2 ,400.0,new ArrayList<>()));
     //   blockChainService.ajouter(new BlockChainRequestDto("blockC3",2 ,500.0,new ArrayList<>()));
    }

    @Override
    public void minerBlock() {
        List<Transaction> transactions=transactionRepository.findAll();
      /*  blockChainRepository.findAll().forEach(blockChain -> {
            System.out.println(blockChain.getNom());
            try {
                System.out.println(transactions);
              //  blockChainService.miner(blockChain.getId(),transactions,"destination4");
            } catch (BlockChainInvalidException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            System.out.println("done");
        });*/

    }

    @Override
    public void createTransactions() {
        Stream.of("destination1","destination2","destination3").forEach(str->{
            Transaction transaction=new Transaction();
            transaction.setDate(new Date());
            transaction.setDestinationAddress(str);
            transaction.setMontant(5000);
            transaction.setSourceAddress("source");
            transaction.setId(UUID.randomUUID().toString());
            transactionRepository.save(transaction);
        });
    }
}
