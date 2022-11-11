package dev.cironeto.accesscontrolservice.controller;

import dev.cironeto.accesscontrolservice.dto.AccessTokenResponseBody;
import dev.cironeto.accesscontrolservice.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/keycloak")
public class KeycloakController {

	private final KeycloakService keycloakService;

	@PostMapping(value = "/token")
	public ResponseEntity<AccessTokenResponseBody> getAccessToken(String username, String password){
		return ResponseEntity.ok(keycloakService.getAccessToken(username, password));
	}

}
