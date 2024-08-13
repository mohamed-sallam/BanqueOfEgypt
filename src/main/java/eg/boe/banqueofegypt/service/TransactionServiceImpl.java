package eg.boe.banqueofegypt.service;

import eg.boe.banqueofegypt.controller.TransactionService;
import eg.boe.banqueofegypt.db.TransactionRepository;
import eg.boe.banqueofegypt.entity.Transaction;
import eg.boe.banqueofegypt.model.dto.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<TransactionDto> getAllTransactions() {
        return transactionRepository.findAll().stream().map(it->
                modelMapper.map(it, TransactionDto.class)
        ).toList();
    }
}
