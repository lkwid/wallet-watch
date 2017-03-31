package lkwid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lkwid.entity.Account;

public interface AccountDao extends JpaRepository<Account, Long>{	
	@Query("SELECT a FROM Account a WHERE a.email = ?1")
	Account findByEmail(String email);
}
