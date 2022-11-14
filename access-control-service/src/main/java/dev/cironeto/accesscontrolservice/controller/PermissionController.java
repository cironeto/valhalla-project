package dev.cironeto.accesscontrolservice.controller;

import dev.cironeto.accesscontrolservice.dto.PermissionRequestBody;
import dev.cironeto.accesscontrolservice.dto.PermissionResponseBody;
import dev.cironeto.accesscontrolservice.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/permission")
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping(value = "/create")
    public ResponseEntity<PermissionResponseBody> createPermission(@RequestBody PermissionRequestBody dto){
        return ResponseEntity.ok(permissionService.create(dto));
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<PermissionResponseBody>> findAll(){
        return ResponseEntity.ok(permissionService.findAll());
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<PermissionResponseBody> findById(@PathVariable Long id){
        return ResponseEntity.ok(permissionService.findById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PermissionResponseBody> update(@PathVariable Long id, @RequestBody PermissionRequestBody dto){
        return ResponseEntity.ok(permissionService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        permissionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
