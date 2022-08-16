package dev.cironeto.accesscontrolservice.controller;

import dev.cironeto.accesscontrolservice.dto.UserPostRequestBody;
import dev.cironeto.accesscontrolservice.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/keycloak")
public class KeycloakController {

	private final KeycloakService keycloakService;

	@PostMapping(value = "/create-user")
	public ResponseEntity<UUID> createKeycloakUser(@RequestBody UserPostRequestBody userDto) {
		return ResponseEntity.ok(keycloakService.createUserInKeycloak(userDto));
	}

}
