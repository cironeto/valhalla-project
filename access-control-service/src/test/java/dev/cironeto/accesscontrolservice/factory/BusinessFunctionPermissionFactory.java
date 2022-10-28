package dev.cironeto.accesscontrolservice.factory;

import dev.cironeto.accesscontrolservice.dto.BusinessFunctionPermissionRequestBody;

public class BusinessFunctionPermissionFactory {

    public static BusinessFunctionPermissionRequestBody createValidPostBusinessFunctionPermission(){
        BusinessFunctionPermissionRequestBody dto = new BusinessFunctionPermissionRequestBody();
        dto.setFunctionName("function-test");
        dto.setPermission("permission-test");
        dto.setApplicationName("App-test");
        return dto;
    }
}
