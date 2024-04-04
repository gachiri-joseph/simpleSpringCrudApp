package com.spring_projects.bankingapp.service;
import com.spring_projects.bankingapp.dto.AccountDto;

import java.util.List;

public interface AccountService {

     AccountDto createAccount(AccountDto accountDto);
     AccountDto getAccountById(Long id);
     AccountDto deposit(Long id,double amount);
     AccountDto withdraw(Long id,double amount);
     List<AccountDto> getAccounts();
     void delete(Long id);
}
