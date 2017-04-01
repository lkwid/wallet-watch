package lkwid.service;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lkwid.entity.Account;
import lkwid.entity.Roles;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceTest {
	private Account testAccount; 
	
	@Autowired
	private AccountService accountService;
	
	@Before
	public void setUp() throws Exception {
		testAccount = new Account();
		testAccount.setId(1L);
		testAccount.setFirstName("Romand");
		testAccount.setLastName("Guzik");
		testAccount.setEmail("rguzik@gmail.com");
		testAccount.setPassword("abc1");
		testAccount.setBalance(new BigDecimal(2600.00));		
		testAccount.setRoles(Arrays.asList(Roles.USER));
		accountService.saveAccount(testAccount);
	}

	@Test
	public void getEmail() {		
		Assert.assertEquals("rguzik@gmail.com", accountService.showAccount("rguzik@gmail.com").getEmail());
	}
	
	@Test
	public void existEmail() {
		String email = "rguzik@gmail.com";	
		Assert.assertEquals(true, accountService.existsAccount(email));
	}
	
	@Test
	public void getRoles() {	
		Assert.assertEquals(Roles.USER.getName(), accountService.showAccount("rguzik@gmail.com").getRoles().get(0).getName());
	}

}
