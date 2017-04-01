package lkwid.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
			return new ResponseEntity<Account>(accountService.saveAccount(account), status);
		} else
			return new ResponseEntity<String>("Email already exists", status = HttpStatus.CONFLICT);
	}

	@GetMapping("/{email}")
	@ResponseBody
	public Account showUser(@PathVariable("email") String email) {
		Account account = accountService.showAccount(email);
		return account;
	}

}
