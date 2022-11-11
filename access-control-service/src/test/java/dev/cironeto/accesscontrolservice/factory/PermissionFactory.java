package dev.cironeto.accesscontrolservice.factory;

import dev.cironeto.accesscontrolservice.dto.PermissionRequestBody;
import dev.cironeto.accesscontrolservice.model.Permission;

public class PermissionFactory {

    public static PermissionRequestBody createValidPostPermission(){
        PermissionRequestBody dto = new PermissionRequestBody();
        dto.setName("view-test");
        return dto;
    }

    public static Permission createPermissionToBeSaved(){
        Permission permission = new Permission();
        permission.setName("Permission-Test");
        return permission;
    }

    public static PermissionRequestBody createNotValidPostPermission(){
        PermissionRequestBody dto = new PermissionRequestBody();
        dto.setName("Permission-Test");
        return dto;
    }

}
