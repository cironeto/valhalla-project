package dev.cironeto.accesscontrolservice.dto;

import dev.cironeto.accesscontrolservice.model.Permission;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PermissionRequestBody {

    @NotBlank
    private String name;

    public PermissionRequestBody(Permission entity){
        this.name = entity.getName();
    }
}
