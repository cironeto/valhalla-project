package dev.cironeto.accesscontrolservice.dto;

import dev.cironeto.accesscontrolservice.model.Permission;
import dev.cironeto.accesscontrolservice.model.Profile;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileRequestBody {

    @NotBlank
    private String name;

    public ProfileRequestBody(Profile entity){
        this.name = entity.getName();
    }
}
