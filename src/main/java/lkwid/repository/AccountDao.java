package lkwid.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lkwid.entity.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {	
	
	@Query("SELECT a FROM Account a WHERE a.email = ?1")
	Account findByEmail(String email);	
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Account a SET a.balance = :balance WHERE a.email = :email")
	int updateBalance(@Param("balance") BigDecimal balance, @Param("email") String email);
}
