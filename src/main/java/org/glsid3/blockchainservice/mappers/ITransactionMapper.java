package org.glsid3.blockchainservice.mappers;

import org.glsid3.blockchainservice.dto.TransactionRequestDto;
import org.glsid3.blockchainservice.dto.TransactionResponseDto;
import org.glsid3.blockchainservice.entities.Transaction;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ITransactionMapper {
    TransactionResponseDto transactionToTransactionResponseDto(Transaction transaction);
    Transaction transactionRequestDtoToTransaction(TransactionRequestDto transactionRequestDto);
}