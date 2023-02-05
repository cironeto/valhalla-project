package dev.cironeto.accesscontrolservice.controller;

import dev.cironeto.accesscontrolservice.dto.AppUserProfileRequestBody;
import dev.cironeto.accesscontrolservice.dto.AppUserProfileResponseBody;
import dev.cironeto.accesscontrolservice.service.AppUserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user-profile")
public class AppUserProfileController {

    private final AppUserProfileService appUserProfileService;

    @PostMapping(value = "/create")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'create')")
    public ResponseEntity<AppUserProfileResponseBody> createBusinessFunctionPermission(@RequestBody AppUserProfileRequestBody dto){
        return ResponseEntity.ok(appUserProfileService.create(dto));
    }

    @GetMapping(value = "/find/all")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'view')")
    public ResponseEntity<List<AppUserProfileResponseBody>> findAll(){
        return ResponseEntity.ok(appUserProfileService.findAll());
    }

    @GetMapping(value = "/find/{id}")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'view')")
    public ResponseEntity<AppUserProfileResponseBody> findById(@PathVariable Long id){
        return ResponseEntity.ok(appUserProfileService.findById(id));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'edit')")
    public ResponseEntity<AppUserProfileResponseBody> update(@PathVariable Long id, @RequestBody AppUserProfileRequestBody dto){
        return ResponseEntity.ok(appUserProfileService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'delete')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        appUserProfileService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
