package dev.cironeto.accesscontrolservice.factory;

import dev.cironeto.accesscontrolservice.dto.BusinessFunctionPostRequestBody;
import dev.cironeto.accesscontrolservice.dto.PermissionPostRequestBody;
import dev.cironeto.accesscontrolservice.model.BusinessFunction;
import dev.cironeto.accesscontrolservice.model.Permission;

public class BusinessFunctionFactory {

    public static BusinessFunctionPostRequestBody createValidPostBusinessFunction(){
        BusinessFunctionPostRequestBody dto = new BusinessFunctionPostRequestBody();
        dto.setFunctionName("manager-test");
        dto.setApplicationName("App 1 - test");
        return dto;
    }

    public static BusinessFunction createBusinessFunctionToBeSaved(){
        BusinessFunction businessFunction = new BusinessFunction();
        businessFunction.setFunctionName("manager-test");
        businessFunction.setApplicationName("App 1 - test");
        return businessFunction;
    }

    public static BusinessFunctionPostRequestBody createNotValidPostBusinessFunction(){
        BusinessFunctionPostRequestBody dto = new BusinessFunctionPostRequestBody();
        dto.setFunctionName("manager-test");
        dto.setApplicationName("App 1 - test");
        return dto;
    }
}
