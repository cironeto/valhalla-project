package dev.cironeto.accesscontrolservice.service;

import dev.cironeto.accesscontrolservice.dto.ProfileBusinessFunctionPermissionRequestBody;
import dev.cironeto.accesscontrolservice.dto.ProfileBusinessFunctionPermissionResponseBody;
import dev.cironeto.accesscontrolservice.exception.BadRequestException;
import dev.cironeto.accesscontrolservice.exception.NotFoundException;
import dev.cironeto.accesscontrolservice.model.*;
import dev.cironeto.accesscontrolservice.repository.*;
import dev.cironeto.accesscontrolservice.validation.BeanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

        validateIfExists(dto);

        BusinessFunction businessFunction =
                businessFunctionRepository.findByNameAndFunction(dto.getApplicationName(), dto.getFunctionName());
        Permission permission =
                permissionRepository.findByName(dto.getPermission());

        BusinessFunctionPermission businessFunctionPermission = businessFunctionPermissionRepository.checkIfExists(businessFunction.getId(), permission.getId());
        if (businessFunctionPermission == null){
            throw new BadRequestException("Can not find Business Function/Permission");
        }

        Profile profile = profileRepository.findByName(dto.getProfileName());
        if(profile == null){
            throw new BadRequestException("Can not find Profile");
        }

        ProfileBusinessFunctionPermission entityToBeSaved = new ProfileBusinessFunctionPermission();
        entityToBeSaved.setBusinessFunctionPermission(businessFunctionPermission);
        entityToBeSaved.setProfile(profile);

        ProfileBusinessFunctionPermission entity = repository.save(entityToBeSaved);

        return new ProfileBusinessFunctionPermissionResponseBody(entity);

    }

    private void validateIfExists(ProfileBusinessFunctionPermissionRequestBody dto) {
        if(!isBusinessFunctionExists(dto.getApplicationName(), dto.getFunctionName())) {
            throw new NotFoundException("Business Function does not exist");
        }
        if(!isPermissionExists(dto.getPermission())) {
            throw new NotFoundException("Permission does not exist");
        }
        if(!isProfileExists(dto.getProfileName())) {
            throw new NotFoundException("Profile does not exist");
        }
    }

    private boolean isBusinessFunctionExists(String applicationName, String functionName) {
        BusinessFunction entity = businessFunctionRepository.findByNameAndFunction(applicationName, functionName);
        if (entity != null){
            return true;
        }
        return false;
    }

    private boolean isPermissionExists(String name) {
        Permission entity = permissionRepository.findByName(name);
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

    public List<ProfileBusinessFunctionPermissionResponseBody> findAll(){
        List<ProfileBusinessFunctionPermission> list = repository.findAll();
        return list.stream().map(p -> new ProfileBusinessFunctionPermissionResponseBody(p)).collect(Collectors.toList());
    }

    public ProfileBusinessFunctionPermissionResponseBody findById(Long id){
        Optional<ProfileBusinessFunctionPermission> optional = repository.findById(id);
        ProfileBusinessFunctionPermission entity = optional.orElseThrow(() -> new BadRequestException("ID not found"));
        return new ProfileBusinessFunctionPermissionResponseBody(entity);
    }

    public ProfileBusinessFunctionPermissionResponseBody update(Long id, ProfileBusinessFunctionPermissionRequestBody dto){
    // TODO
    return null;
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        } catch (Exception e){
            throw new BadRequestException("ID not found");
        }
    }
}
