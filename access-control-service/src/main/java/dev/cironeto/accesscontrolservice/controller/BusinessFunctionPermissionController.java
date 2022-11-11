package dev.cironeto.accesscontrolservice.controller;

import dev.cironeto.accesscontrolservice.dto.BusinessFunctionPermissionRequestBody;
import dev.cironeto.accesscontrolservice.dto.BusinessFunctionPermissionResponseBody;
import dev.cironeto.accesscontrolservice.dto.BusinessFunctionPostRequestBody;
import dev.cironeto.accesscontrolservice.dto.BusinessFunctionResponseBody;
import dev.cironeto.accesscontrolservice.model.BusinessFunctionPermission;
import dev.cironeto.accesscontrolservice.service.BusinessFunctionPermissionService;
import dev.cironeto.accesscontrolservice.service.BusinessFunctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business-function-permission")
public class BusinessFunctionPermissionController {

    private final BusinessFunctionPermissionService businessFunctionPermissionService;

    @PostMapping(value = "/create")
    public ResponseEntity<BusinessFunctionPermissionResponseBody> createBusinessFunctionPermission(@RequestBody BusinessFunctionPermissionRequestBody dto){
        return ResponseEntity.ok(businessFunctionPermissionService.create(dto));
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<BusinessFunctionPermissionResponseBody>> findAll(){
        return ResponseEntity.ok(businessFunctionPermissionService.findAll());
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<BusinessFunctionPermissionResponseBody> findById(@PathVariable Long id){
        return ResponseEntity.ok(businessFunctionPermissionService.findById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BusinessFunctionPermissionResponseBody> update(@PathVariable Long id, @RequestBody BusinessFunctionPermissionRequestBody dto){
        return ResponseEntity.ok(businessFunctionPermissionService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        businessFunctionPermissionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
