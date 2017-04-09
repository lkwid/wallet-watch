package lkwid.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import lkwid.entity.Account;
import lkwid.entity.Operation;
import lkwid.repository.OperationDao;

@Service
@Transactional
public class OperationService {	
	
	@Autowired
	private OperationDao operationDao;
	@Autowired
	private AccountService accountService;	

	private Account getAccountFromSession() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Account account = accountService.findAccount(user.getUsername());
		return account;
	}	

	public void saveOperation(Operation operation) {
		Account account = getAccountFromSession();
		operation.setAccount(account);
		operation.setDate(LocalDate.now());				
		BigDecimal balance = balanceChange(operation.getTransfer(), account);
		accountService.updateBalance(balance, account.getEmail());		
		operationDao.save(operation);
	}

	private BigDecimal balanceChange(BigDecimal transfer, Account account) {
		BigDecimal balance = account.getBalance();
		return balance.subtract(transfer);
	}
	
	public List<Operation> showOperations() {
		Account account = getAccountFromSession();
		List<Operation> operations = (List<Operation>) operationDao.findAllByUser(account.getId());
		Collections.reverse(operations);
		return operations;
	}	


}
