package lkwid.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lkwid.entity.Operation;
import lkwid.service.OperationService;

@RestController
@RequestMapping("/api/operation")
public class OperationApiController {
	
	@Autowired
	private OperationService operationService;
	
	@PostMapping
	public void saveOperation(@RequestBody Operation operation) {
		operationService.saveOperation(operation);
	}
	
}
