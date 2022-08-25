package dev.cironeto.accesscontrolservice.service;

import dev.cironeto.accesscontrolservice.dto.UserPostRequestBody;
import dev.cironeto.accesscontrolservice.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class KeycloakService {
	@Value("${keycloak.auth-server-url}")
	private String SERVER_URL;
	@Value("${keycloak.resource}")
	private String CLIENT_ID;
	@Value("${keycloak.realm}")
	private String REALM;
	@Value("${keycloak.credentials.secret}")
	private String SECRET_KEY;

	private final AppUserService appUserService;

	public UUID createUserInKeycloak(UserPostRequestBody userDto) {
		UserRepresentation user = buildKeycloakUserToBeCreated(userDto);
		RealmResource keycloakRealmResource = getKeycloak();
		UsersResource usersResource = keycloakRealmResource.users();

		UUID createdId;
		try {
			Response response = usersResource.create(user);
			createdId = UUID.fromString(CreatedResponseUtil.getCreatedId(response));
			appUserService.saveUser(createdId, userDto);
		}catch (RuntimeException e){
			throw new BadRequestException("E-mail already exists");
		}
		return createdId;
	}

	private UserRepresentation buildKeycloakUserToBeCreated(UserPostRequestBody userDto) {
		UserRepresentation user = new UserRepresentation();
		user.setEmail(userDto.getEmail());
		user.setLastName(userDto.getLastName());
		user.setFirstName(userDto.getFirstName());
		user.setEnabled(true);
		user.setRealmRoles(List.of("USER"));

		CredentialRepresentation passwordCredential = new CredentialRepresentation();
		passwordCredential.setTemporary(false);
		passwordCredential.setType(CredentialRepresentation.PASSWORD);
		passwordCredential.setValue(userDto.getPassword());

		user.setCredentials(List.of(passwordCredential));
		return user;
	}

	private RealmResource getKeycloak() {
		return KeycloakBuilder.builder()
				.serverUrl(SERVER_URL)
				.realm(REALM)
				.grantType(OAuth2Constants.CLIENT_CREDENTIALS)
				.clientId(CLIENT_ID)
				.clientSecret(SECRET_KEY)
				.realm(REALM)
				.resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
				.build().realm(REALM);
	}

	public String getAccessToken(String username, String password){
		Keycloak instance = Keycloak.getInstance(
				SERVER_URL,
				REALM,
				username,
				password,
				CLIENT_ID,
				SECRET_KEY);
		String accessTokenString = instance.tokenManager().getAccessTokenString();
		return accessTokenString;
	}

}