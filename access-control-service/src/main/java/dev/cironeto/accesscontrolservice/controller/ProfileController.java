package dev.cironeto.accesscontrolservice.controller;

import dev.cironeto.accesscontrolservice.dto.ProfileRequestBody;
import dev.cironeto.accesscontrolservice.dto.ProfileResponseBody;
import dev.cironeto.accesscontrolservice.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/profile")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping(value = "/create")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'create')")
    public ResponseEntity<ProfileResponseBody> createPermission(@RequestBody ProfileRequestBody dto){
        return ResponseEntity.ok(profileService.create(dto));
    }

    @GetMapping(value = "/find/all")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'view')")
    public ResponseEntity<List<ProfileResponseBody>> findAll(){
        return ResponseEntity.ok(profileService.findAll());
    }

    @GetMapping(value = "/find/{id}")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'view')")
    public ResponseEntity<ProfileResponseBody> findById(@PathVariable Long id){
        return ResponseEntity.ok(profileService.findById(id));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'edit')")
    public ResponseEntity<ProfileResponseBody> update(@PathVariable Long id, @RequestBody ProfileRequestBody dto){
        return ResponseEntity.ok(profileService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("@accessControlService.validateAccess('view-content', 'delete')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        profileService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
