package dev.cironeto.accesscontrolservice.controller;

import dev.cironeto.accesscontrolservice.dto.BusinessFunctionPermissionRequestBody;
import dev.cironeto.accesscontrolservice.dto.BusinessFunctionPermissionResponseBody;
import dev.cironeto.accesscontrolservice.service.BusinessFunctionPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business-function-permission")
public class BusinessFunctionPermissionController {

    private final BusinessFunctionPermissionService businessFunctionPermissionService;

    @PostMapping(value = "/create")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'create')")
    public ResponseEntity<BusinessFunctionPermissionResponseBody> createBusinessFunctionPermission(@RequestBody BusinessFunctionPermissionRequestBody dto){
        return ResponseEntity.ok(businessFunctionPermissionService.create(dto));
    }

    @GetMapping(value = "/find/all")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'view')")
    public ResponseEntity<List<BusinessFunctionPermissionResponseBody>> findAll(){
        return ResponseEntity.ok(businessFunctionPermissionService.findAll());
    }

    @GetMapping(value = "/find/{id}")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'view')")
    public ResponseEntity<BusinessFunctionPermissionResponseBody> findById(@PathVariable Long id){
        return ResponseEntity.ok(businessFunctionPermissionService.findById(id));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'edit')")
    public ResponseEntity<BusinessFunctionPermissionResponseBody> update(@PathVariable Long id, @RequestBody BusinessFunctionPermissionRequestBody dto){
        return ResponseEntity.ok(businessFunctionPermissionService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'delete')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        businessFunctionPermissionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
