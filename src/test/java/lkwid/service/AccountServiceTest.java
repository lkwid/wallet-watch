package lkwid.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lkwid.entity.Account;
import lkwid.entity.Roles;
import lkwid.entity.dto.AccountDto;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AccountServiceTest {	
	
	@Autowired
	private AccountService accountService;
	
	@Before	
	public void setUp() throws Exception {
		AccountDto accountDto = new AccountDto();		
		accountDto.setFirstName("Romand");
		accountDto.setLastName("Guzik");
		accountDto.setEmail("rguzik@gmail.com");
		accountDto.setPassword("abc1");
		accountDto.setBalance(new BigDecimal(2600.00));		
		accountService.saveAccount(accountDto);
	}

	@Test
	public void getEmail() {		
		Assert.assertEquals("rguzik@gmail.com", accountService.findAccount("rguzik@gmail.com").getEmail());
	}
	
	@Test
	public void existEmail() {
		String email = "rguzik@gmail.com";	
		Assert.assertEquals(true, accountService.existsAccount(email));
	}
	
	@Test
	public void getRoles() {	
		Assert.assertEquals(Roles.USER.getName(), accountService.findAccount("rguzik@gmail.com").getRoles().get(0).getName());
	}
	
	@Test
	public void updateAccount() {
		Account testAccount = accountService.findAccount("rguzik@gmail.com");		
		testAccount.setPassword("def2");
		accountService.updateAccount("rguzik@gmail.com", testAccount);
		Assert.assertEquals("def2", accountService.findAccount("rguzik@gmail.com").getPassword());
	}

}
