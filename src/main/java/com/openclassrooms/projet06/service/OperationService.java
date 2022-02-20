package com.openclassrooms.projet06.service;

import com.openclassrooms.projet06.dto.SendMoneyDto;
import com.openclassrooms.projet06.model.Account;
import com.openclassrooms.projet06.model.Operation;
import com.openclassrooms.projet06.repository.OperationRepository;
import com.openclassrooms.projet06.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OperationService {

    private AccountService accountService;
    private OperationRepository operationRepository;


    public OperationService(AccountService accountService, OperationRepository operationRepository) {
        this.accountService = accountService;
        this.operationRepository = operationRepository;
    }


    public Operation sendMoney(SendMoneyDto sendMoneyDto, String email){
        Operation operation = new Operation();
        Account accountFrom = this.accountService.getAccount(email);
        Account accountTo = this.accountService.getAccount(sendMoneyDto.getEmail());
        operation.setAmount(sendMoneyDto.getAmount());
        operation.setDescription(sendMoneyDto.getDescription());
        operation.setAccountFrom(accountFrom);
        operation.setAccountTo(accountTo);
        this.accountService.setAmount(sendMoneyDto.getEmail(), sendMoneyDto.getAmount());
        this.accountService.setAmount(email,-sendMoneyDto.getAmount());

        return this.operationRepository.save(operation);
    }

    public List<Operation> getOperationsDetails(String email){
        return this.operationRepository.findOperationByUser(email);
    }

}
