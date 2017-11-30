package com.cts.qbank.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cts.qbank.domain.PrimaryAccount;

public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount,Long> {

    PrimaryAccount findByAccountNumber (long accountNumber);
    
    @Query("SELECT coalesce(max(pa.accountNumber), 11000000) FROM  PrimaryAccount pa")
    Long getMaxAccountNum();
}
