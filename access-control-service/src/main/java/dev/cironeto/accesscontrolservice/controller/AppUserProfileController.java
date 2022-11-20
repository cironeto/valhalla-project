package dev.cironeto.accesscontrolservice.controller;

import dev.cironeto.accesscontrolservice.dto.AppUserProfileRequestBody;
import dev.cironeto.accesscontrolservice.dto.AppUserProfileResponseBody;
import dev.cironeto.accesscontrolservice.service.AppUserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user-profile")
public class AppUserProfileController {

    private final AppUserProfileService appUserProfileService;

    @PostMapping(value = "/create")
    public ResponseEntity<AppUserProfileResponseBody> createBusinessFunctionPermission(@RequestBody AppUserProfileRequestBody dto){
        return ResponseEntity.ok(appUserProfileService.create(dto));
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<AppUserProfileResponseBody>> findAll(){
        return ResponseEntity.ok(appUserProfileService.findAll());
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<AppUserProfileResponseBody> findById(@PathVariable Long id){
        return ResponseEntity.ok(appUserProfileService.findById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AppUserProfileResponseBody> update(@PathVariable Long id, @RequestBody AppUserProfileRequestBody dto){
        return ResponseEntity.ok(appUserProfileService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        appUserProfileService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
