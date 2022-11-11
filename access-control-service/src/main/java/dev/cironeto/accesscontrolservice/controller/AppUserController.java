package dev.cironeto.accesscontrolservice.controller;

import dev.cironeto.accesscontrolservice.dto.AppUserPostRequestBody;
import dev.cironeto.accesscontrolservice.dto.AppUserRequestBody;
import dev.cironeto.accesscontrolservice.service.KeycloakService;
import dev.cironeto.accesscontrolservice.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class AppUserController {

	private final AppUserService appUserService;
	private final KeycloakService keycloakService;

	@PostMapping(value = "/create")
	public ResponseEntity<UUID> createKeycloakUser(@RequestBody AppUserPostRequestBody userDto) {
		return ResponseEntity.ok(keycloakService.createUserInKeycloak(userDto));
	}

	@GetMapping(value = "/find/all")
	public ResponseEntity<List<AppUserRequestBody>> findAll(){
		return ResponseEntity.ok(appUserService.findAll());
	}

	@GetMapping(value = "/find/{id}")
	public ResponseEntity<AppUserRequestBody> findById(@PathVariable UUID id){
		return ResponseEntity.ok(appUserService.findById(id));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<AppUserRequestBody> update(@PathVariable UUID id, @RequestBody AppUserPostRequestBody dto){
		return ResponseEntity.ok(appUserService.update(id, dto));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id){
		appUserService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
