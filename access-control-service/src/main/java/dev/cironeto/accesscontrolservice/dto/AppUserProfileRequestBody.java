package dev.cironeto.accesscontrolservice.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppUserProfileRequestBody {

    @NotEmpty
    private String appUserEmail;
    @NotEmpty
    private String profileName;
}