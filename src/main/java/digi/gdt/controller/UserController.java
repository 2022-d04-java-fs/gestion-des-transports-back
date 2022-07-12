package digi.gdt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digi.gdt.service.UserService;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*")
public class UserController {

	private UserService userSrv;

	public UserController(UserService userSrv) {
		this.userSrv = userSrv;
	}

}
