package lkwid.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lkwid.entity.Account;
import lkwid.entity.Roles;
import lkwid.repository.AccountDao;

@Service
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
		return new org.springframework.security.core.userdetails
				.User(account.getEmail(),
						account.getPassword(),
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
	
	public Account showAccount(String email) {
		return accountDao.findByEmail(email);
	}
	
	public boolean existsAccount(String email) {
		if (accountDao.findByEmail(email) != null)
			return true;		
		return false;
	}
	
	public Account saveAccount(Account account) {
		return accountDao.save(account);
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
