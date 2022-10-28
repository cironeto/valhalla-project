package dev.cironeto.accesscontrolservice.controller;

import dev.cironeto.accesscontrolservice.dto.BusinessFunctionPostRequestBody;
import dev.cironeto.accesscontrolservice.dto.BusinessFunctionResponseBody;
import dev.cironeto.accesscontrolservice.service.BusinessFunctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business-function")
public class BusinessFunctionController {

    private final BusinessFunctionService businessFunctionService;

    @PostMapping(value = "/create")
    public ResponseEntity<BusinessFunctionResponseBody> createBusinessFunction(@RequestBody BusinessFunctionPostRequestBody dto){
        return ResponseEntity.ok(businessFunctionService.create(dto));
    }
}
