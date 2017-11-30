package com.cts.qbank;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import com.cts.qbank.validation.UserRegistrationValidator;

/**
 * Junit test case class for User registration process
 * @author Aniruddha Anikhindi
 *
 */
public class TestUserRegistrationValidation {

	private UserRegistrationValidator userRegistrationValidator = new UserRegistrationValidator();
	private final String spChars = "$#@&";
	private final String validName = "abcd";
	
	private final String invalidContact = "980AAA";
	private final String validContact = "8087770405";
	
	
	@Test
	public void testValidateFirstName(){
		assertFalse(userRegistrationValidator.validateFirstName(spChars));
		assertTrue(userRegistrationValidator.validateFirstName(validName));
	}
	
	@Test
	public void testValidateLastName() {
		assertFalse(userRegistrationValidator.validateLastName(spChars));
		assertTrue(userRegistrationValidator.validateLastName(validName));
	}
	
	@Test
	public void testValidateUserName() {
		assertFalse(userRegistrationValidator.validateUserName(spChars));
		assertTrue(userRegistrationValidator.validateUserName(validName));
	}
	
	@Test
	public void testValidateContactNumber() {
		
		assertFalse(userRegistrationValidator.validateContactNumber(invalidContact));
		assertTrue(userRegistrationValidator.validateContactNumber(validContact));
	}
	
	@Test
	public void testValidateConfirmPassword() {
		
		assertFalse(userRegistrationValidator.validateConfirmPassword("abcd", "defg"));
		assertTrue(userRegistrationValidator.validateConfirmPassword("abcd", "abcd"));
	}
	
	@Test
	public void testValidatePAN() {
		assertFalse(userRegistrationValidator.validatePAN("1234"));
		assertTrue(userRegistrationValidator.validatePAN("123456789012"));
	}
	
	@Test
	public void testValidateAge() {
		assertFalse(userRegistrationValidator.validateAge(new Date(2017, 10, 10)));
	}
	
	@Test
	public void testValidateCitizenStatus() {

		assertTrue(userRegistrationValidator.validateCitizenStatus(new Date(1963, 10, 10), "Senior"));
		assertFalse(userRegistrationValidator.validateCitizenStatus(new Date(1963, 10, 10), "Normal"));
	}
	
	@Test
    public void testValidateInitialAmt() {
		assertTrue(userRegistrationValidator.validateInitialAmt("India", new BigDecimal("5000")));
		assertFalse(userRegistrationValidator.validateInitialAmt("India", new BigDecimal("4998")));
		
		assertTrue(userRegistrationValidator.validateInitialAmt("UK", new BigDecimal("14000")));
		assertFalse(userRegistrationValidator.validateInitialAmt("UK", new BigDecimal("10000")));
		
		assertTrue(userRegistrationValidator.validateInitialAmt("USA", new BigDecimal("15000")));
		assertFalse(userRegistrationValidator.validateInitialAmt("USA", new BigDecimal("1000")));
		
		assertTrue(userRegistrationValidator.validateInitialAmt("Italy", new BigDecimal("13000")));
		assertFalse(userRegistrationValidator.validateInitialAmt("Italy", new BigDecimal("10000")));
    }
    
	@Test
    public void testValidateEmail() {
		assertTrue(userRegistrationValidator.validateEmail("aniruddha.anikhindi@cognizant.com"));
		assertFalse(userRegistrationValidator.validateEmail("aniruddha.anikhindicognizant.com"));
    }

}
