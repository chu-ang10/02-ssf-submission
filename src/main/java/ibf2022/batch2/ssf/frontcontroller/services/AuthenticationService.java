package ibf2022.batch2.ssf.frontcontroller.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch2.ssf.frontcontroller.models.CaptchaEntry;
import ibf2022.batch2.ssf.frontcontroller.models.Entry;
import ibf2022.batch2.ssf.frontcontroller.respositories.AuthenticationRepository;
import jakarta.validation.Valid;

@Service
public class AuthenticationService {
	@Autowired
    private AuthenticationRepository authenticationRepo;

	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public void authenticate(String username, String password) throws Exception {
		// https://stackoverflow.com/a/42370974/998335
	final String uri = "https://auth.checklee.com/api/authenticate";

	Entry entry = new Entry();
	entry.setUsername(username);


	entry.setPassword(password);

	String string = entry.toJson().toString();

    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.postForObject(uri, string, String.class);
  
	if (result.contains("Authenticated")) {
		System.out.println("Authenticated");
	} else {
		System.out.println("Not Authenticated");
		throw new Exception("Not Authenticated");
	}

	}

	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) {
		authenticationRepo.disableUser(username);
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		return authenticationRepo.isLocked(username);
	}

	public boolean needsCaptcha(String username) {
		return false;
	}

	public List<ObjectError> validateLogin(String username, String password) {
		List<ObjectError> errors = new LinkedList<>();
        FieldError error;

		if (username.length() < 2) {
			error = new FieldError("login", "username", "Username must be at least 2 characters long");
			errors.add(error);
		}
		
		if (password.length() < 2) {
			error = new FieldError("login", "password", "Password must be at least 2 characters long");
			errors.add(error);
		}


		return errors;
	}

    public List<ObjectError> validateLogin(@Valid CaptchaEntry login) {
        return this.validateLogin(login.getUsername(), login.getPassword());
    }


}
