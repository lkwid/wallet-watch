package lkwid.service;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lkwid.entity.Transaction;
import lkwid.repository.TransactionDao;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionDao transactionDao;
	
	public Transaction getTransaction(long id) {
		return transactionDao.findOne(id);
	}
	
	public List<Transaction> getAllTransactions() {
		return transactionDao.findAll();
	}
	
	public void saveTransaction(Transaction transaction) {
		transactionDao.save(transaction);
	}
	
	public void updateTransaction(long id, Transaction transaction) {
		if (transactionDao.exists(id))
			transactionDao.save(transaction);
		else 
			new ObjectNotFoundException(id, "transaction");
	}
	
	public void deleteTransaction(long id) {
		transactionDao.delete(id);
	}
	
}
