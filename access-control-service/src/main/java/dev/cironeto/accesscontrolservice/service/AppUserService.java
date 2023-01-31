package dev.cironeto.accesscontrolservice.service;

import dev.cironeto.accesscontrolservice.dto.AppUserPostRequestBody;
import dev.cironeto.accesscontrolservice.dto.AppUserRequestBody;
import dev.cironeto.accesscontrolservice.exception.BadRequestException;
import dev.cironeto.accesscontrolservice.model.AppUser;
import dev.cironeto.accesscontrolservice.repository.AppUserRepository;
import dev.cironeto.accesscontrolservice.validation.BeanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AppUser save(UUID keycloakId, AppUserPostRequestBody userDto){
        BeanValidator.validate(userDto);

        AppUser appUser = new AppUser();
        appUser.setId(keycloakId);
        appUser.setEmail(userDto.getEmail());
        appUser.setFirstName(userDto.getFirstName());
        appUser.setLastName(userDto.getLastName());
        appUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        appUser.setApplicationName(userDto.getApplicationName());
        AppUser savedAppUser = appUserRepository.save(appUser);
        return savedAppUser;
    }

    public List<AppUserRequestBody> findAll(){
        List<AppUser> appUsers = appUserRepository.findAll();
        return appUsers.stream().map(u -> new AppUserRequestBody(u)).collect(Collectors.toList());
    }

    public AppUserRequestBody findById(UUID id){
        Optional<AppUser> userOptional = appUserRepository.findById(id);
        AppUser appUser = userOptional.orElseThrow(() -> new BadRequestException("ID not found"));
        return new AppUserRequestBody(appUser);
    }

    public AppUserRequestBody update(UUID id, AppUserPostRequestBody dto){
        try {
            AppUser appUser = appUserRepository.getById(id);
            appUser.setEmail(dto.getEmail());
            appUser.setPassword(dto.getPassword());
            appUser.setFirstName(dto.getFirstName());
            appUser.setLastName(dto.getLastName());
            appUser.setApplicationName(dto.getApplicationName());
            AppUser appUserSaved = appUserRepository.save(appUser);
            return new AppUserRequestBody(appUserSaved);
        } catch (Exception e){
            throw new BadRequestException("ID not found");
        }
    }

    public void delete(UUID id){
        try {
            appUserRepository.deleteById(id);
        } catch (Exception e){
            throw new BadRequestException("ID not found");
        }
    }
}
