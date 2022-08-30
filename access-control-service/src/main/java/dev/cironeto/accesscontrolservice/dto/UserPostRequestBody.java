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
public class UserPostRequestBody {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    
    
    public UserPostRequestBody(AppUser appUser) {
    	this.email = appUser.getEmail();
    	this.password = appUser.getPassword();
    	this.firstName = appUser.getFirstName();
    	this.lastName = appUser.getLastName();
    }
}
