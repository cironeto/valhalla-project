package dev.cironeto.accesscontrolservice.factory;

import dev.cironeto.accesscontrolservice.dto.BusinessFunctionRequestBody;
import dev.cironeto.accesscontrolservice.model.BusinessFunction;

public class BusinessFunctionFactory {

    public static BusinessFunctionRequestBody createValidPostBusinessFunction(){
        BusinessFunctionRequestBody dto = new BusinessFunctionRequestBody();
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

    public static BusinessFunctionRequestBody createNotValidPostBusinessFunction(){
        BusinessFunctionRequestBody dto = new BusinessFunctionRequestBody();
        dto.setFunctionName("manager-test");
        dto.setApplicationName("App 1 - test");
        return dto;
    }
}
