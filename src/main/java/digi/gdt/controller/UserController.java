package digi.gdt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import digi.gdt.dto.CreateUserDto;
import digi.gdt.entity.Users;
import digi.gdt.service.UserService;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*")
public class UserController {

	private UserService userSrv;

	public UserController(UserService userSrv) {
		this.userSrv = userSrv;
	}



	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody CreateUserDto user) {
		Users newUser = this.userSrv.createUser(user);

		return ResponseEntity.ok(newUser);
	}

}
