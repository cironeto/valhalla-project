package dev.cironeto.accesscontrolservice.controller;

import dev.cironeto.accesscontrolservice.dto.BusinessFunctionPermissionRequestBody;
import dev.cironeto.accesscontrolservice.dto.BusinessFunctionPostRequestBody;
import dev.cironeto.accesscontrolservice.model.BusinessFunctionPermission;
import dev.cironeto.accesscontrolservice.service.BusinessFunctionPermissionService;
import dev.cironeto.accesscontrolservice.service.BusinessFunctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business-function-permission")
public class BusinessFunctionPermissionController {

    private final BusinessFunctionPermissionService businessFunctionPermissionService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createBusinessFunctionPermission(@RequestBody BusinessFunctionPermissionRequestBody dto){
        return ResponseEntity.ok(businessFunctionPermissionService.create(dto));
    }
}
