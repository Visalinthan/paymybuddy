package com.openclassrooms.projet06.service;

import com.openclassrooms.projet06.dto.SendMoneyDto;
import com.openclassrooms.projet06.model.Account;
import com.openclassrooms.projet06.model.Operation;
import com.openclassrooms.projet06.model.User;
import com.openclassrooms.projet06.repository.OperationRepository;
import com.openclassrooms.projet06.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OperationService {

    private AccountService accountService;
    private UserRepository userRepository;
    private OperationRepository operationRepository;

    @Autowired
    private AuthenticationFacadeImpl authenticationFacade;

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }

    public OperationService(AccountService accountService, UserRepository userRepository, OperationRepository operationRepository) {
        this.accountService = accountService;
        this.userRepository = userRepository;
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
