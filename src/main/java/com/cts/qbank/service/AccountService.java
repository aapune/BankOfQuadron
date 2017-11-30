package com.cts.qbank.service;

import java.math.BigDecimal;
import java.security.Principal;

import com.cts.qbank.domain.PrimaryAccount;
import com.cts.qbank.domain.SavingsAccount;

public interface AccountService {
	
    PrimaryAccount createPrimaryAccount(BigDecimal initAmt);
    
    SavingsAccount createSavingsAccount(BigDecimal initAmt);
    
    void deposit(String accountType, double amount, Principal principal);
    
    void withdraw(String accountType, double amount, Principal principal);
    
}
