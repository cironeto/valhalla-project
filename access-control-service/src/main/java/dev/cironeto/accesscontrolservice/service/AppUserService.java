package dev.cironeto.accesscontrolservice.service;

import dev.cironeto.accesscontrolservice.dto.UserPostRequestBody;
import dev.cironeto.accesscontrolservice.model.AppUser;
import dev.cironeto.accesscontrolservice.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AppUser saveUser(UUID keycloakId, UserPostRequestBody userDto){
        AppUser user = new AppUser();
        user.setId(keycloakId);
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        AppUser savedUser = appUserRepository.save(user);
        return savedUser;
    }

}
