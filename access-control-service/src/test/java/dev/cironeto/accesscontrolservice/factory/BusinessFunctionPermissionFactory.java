package dev.cironeto.accesscontrolservice.factory;

import dev.cironeto.accesscontrolservice.dto.BusinessFunctionPermissionRequestBody;

public class BusinessFunctionPermissionFactory {

    public static BusinessFunctionPermissionRequestBody createValidPostBusinessFunctionPermission(){
        BusinessFunctionPermissionRequestBody dto = new BusinessFunctionPermissionRequestBody();
        dto.setPermissionId(1L);
        dto.setBusinessFunctionId(1L);
        return dto;
    }

    public static BusinessFunctionPermissionRequestBody createEmptyPostBusinessFunctionPermissionObject(){
        return new BusinessFunctionPermissionRequestBody();
    }
}
