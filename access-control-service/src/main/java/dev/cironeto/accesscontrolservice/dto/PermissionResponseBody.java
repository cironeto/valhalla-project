package dev.cironeto.accesscontrolservice.dto;

import dev.cironeto.accesscontrolservice.model.Permission;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PermissionResponseBody {

    private Long id;
    private String name;

    public PermissionResponseBody(Permission entity){
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
