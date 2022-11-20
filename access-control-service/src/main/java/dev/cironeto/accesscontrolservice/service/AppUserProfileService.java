package dev.cironeto.accesscontrolservice.service;

import dev.cironeto.accesscontrolservice.dto.AppUserProfileRequestBody;
import dev.cironeto.accesscontrolservice.dto.AppUserProfileResponseBody;
import dev.cironeto.accesscontrolservice.dto.BusinessFunctionPermissionRequestBody;
import dev.cironeto.accesscontrolservice.dto.BusinessFunctionPermissionResponseBody;
import dev.cironeto.accesscontrolservice.exception.BadRequestException;
import dev.cironeto.accesscontrolservice.exception.NotFoundException;
import dev.cironeto.accesscontrolservice.model.*;
import dev.cironeto.accesscontrolservice.repository.AppUserProfileRepository;
import dev.cironeto.accesscontrolservice.repository.AppUserRepository;
import dev.cironeto.accesscontrolservice.repository.ProfileRepository;
import dev.cironeto.accesscontrolservice.validation.BeanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppUserProfileService {

    private final AppUserProfileRepository repository;
    private final AppUserRepository appUserRepository;
    private final ProfileRepository profileRepository;

    public AppUserProfileResponseBody create(AppUserProfileRequestBody dto) {
        BeanValidator.validate(dto);

        if(!isAppUserExists(dto.getAppUserEmail())
                || !isProfileExists(dto.getProfileName())) {
            throw new NotFoundException("User and/or Profile does not exist");
        }

        AppUser appUser = appUserRepository.findByEmail(dto.getAppUserEmail());
        Profile profile = profileRepository.findByName(dto.getProfileName());

        AppUserProfile entity = repository.checkIfExists(appUser.getId().toString(), profile.getId());

        if (entity != null){
            throw new BadRequestException(String.format("User/Profile relationship already exists. ID: %d",  entity.getId()));

        }else {
            AppUserProfile entityToBeSaved = new AppUserProfile();
            entityToBeSaved.setAppUser(appUser);
            entityToBeSaved.setProfile(profile);

            AppUserProfile savedEntity = repository.save(entityToBeSaved);

            return new AppUserProfileResponseBody(savedEntity);
        }
    }

    private boolean isAppUserExists(String email) {
        AppUser entity = appUserRepository.findByEmail(email);
        if (entity != null){
            return true;
        }
        return false;
    }

    private boolean isProfileExists(String name) {
        Profile entity = profileRepository.findByName(name);
        if (entity != null){
            return true;
        }
        return false;
    }

    public List<AppUserProfileResponseBody> findAll(){
        List<AppUserProfile> list = repository.findAll();
        return list.stream().map(p -> new AppUserProfileResponseBody(p)).collect(Collectors.toList());
    }

    public AppUserProfileResponseBody findById(Long id){
        Optional<AppUserProfile> optional = repository.findById(id);
        AppUserProfile entity = optional.orElseThrow(() -> new BadRequestException("ID not found"));
        return new AppUserProfileResponseBody(entity);
    }

    public AppUserProfileResponseBody update(Long id, AppUserProfileRequestBody dto){
        if(!isAppUserExists(dto.getAppUserEmail())
            || !isProfileExists(dto.getProfileName())) {
            throw new NotFoundException("User and/or Profile does not exist");
        }

        try {
            AppUserProfile entity = repository.getById(id);

            AppUser appUser = appUserRepository.findByEmail(dto.getAppUserEmail());
            Profile profile = profileRepository.findByName(dto.getProfileName());

            entity.setAppUser(appUser);
            entity.setProfile(profile);

            AppUserProfile savedEntity = repository.save(entity);
            return new AppUserProfileResponseBody(savedEntity);
        } catch (Exception e){
            throw new BadRequestException("ID not found");
        }
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        } catch (Exception e){
            throw new BadRequestException("ID not found");
        }
    }
}
