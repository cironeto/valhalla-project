package dev.cironeto.accesscontrolservice.controller;

import dev.cironeto.accesscontrolservice.dto.PermissionPostRequestBody;
import dev.cironeto.accesscontrolservice.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/permission")
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createPermission(@RequestBody PermissionPostRequestBody dto){
        return ResponseEntity.ok(permissionService.save(dto));
    }
}
