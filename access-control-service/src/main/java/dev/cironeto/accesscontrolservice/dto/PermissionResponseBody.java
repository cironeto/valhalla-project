package dev.cironeto.accesscontrolservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
public class PermissionResponseBody {

    private Long permissionCreatedId;

    public PermissionResponseBody(Long id){
        this.permissionCreatedId = id;
    }
}
