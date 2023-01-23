package dev.cironeto.accesscontrolservice.service;

import dev.cironeto.accesscontrolservice.dto.ProfileBusinessFunctionPermissionRequestBody;
import dev.cironeto.accesscontrolservice.dto.ProfileBusinessFunctionPermissionResponseBody;
import dev.cironeto.accesscontrolservice.exception.BadRequestException;
import dev.cironeto.accesscontrolservice.exception.NotFoundException;
import dev.cironeto.accesscontrolservice.model.BusinessFunctionPermission;
import dev.cironeto.accesscontrolservice.model.Profile;
import dev.cironeto.accesscontrolservice.model.ProfileBusinessFunctionPermission;
import dev.cironeto.accesscontrolservice.repository.*;
import dev.cironeto.accesscontrolservice.validation.BeanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileBusinessFunctionPermissionService {

    private final ProfileBusinessFunctionPermissionRepository repository;
    private final BusinessFunctionPermissionRepository businessFunctionPermissionRepository;
    private final BusinessFunctionRepository businessFunctionRepository;
    private final PermissionRepository permissionRepository;
    private final ProfileRepository profileRepository;

    public ProfileBusinessFunctionPermissionResponseBody create(ProfileBusinessFunctionPermissionRequestBody dto) {
        BeanValidator.validate(dto);

        BusinessFunctionPermission businessFunctionPermission = businessFunctionPermissionRepository
                .findById(dto.getBusinessFunctionPermissionId())
                .orElseThrow(() -> new NotFoundException("Business Function Permission does not exist"));

        Profile profile = profileRepository
                .findById(dto.getProfileId())
                .orElseThrow(() -> new NotFoundException("Profile does not exist"));

        ProfileBusinessFunctionPermission entityToBeSaved = new ProfileBusinessFunctionPermission();
        entityToBeSaved.setBusinessFunctionPermission(businessFunctionPermission);
        entityToBeSaved.setProfile(profile);

        ProfileBusinessFunctionPermission entity = repository.save(entityToBeSaved);

        return new ProfileBusinessFunctionPermissionResponseBody(entity);

    }

    public List<ProfileBusinessFunctionPermissionResponseBody> findAll(){
        List<ProfileBusinessFunctionPermission> list = repository.findAll();
        return list.stream().map(p -> new ProfileBusinessFunctionPermissionResponseBody(p)).collect(Collectors.toList());
    }

    public ProfileBusinessFunctionPermissionResponseBody findById(Long id){
        ProfileBusinessFunctionPermission entity = repository.findById(id).orElseThrow(() -> new BadRequestException("ID not found"));
        return new ProfileBusinessFunctionPermissionResponseBody(entity);
    }

    public ProfileBusinessFunctionPermissionResponseBody update(Long id, ProfileBusinessFunctionPermissionRequestBody dto){
        try {
            ProfileBusinessFunctionPermission entity = repository.getById(id);
            BusinessFunctionPermission businessFunctionPermission = businessFunctionPermissionRepository
                    .findById(dto.getBusinessFunctionPermissionId())
                    .orElseThrow(() -> new NotFoundException("Business Function Permission does not exist"));

            Profile profile = profileRepository
                    .findById(dto.getProfileId())
                    .orElseThrow(() -> new NotFoundException("Profile does not exist"));

            entity.setProfile(profile);
            entity.setBusinessFunctionPermission(businessFunctionPermission);

            ProfileBusinessFunctionPermission savedEntity = repository.save(entity);
            return new ProfileBusinessFunctionPermissionResponseBody(savedEntity);
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
