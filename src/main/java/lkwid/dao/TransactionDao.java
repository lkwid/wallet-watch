package lkwid.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import lkwid.entity.Transaction;

public interface TransactionDao extends JpaRepository<Transaction, Long>{
}
