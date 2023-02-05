package dev.cironeto.accesscontrolservice.controller;

import dev.cironeto.accesscontrolservice.dto.PermissionRequestBody;
import dev.cironeto.accesscontrolservice.dto.PermissionResponseBody;
import dev.cironeto.accesscontrolservice.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/permission")
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping(value = "/create")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'create')")
    public ResponseEntity<PermissionResponseBody> createPermission(@RequestBody PermissionRequestBody dto){
        return ResponseEntity.ok(permissionService.create(dto));
    }

    @GetMapping(value = "/find/all")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'view')")
    public ResponseEntity<List<PermissionResponseBody>> findAll(){
        return ResponseEntity.ok(permissionService.findAll());
    }

    @GetMapping(value = "/find/{id}")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'view')")
    public ResponseEntity<PermissionResponseBody> findById(@PathVariable Long id){
        return ResponseEntity.ok(permissionService.findById(id));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'edit')")
    public ResponseEntity<PermissionResponseBody> update(@PathVariable Long id, @RequestBody PermissionRequestBody dto){
        return ResponseEntity.ok(permissionService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'delete')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        permissionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
