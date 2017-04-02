package lkwid.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lkwid.entity.Account;
import lkwid.entity.Roles;
import lkwid.entity.dto.AccountDto;
import lkwid.exception.AccountExistsException;
import lkwid.repository.AccountDao;

@Service
@Transactional
public class AccountService implements UserDetailsService {

	@Autowired
	private AccountDao accountDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Account account = accountDao.findByEmail(email);
		if (account == null) {
			throw new UsernameNotFoundException("No user found with username: " + email);
		}
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getPassword(),
				enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
				getAuthorities(account.getRoles()));
	}

	private static List<GrantedAuthority> getAuthorities(List<Roles> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Roles role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}
	
	public Account saveAccountFromApi(Account account) {
		return accountDao.save(account);
	}

	public Account saveAccount(AccountDto accountDto) throws AccountExistsException {
		if (accountExists(accountDto) != null)
			throw new AccountExistsException();
		Account account = new Account();
		account.setFirstName(accountDto.getFirstName());
		account.setLastName(accountDto.getLastName());
		account.setEmail(accountDto.getEmail());
		account.setPassword(accountDto.getPassword());
		account.setBalance(accountDto.getBalance());
		account.setRoles(Arrays.asList(Roles.USER));

		return accountDao.save(account);
	}

	private Account accountExists(AccountDto accountDto) {
		Account account = accountDao.findByEmail(accountDto.getEmail());
		return account;
	}

	public void updateBalance(BigDecimal transfer, String email) {
		accountDao.updateBalance(transfer, email);
	}

	public Account findAccount(String email) {
		return accountDao.findByEmail(email);
	}

	public boolean existsAccount(String email) {		
		if (accountDao.findByEmail(email) != null)
			return true;
		return false;
	}

	public void updateAccount(String email, Account account) {
		Example<Account> accountByEmail = Example.of(findAccount(email));
		if (accountDao.exists(accountByEmail))
			new ObjectNotFoundException(email, "account");			
		else
			accountDao.save(account);			
	}

	public void deleteAccount(String email) {
		Account account = findAccount(email);
		accountDao.delete(account);
	}

}
