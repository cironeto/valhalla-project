package dev.cironeto.accesscontrolservice.factory;

import dev.cironeto.accesscontrolservice.dto.PermissionPostRequestBody;
import dev.cironeto.accesscontrolservice.model.Permission;

public class PermissionFactory {

    public static PermissionPostRequestBody createValidPostPermission(){
        PermissionPostRequestBody dto = new PermissionPostRequestBody();
        dto.setName("view-test");
        return dto;
    }

    public static Permission createPermissionToBeSaved(){
        Permission permission = new Permission();
        permission.setName("Permission-Test");
        return permission;
    }

    public static PermissionPostRequestBody createNotValidPostPermission(){
        PermissionPostRequestBody dto = new PermissionPostRequestBody();
        dto.setName("Permission-Test");
        return dto;
    }

}
