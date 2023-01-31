package dev.cironeto.accesscontrolservice.controller;

import dev.cironeto.accesscontrolservice.service.AccessControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/access-control")
public class AccessControlController {

    private final AccessControlService accessControlService;

    @GetMapping(value = "/validate")
    public ResponseEntity<String> validateAccess(
            @RequestParam(value = "functionName", defaultValue = "") String functionName,
            @RequestParam(value = "permission", defaultValue = "") String permission
    ){
        boolean access = accessControlService.validateAccess(functionName, permission);

        if (access) {
            return new ResponseEntity<>("true", HttpStatus.OK);
        }
        return new ResponseEntity<>("false", HttpStatus.FORBIDDEN);
    }
}
