package com.cts.qbank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cts.qbank.domain.User;

public interface UserDao extends CrudRepository<User, Long> {
    
	User findByUsername(String username);
	
    User findByEmail(String email);
    
    User findByPan(String pan);
    
    List<User> findAll();
    
    @Query("SELECT at.accDesc FROM  AccountType at")
    List <String> getAccountTypes();
}
