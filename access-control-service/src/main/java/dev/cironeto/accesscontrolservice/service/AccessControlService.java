package dev.cironeto.accesscontrolservice.service;

import dev.cironeto.accesscontrolservice.exception.BadRequestException;
import dev.cironeto.accesscontrolservice.model.AppUser;
import dev.cironeto.accesscontrolservice.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("accessControlService")
@RequiredArgsConstructor
public class AccessControlService {

    private final AppUserRepository appUserRepository;

    public boolean validateAccess(String functionName, String permission){
        String loggedUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        UUID uuid = UUID.fromString(loggedUserId);
        AppUser appUser = appUserRepository.findById(uuid).orElseThrow(() -> new BadRequestException("ID not found"));

        String applicationName = appUser.getApplicationName();

        String userMail = appUserRepository.hasPermission(applicationName, functionName, permission, loggedUserId);

        if (userMail != null) {
            return true;
        }
        return false;
    }
}
