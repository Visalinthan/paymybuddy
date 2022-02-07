package com.openclassrooms.projet06.service;

import com.openclassrooms.projet06.model.Operation;
import com.openclassrooms.projet06.repository.OperationRepository;

import java.util.List;

public class OperationService {

    private AccountService accountService;
    private OperationRepository operationRepository;

    public OperationService(AccountService accountService, OperationRepository operationRepository) {
        this.accountService = accountService;
        this.operationRepository = operationRepository;
    }

    public void sendMoney(Long userId, Long contactId, double amount){
        //this.accountService.setAmount(contactId, amount);
        //this.accountService.setAmount(userId,-amount);
    }

    public List<Operation> getOperationsDetails(Long userId){
        return this.operationRepository.findOperationByUserId(userId);
    }
}
