package digi.gdt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digi.gdt.dto.UserCredentialsDto;
import digi.gdt.dto.UserDetailsDto;
import digi.gdt.entity.User;
import digi.gdt.service.UserService;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*")
public class UserLoginController {

	private UserService userSrv;

	public UserLoginController(UserService userSrv) {
		super();
		this.userSrv = userSrv;
	}

	@PostMapping
	public ResponseEntity<?> connectUser(@RequestBody UserCredentialsDto user) {
		User connectedUser = this.userSrv.login(user);
		UserDetailsDto userDetails = UserDetailsDto.from(connectedUser);
		return ResponseEntity.ok(userDetails);
	}

}
