package lkwid.controller;

import java.util.Collection;

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
		return "account";
	}
	
	@PostMapping(value = "/operation", params = "save")
	public String saveOperation(@Valid Operation operation) {			
		operationService.saveOperation(operation);
		return "redirect:/account";
	}	

}
