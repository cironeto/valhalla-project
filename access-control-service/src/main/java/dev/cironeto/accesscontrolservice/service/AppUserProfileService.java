package dev.cironeto.accesscontrolservice.service;

import dev.cironeto.accesscontrolservice.dto.AppUserProfileRequestBody;
import dev.cironeto.accesscontrolservice.dto.AppUserProfileResponseBody;
import dev.cironeto.accesscontrolservice.exception.BadRequestException;
import dev.cironeto.accesscontrolservice.exception.NotFoundException;
import dev.cironeto.accesscontrolservice.model.AppUser;
import dev.cironeto.accesscontrolservice.model.AppUserProfile;
import dev.cironeto.accesscontrolservice.model.Profile;
import dev.cironeto.accesscontrolservice.repository.AppUserProfileRepository;
import dev.cironeto.accesscontrolservice.repository.AppUserRepository;
import dev.cironeto.accesscontrolservice.repository.ProfileRepository;
import dev.cironeto.accesscontrolservice.validation.BeanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppUserProfileService {

    private final AppUserProfileRepository repository;
    private final AppUserRepository appUserRepository;
    private final ProfileRepository profileRepository;

    public AppUserProfileResponseBody create(AppUserProfileRequestBody dto) {
        BeanValidator.validate(dto);

        AppUser appUser = appUserRepository
                .findById(dto.getAppUserId()).orElseThrow(()-> new NotFoundException("User does not exist"));
        Profile profile = profileRepository
                .findById(dto.getProfileID()).orElseThrow(() -> new NotFoundException("Profile does not exist"));

        AppUserProfile entity = repository.checkIfExists(appUser.getId().toString(), profile.getId());

        if (entity != null){
            throw new BadRequestException(String.format("User/Profile relationship already exists. ID: %d",  entity.getId()));
        }

        AppUserProfile newEntity = new AppUserProfile();
        newEntity.setAppUser(appUser);
        newEntity.setProfile(profile);

        AppUserProfile savedEntity = repository.save(newEntity);

        return new AppUserProfileResponseBody(savedEntity);
    }

    public List<AppUserProfileResponseBody> findAll(){
        List<AppUserProfile> list = repository.findAll();
        return list.stream().map(p -> new AppUserProfileResponseBody(p)).collect(Collectors.toList());
    }

    public AppUserProfileResponseBody findById(Long id){
        AppUserProfile entity = repository.findById(id).orElseThrow(() -> new BadRequestException("ID not found"));
        return new AppUserProfileResponseBody(entity);
    }

    public AppUserProfileResponseBody update(Long id, AppUserProfileRequestBody dto){
        try {
            AppUserProfile entity = repository.getById(id);

            AppUser appUser = appUserRepository
                    .findById(dto.getAppUserId()).orElseThrow(()-> new NotFoundException("User does not exist"));
            Profile profile = profileRepository
                    .findById(dto.getProfileID()).orElseThrow(() -> new NotFoundException("Profile does not exist"));

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
