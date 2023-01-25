package dev.cironeto.accesscontrolservice.controller;

import dev.cironeto.accesscontrolservice.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/access-control")
public class AccessControlController {

    private final AppUserRepository appUserRepository;

    @GetMapping(value = "/validate")
    public ResponseEntity<String> validateAccess(
            @RequestParam(value = "applicationName", defaultValue = "") String applicationName,
            @RequestParam(value = "functionName", defaultValue = "") String functionName,
            @RequestParam(value = "permission", defaultValue = "") String permission
    ){
        String loggedUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        String userMail = appUserRepository.validateAccess(applicationName, functionName, permission, loggedUserId);

        if (userMail != null) {
            return new ResponseEntity<>("true", HttpStatus.OK);
        }
        return new ResponseEntity<>("false", HttpStatus.FORBIDDEN);
    }
}
