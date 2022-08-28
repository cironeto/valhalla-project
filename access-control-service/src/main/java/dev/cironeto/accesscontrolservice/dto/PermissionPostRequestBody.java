package dev.cironeto.accesscontrolservice.dto;

import dev.cironeto.accesscontrolservice.model.BusinessFunction;
import dev.cironeto.accesscontrolservice.model.Permission;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PermissionPostRequestBody {

    @NotBlank
    private String name;

    public PermissionPostRequestBody(Permission entity){
        this.name = entity.getName();
    }
}
