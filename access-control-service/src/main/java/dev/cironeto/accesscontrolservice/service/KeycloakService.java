package dev.cironeto.accesscontrolservice.service;

import dev.cironeto.accesscontrolservice.dto.AccessTokenResponseBody;
import dev.cironeto.accesscontrolservice.dto.AppUserPostRequestBody;
import dev.cironeto.accesscontrolservice.exception.BadRequestException;
import dev.cironeto.accesscontrolservice.validation.BeanValidator;
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
import org.keycloak.util.JsonSerialization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
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

	public UUID createUserInKeycloak(AppUserPostRequestBody userDto) {
		BeanValidator.validate(userDto);

		UserRepresentation user = buildKeycloakUserToBeCreated(userDto);
		RealmResource keycloakRealmResource = getKeycloak();
		UsersResource usersResource = keycloakRealmResource.users();

		UUID createdId;
		try {
			Response response = usersResource.create(user);
			createdId = UUID.fromString(CreatedResponseUtil.getCreatedId(response));
			appUserService.save(createdId, userDto);
		}catch (Exception e){
			throw new BadRequestException("E-mail already exists");
		}
		return createdId;
	}

	private UserRepresentation buildKeycloakUserToBeCreated(AppUserPostRequestBody userDto) {
		UserRepresentation user = new UserRepresentation();
		user.setUsername(userDto.getEmail());
		user.setEmail(userDto.getEmail());
		user.setLastName(userDto.getLastName());
		user.setFirstName(userDto.getFirstName());
		user.setEnabled(true);

		CredentialRepresentation passwordCredential = new CredentialRepresentation();
		passwordCredential.setTemporary(false);
		passwordCredential.setType(CredentialRepresentation.PASSWORD);
		passwordCredential.setValue(userDto.getPassword());

		user.setCredentials(List.of(passwordCredential));
		return user;
	}

	private RealmResource getKeycloak() {
		Keycloak keycloak = KeycloakBuilder.builder()
				.serverUrl(SERVER_URL)
				.realm(REALM)
				.grantType(OAuth2Constants.CLIENT_CREDENTIALS)
				.clientId(CLIENT_ID)
				.clientSecret(SECRET_KEY).build();
		RealmResource realmResource = keycloak.realm(REALM);
		return realmResource;
	}

	public AccessTokenResponseBody getAccessToken(String username, String password) {
		Keycloak instance = Keycloak.getInstance(
				SERVER_URL,
				REALM,
				username,
				password,
				CLIENT_ID,
				SECRET_KEY);
		AccessTokenResponseBody token = new AccessTokenResponseBody();
		String accessTokenString = instance.tokenManager().getAccessTokenString();
		token.setAccessToken(accessTokenString);
		return token;
	}

}