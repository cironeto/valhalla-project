package dev.cironeto.accesscontrolservice.factory;

import dev.cironeto.accesscontrolservice.dto.BusinessFunctionPostRequestBody;

public class BusinessFunctionFactory {

    public static BusinessFunctionPostRequestBody createValidPostBusinessFunction(){
        BusinessFunctionPostRequestBody dto = new BusinessFunctionPostRequestBody();
        dto.setFunctionName("manager-test");
        dto.setApplicationName("App 1 - test");
        return dto;
    }
}
