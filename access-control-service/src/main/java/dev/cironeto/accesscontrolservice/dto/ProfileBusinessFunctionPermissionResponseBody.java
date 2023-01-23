package dev.cironeto.accesscontrolservice.dto;

import dev.cironeto.accesscontrolservice.model.ProfileBusinessFunctionPermission;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileBusinessFunctionPermissionResponseBody {

    private long id;
    private String applicationName;
    private String functionName;
    private String permission;
    private String profileName;

    public ProfileBusinessFunctionPermissionResponseBody(ProfileBusinessFunctionPermission entity){
        this.id = entity.getId();
        this.applicationName = entity.getBusinessFunctionPermission().getBusinessFunction().getApplicationName();
        this.functionName = entity.getBusinessFunctionPermission().getBusinessFunction().getFunctionName();
        this.permission = entity.getBusinessFunctionPermission().getPermission().getName();
        this.profileName = entity.getProfile().getName();
    }
}