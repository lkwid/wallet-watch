package lkwid.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lkwid.entity.Account;
import lkwid.entity.dto.AccountDto;
import lkwid.exception.AccountExistsException;
import lkwid.service.AccountService;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping({"/", "/welcome}"})
	public String home(Model model, AccountDto accountDto) {
		model.addAttribute("accountDto", accountDto);
		return "home";
	}
	
	@PostMapping("/account/registration")
	public ModelAndView regiterAccount(@ModelAttribute @Valid AccountDto accountDto, BindingResult result) {
		Account account = new Account();
		if (!result.hasErrors()) {
			account = createAccount(accountDto);
		}
		if (account == null) {
			result.rejectValue("email", "message.regEmail");
		}
		if (result.hasErrors())
			return new ModelAndView("home", "accountDto", accountDto);
		else
			return new ModelAndView("account", "accountDto", accountDto) {
			};		
	}

	private Account createAccount(AccountDto accountDto) {
		Account account = null;
		try {
			account = accountService.saveAccount(accountDto);
		} catch (AccountExistsException e) {
			e.getMessage();
			return null;
		}
		return account;
	}


}
