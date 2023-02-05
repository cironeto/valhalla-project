package dev.cironeto.accesscontrolservice.controller;

import dev.cironeto.accesscontrolservice.dto.BusinessFunctionRequestBody;
import dev.cironeto.accesscontrolservice.dto.BusinessFunctionResponseBody;
import dev.cironeto.accesscontrolservice.service.BusinessFunctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business-function")
public class BusinessFunctionController {

    private final BusinessFunctionService businessFunctionService;

    @PostMapping(value = "/create")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'create')")
    public ResponseEntity<BusinessFunctionResponseBody> createBusinessFunction(@RequestBody BusinessFunctionRequestBody dto){
        return ResponseEntity.ok(businessFunctionService.create(dto));
    }

    @GetMapping(value = "/find/all")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'view')")
    public ResponseEntity<List<BusinessFunctionResponseBody>> findAll(){
        return ResponseEntity.ok(businessFunctionService.findAll());
    }

    @GetMapping(value = "/find/{id}")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'view')")
    public ResponseEntity<BusinessFunctionResponseBody> findById(@PathVariable Long id){
        return ResponseEntity.ok(businessFunctionService.findById(id));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'edit')")
    public ResponseEntity<BusinessFunctionResponseBody> update(@PathVariable Long id, @RequestBody BusinessFunctionRequestBody dto){
        return ResponseEntity.ok(businessFunctionService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'delete')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        businessFunctionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
