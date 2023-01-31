package dev.cironeto.accesscontrolservice.dto;

import dev.cironeto.accesscontrolservice.model.AppUser;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppUserPostRequestBody {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String applicationName;
    
    
    public AppUserPostRequestBody(AppUser entity) {
    	this.email = entity.getEmail();
    	this.password = entity.getPassword();
    	this.firstName = entity.getFirstName();
    	this.lastName = entity.getLastName();
        this.applicationName = entity.getApplicationName();
    }
}
