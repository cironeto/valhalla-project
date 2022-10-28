package dev.cironeto.accesscontrolservice.factory;

import dev.cironeto.accesscontrolservice.dto.PermissionPostRequestBody;

public class PermissionFactory {

    public static PermissionPostRequestBody createValidPostPermission(){
        PermissionPostRequestBody dto = new PermissionPostRequestBody();
        dto.setName("view-test");
        return dto;
    }
}
