package com.cts.qbank.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.qbank.dao.RoleDao;
import com.cts.qbank.domain.PrimaryAccount;
import com.cts.qbank.domain.SavingsAccount;
import com.cts.qbank.domain.User;
import com.cts.qbank.domain.security.UserRole;
import com.cts.qbank.service.UserService;
import com.cts.qbank.validation.UserRegistrationValidator;


@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private RoleDao roleDao;
	
	
	@RequestMapping("/")
	public String redirectHome() {
		return "home";
	}
	
	@RequestMapping("/index")
    public String index() {
        return "index";
    }
	@RequestMapping("/home")
    public String home() {
        return "home";
    }
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        User user = new User();       
        addRequiredAttributes(model);     
        model.addAttribute("user", user);
        return "signup";
    }

	private void addRequiredAttributes(Model model) {
		List <String> genderList = Arrays.asList( new String [] {"M","F"});
        List <String> cstatList = Arrays.asList( new String [] {"Minor","Normal","Senior"});
        List <String> accList = Arrays.asList( new String [] {"Salary","Savings"});
        List <String> countryList = Arrays.asList( new String [] {"India","UK","USA","Italy"});
        List <String> stateList = Arrays.asList( new String [] {"TamilNadu","Kerala","Karnataka","Andhra Pradesh","Scotland","New Jersey","Rome"});
        model.addAttribute("genderList",genderList);        
        model.addAttribute("cstatList",cstatList);     
        model.addAttribute("accList",accList); 
        model.addAttribute("countryList",countryList);     
        model.addAttribute("stateList",stateList);
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute("user") User user,  Model model) {
	
		UserRegistrationValidator userValidator = new UserRegistrationValidator();
		List <String> errorList = userValidator.validate(user);
		if(errorList != null && errorList.size() > 0 ) {
			addRequiredAttributes(model);  
			model.addAttribute("errorList", errorList);
			return "signup";
		}
		
		
        if(userService.checkUserExists(user.getUsername(), user.getEmail(), user.getPan()))  {
     	
            if (userService.checkEmailExists(user.getEmail())) {
                model.addAttribute("emailExists", true);
            }

            if (userService.checkUsernameExists(user.getUsername())) {
                model.addAttribute("usernameExists", true);
            }
            
            if (userService.checkPanExists(user.getPan())) {
                model.addAttribute("panExists", true);
            }
            addRequiredAttributes(model);  
            return "signup";
        } else {
        	 Set<UserRole> userRoles = new HashSet<>();
             userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));

            userService.createUser(user, userRoles);
            model.addAttribute("successSave", "Registered successfully with Bank of Quadron.");
            return "signup";
            //return "redirect:/";
        }
    }
	
	@RequestMapping("/userFront")
	public String userFront(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        
        if(user.getPrimaryAccount()  != null) {
        	PrimaryAccount primaryAccount = user.getPrimaryAccount();
        	model.addAttribute("primaryAccount", primaryAccount);
        }else if(user.getSavingsAccount()  != null) {
            SavingsAccount savingsAccount = user.getSavingsAccount();
            model.addAttribute("savingsAccount", savingsAccount);
        }
        model.addAttribute("acctType",user.getAccntType());
        return "userFront";
    }
}
