package com.spring_projects.bankingapp.service.impl;

import com.spring_projects.bankingapp.dto.AccountDto;
import com.spring_projects.bankingapp.entity.Account;
import com.spring_projects.bankingapp.mapper.AccountMapper;
import com.spring_projects.bankingapp.repository.AccountRepository;
import com.spring_projects.bankingapp.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl (AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= AccountMapper.mapToAccount(accountDto);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
       Account account=accountRepository.
               findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));
       return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account=accountRepository.
                findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));
        double total=account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account=accountRepository.
                findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));

        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient funds");
        }
        double total=account.getBalance()-amount;
        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAccounts() {
        List <Account> accounts=accountRepository.findAll();
        return accounts.stream().map((account)->
            AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Account account=accountRepository.
                findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));
        accountRepository.
                deleteById(id);

    }
}
