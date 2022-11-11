package dev.cironeto.accesscontrolservice.dto;

import dev.cironeto.accesscontrolservice.model.AppUser;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppUserRequestBody {

    private UUID id;
    private String email;
    private String firstName;
    private String lastName;


    public AppUserRequestBody(AppUser entity) {
        this.id = entity.getId();
    	this.email = entity.getEmail();
    	this.firstName = entity.getFirstName();
    	this.lastName = entity.getLastName();
    }
}
