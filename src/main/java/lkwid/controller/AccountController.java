package lkwid.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lkwid.entity.Account;
import lkwid.entity.dto.AccountDto;
import lkwid.exception.AccountExistsException;
import lkwid.service.AccountService;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@RequestMapping("/")	
	public String home(Model model, AccountDto accountDto) {
		model.addAttribute("accountDto", accountDto);		
		return "home";
	}
	
	@RequestMapping("/login/error")
	public String loginError(Model model, AccountDto accountDto) {
		model.addAttribute("loginError", true);
		model.addAttribute("accountDto", accountDto);	
		return "home";
	}

	@PostMapping("/login/registration")
	public String regiterAccount(@ModelAttribute @Valid AccountDto accountDto, BindingResult result, HttpServletRequest request) {
		Account account = new Account();
		if (!result.hasErrors()) {
			account = createAccount(accountDto);
		}
		if (account == null) {
			result.rejectValue("email", "message.regEmail");
		}
		if (result.hasErrors())
			return "home";
		else
			authenticateUserAndSetSession(account, request);
			return "redirect:/account";
			
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
	
	private void authenticateUserAndSetSession(Account account, HttpServletRequest request) {
        String username = account.getEmail();
        String password = account.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // generate session if one doesn't exist
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }

}
