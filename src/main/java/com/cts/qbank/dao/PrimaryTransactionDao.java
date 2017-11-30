package com.cts.qbank.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cts.qbank.domain.PrimaryTransaction;

public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction, Long> {

    List<PrimaryTransaction> findAll();
}
