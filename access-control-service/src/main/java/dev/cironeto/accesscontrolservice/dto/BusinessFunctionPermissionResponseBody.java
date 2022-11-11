package dev.cironeto.accesscontrolservice.dto;

import dev.cironeto.accesscontrolservice.model.BusinessFunctionPermission;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BusinessFunctionPermissionResponseBody {

    private Long id;
    private String applicationName;
    private String functionName;
    private String permission;

    public BusinessFunctionPermissionResponseBody(BusinessFunctionPermission entity){
        this.id = entity.getId();
        this.applicationName = entity.getBusinessFunction().getApplicationName();
        this.functionName = entity.getBusinessFunction().getFunctionName();
        this.permission = entity.getPermission().getName();
    }
}
