package dev.cironeto.accesscontrolservice.controller;

import dev.cironeto.accesscontrolservice.dto.ProfileBusinessFunctionPermissionRequestBody;
import dev.cironeto.accesscontrolservice.dto.ProfileBusinessFunctionPermissionResponseBody;
import dev.cironeto.accesscontrolservice.service.ProfileBusinessFunctionPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/profile-business-function-permission")
public class ProfileBusinessFunctionPermissionController {

    private final ProfileBusinessFunctionPermissionService profileBusinessFunctionPermissionService;

    @PostMapping(value = "/create")
    public ResponseEntity<ProfileBusinessFunctionPermissionResponseBody> createBusinessFunctionPermission(@RequestBody ProfileBusinessFunctionPermissionRequestBody dto){
        return ResponseEntity.ok(profileBusinessFunctionPermissionService.create(dto));
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<ProfileBusinessFunctionPermissionResponseBody>> findAll(){
        return ResponseEntity.ok(profileBusinessFunctionPermissionService.findAll());
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<ProfileBusinessFunctionPermissionResponseBody> findById(@PathVariable Long id){
        return ResponseEntity.ok(profileBusinessFunctionPermissionService.findById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProfileBusinessFunctionPermissionResponseBody> update(@PathVariable Long id, @RequestBody ProfileBusinessFunctionPermissionRequestBody dto){
        return ResponseEntity.ok(profileBusinessFunctionPermissionService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        profileBusinessFunctionPermissionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
