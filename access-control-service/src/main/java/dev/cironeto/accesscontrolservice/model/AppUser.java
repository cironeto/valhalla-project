package dev.cironeto.accesscontrolservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppUser {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    @Column(unique = true)
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String password;

}
