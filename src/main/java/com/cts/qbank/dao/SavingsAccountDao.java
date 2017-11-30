package com.cts.qbank.dao;

import org.springframework.data.repository.CrudRepository;

import com.cts.qbank.domain.SavingsAccount;

public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long> {

    SavingsAccount findByAccountNumber (long accountNumber);
}
