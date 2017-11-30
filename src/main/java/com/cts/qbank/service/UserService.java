package com.cts.qbank.service;

import java.util.List;
import java.util.Set;

import com.cts.qbank.domain.User;
import com.cts.qbank.domain.security.UserRole;

public interface UserService {
	
    User findByUsername(String username);

    User findByEmail(String email);
    
    User findByPan(String pan);

    boolean checkUserExists(String username, String email, String pan);

    boolean checkUsernameExists(String username);
    
    boolean checkPanExists(String pan);

    boolean checkEmailExists(String email);
    
    void save (User user);
    
    User createUser(User user, Set<UserRole> userRoles);
    
    User saveUser (User user); 
    
    List<User> findUserList();

    void enableUser (String username);

    void disableUser (String username);
    
    public List<String> getAccTypes();
    
}
