package com.cts.qbank.validation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;

import com.cts.qbank.domain.User;

public class UserRegistrationValidator {

	List <String> errorList = new ArrayList<>();


	public List<String> validate(User user) {
		
		validateFirstName(user.getFirstName());
		validateLastName(user.getLastName());
		validateUserName(user.getUsername());
		validateContactNumber(user.getPhone());
		validateConfirmPassword(user.getPassword(),user.getConfPassword());
		validatePAN(user.getPan());
		validateAge(user.getDob());
		validateCitizenStatus(user.getDob(), user.getCitizenstat());
	    validateInitialAmt(user.getCountry(), user.getInitDepAmt());
	    validateEmail(user.getEmail());
		return errorList;
	}
	
	public boolean validateFirstName(String fname) {
        if (null != fname) {        	
        	String regx = "[a-zA-Z][a-zA-Z ]*";
        	Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(fname);
            
            if(!matcher.matches()) {
            	errorList.add(" Firstname should only contain alphabets and spaces.");
        		return false;
            }else {
            	return true;
            }
        }
        return false;
    }
	
	public boolean validateLastName(String lname) {
        if (null != lname) {        	
        	String regx = "[a-zA-Z][a-zA-Z ]*";
        	Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(lname);
            
            if(!matcher.matches()) {
            	errorList.add(" Lastname should only contain alphabets and spaces.");
        		return false;
            }else {
            	return true;
            }
        }
        return false;
    }
	
	
	 public boolean validateUserName(String name) {
	        if (null != name) {        	
	        	String regx = "[a-zA-Z][a-zA-Z ]*";
	        	Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
	            Matcher matcher = pattern.matcher(name);
	            
	            if(!matcher.matches()) {
	            	errorList.add(" Username should only contain alphabets and spaces.");
	        		return false;
	            }else {
	            	return true;
	            }
	        }
	        return false;
	    }
	 
	 public boolean validateContactNumber(String phone) {
	        if (null != phone) {

	        	if(phone.matches("\\d{10}")) {
	        		return true;
	        	}else {
	        		errorList.add(" Contact number should be 10 digits.");
	        		return false;
	        	}
	        }
	        return false;
	    }
		
	 public boolean validateConfirmPassword(String pass, String cnfPass) {
	        if (null != pass && null != cnfPass) {        	
	        	if(pass.equalsIgnoreCase(cnfPass)) {
	        		return true;
	        	}else {
	        		errorList.add(" Passwords do not match.");
	        		return false;
	        	}
	        }
	        return false;
	    }
	
	
	 public boolean validatePAN(String pan) {
	        if (null != pan) {        	
	        	if(pan.length() == 12) {
	        		return true;
	        	}else {
	        		errorList.add(" Please enter correct PAN number.");
	        		return false;
	        	}
	        }
	        return false;
	    }

	 public boolean validateAge(Date userDate) {
		 int age = returnAge(userDate);
		 if(age >= 18 && age <= 96){
		    return true;
		 }else{
			 errorList.add(" Age should be betweeen 18 to 96.");
     		return false;
		 }		
	    }
	 
	 public boolean validateCitizenStatus(Date userDate, String citizStatus) {
		 if(null != citizStatus && !"".equals(citizStatus.trim())) {
		 long diffDays = returnAge(userDate);
		 if( (diffDays <= 18 && citizStatus.equals("Minor")) ||
				 (diffDays >  18 && diffDays <= 60 && citizStatus.equals("Normal")) ||
				 (diffDays >  60 && citizStatus.equals("Senior"))
				){
			 
		    return true;
		 }else{
			 errorList.add(" Please select correct citizen status.");
			 return false;
		 }		
	    }else {
	    	 errorList.add(" Please enter citizen status.");
	    	 return false;
	    }
		
	 }

	private int returnAge(Date userDate) {
		Date date = new Date();
		 long diff = Math.abs(date.getTime() - userDate.getTime());
		 long diffDays = diff / (24 * 60 * 60 * 1000);
		 int age = (int) (diffDays / 365);
		return age;
	}


	 public boolean validateInitialAmt(String country, BigDecimal initDep) {
		 if (null != country && !"".equals(country.trim())) {
			 
			 if(initDep == null) {
				 errorList.add(" Please enter initial deposit amount.");
	        	 return false;
			 }else {
	        	if( (country.equalsIgnoreCase("India") && initDep.intValue() >= 5000) ||
	        			(country.equalsIgnoreCase("UK") && initDep.intValue() >= 14000)	||
	        			 (country.equalsIgnoreCase("USA") && initDep.intValue() >= 15000) ||	
	        			  (country.equalsIgnoreCase("Italy") && initDep.intValue() >= 13000)
	        			) {
	        		return true;
	        	}else {
	        		errorList.add(" Please enter correct initial deposit amount.");
	        		return false;
	        	}
			 }
	        }else {
	        	errorList.add(" Please select country.");
        		return false;
	        }
	    }
	
	 
	 public boolean validateEmail(String email) {
		 if (null != email) {     
	        	if(EmailValidator.getInstance().isValid(email)) {
	        		return true;
	        	}else {
	        		errorList.add(" Please enter correct email address.");
	        		return false;
	        	}
	        }
	        return false;	
	    }
	


}
