package lkwid.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lkwid.entity.Operation;
import lkwid.service.OperationService;

@Controller
public class OperationController {
	
	@Autowired
	private OperationService operationService;
	
	@RequestMapping("/account")
	public String showOperations(Model model) {
		Collection<Operation> operations = operationService.showOperations();		
		model.addAttribute("operation", new Operation());
		model.addAttribute("operations", operations);
		model.addAttribute("balance", operationService.showBalance());
		return "account";
	}
	
	@PostMapping(value = "/operation", params = "save")
	public String saveOperation(@Valid Operation operation) {			
		operationService.saveOperation(operation);
		return "redirect:/account";
	}
	
	@RequestMapping(value = "/operation", params = "delete")
	public String deleteOperation(HttpServletRequest req) {
		long id =  Long.valueOf(req.getParameter("delete"));
		operationService.deleteOperation(id);
		return "redirect:/account";
	}

}
