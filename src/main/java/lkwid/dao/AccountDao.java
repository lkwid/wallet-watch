package lkwid.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import lkwid.entity.Account;

public interface AccountDao extends JpaRepository<Account, Long>{
}
