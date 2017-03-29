package lkwid.service;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lkwid.dao.AccountDao;
import lkwid.entity.Account;

@Service
public class AccountService {
	
	@Autowired
	private AccountDao accountDao;
	
	public Account getAccount(long id) {
		return accountDao.findOne(id);
	}
	
	public List<Account> getAllAccounts() {
		return accountDao.findAll();
	}
	
	public void saveAccount(Account account) {
		accountDao.save(account);
	}
	
	public void updateAccount(long id, Account account) {
		if (accountDao.exists(id))
			accountDao.save(account);
		else
			new ObjectNotFoundException(id, "account");
	}
	
	public void deleteAccount(long id) {
		accountDao.delete(id);
	}	
	
}
