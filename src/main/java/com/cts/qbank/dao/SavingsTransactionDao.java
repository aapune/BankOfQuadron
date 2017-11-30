package com.cts.qbank.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cts.qbank.domain.SavingsTransaction;

public interface SavingsTransactionDao extends CrudRepository<SavingsTransaction, Long> {

    List<SavingsTransaction> findAll();
}

