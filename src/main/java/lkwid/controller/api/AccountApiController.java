package lkwid.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lkwid.entity.Account;
import lkwid.service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountApiController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping
	public ResponseEntity<?> createAccount(@RequestBody Account account) {
		HttpStatus status = HttpStatus.OK;
		if (!accountService.existsAccount(account.getEmail())) {
			status = HttpStatus.CREATED;
			return new ResponseEntity<Account>(accountService.saveAccountFromApi(account), status);
		} else
			return new ResponseEntity<String>("Email already exists", status = HttpStatus.CONFLICT);
	}

	@GetMapping
	@ResponseBody
	public String showUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Account account = accountService.findAccount(user.getUsername());
		return "Account: " + account.getEmail() + " : " + account.getBalance();
	}

}
