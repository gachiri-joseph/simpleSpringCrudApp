package com.spring_projects.bankingapp.repository;

import com.spring_projects.bankingapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
