package digi.gdt.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
public class UserLoginController {
	
	private UserService userSrv;

	public UserLoginController(UserService userSrv) {
		super();
		this.userSrv = userSrv;
	}
	
	@PostMapping
	  public ResponseEntity<?> userlogin(@RequestBody @Validated UserCredentialsDto userCredentials ){
		
		String email = userCredentials.getEmail();
		String password = userCredentials.getPassword();
		
		Optional<User> userExist = this.userSrv.findByEmail(email);
		if(userExist.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		
		User user = userExist.get();
		
		if(!user.getPassword().equals(password)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		
		UserDetailsDto userDetails = UserDetailsDto.from(user);
		
		return ResponseEntity.ok(userDetails);
	  }

}
