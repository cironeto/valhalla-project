package dev.cironeto.accesscontrolservice.service;

import dev.cironeto.accesscontrolservice.dto.AppUserPostRequestBody;
import dev.cironeto.accesscontrolservice.dto.AppUserRequestBody;
import dev.cironeto.accesscontrolservice.dto.PermissionRequestBody;
import dev.cironeto.accesscontrolservice.dto.PermissionResponseBody;
import dev.cironeto.accesscontrolservice.exception.BadRequestException;
import dev.cironeto.accesscontrolservice.model.AppUser;
import dev.cironeto.accesscontrolservice.model.Permission;
import dev.cironeto.accesscontrolservice.repository.PermissionRepository;
import dev.cironeto.accesscontrolservice.validation.BeanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionResponseBody create(PermissionRequestBody dto) {
        BeanValidator.validate(dto);

        Permission permission = permissionRepository.findByName(dto.getName());
        if (permission != null){
            throw new BadRequestException(String.format("Permission already exists. ID: %d",  permission.getId()));
        }else {
            Permission entity = new Permission();
            entity.setName(dto.getName());

            Permission savedEntity = permissionRepository.save(entity);
            return new PermissionResponseBody(savedEntity);
        }
    }

    public List<PermissionRequestBody> findAll(){
        List<Permission> permissions = permissionRepository.findAll();
        return permissions.stream().map(p -> new PermissionRequestBody(p)).collect(Collectors.toList());
    }

    public PermissionRequestBody findById(Long id){
        Optional<Permission> permissionOptional = permissionRepository.findById(id);
        Permission permission = permissionOptional.orElseThrow(() -> new BadRequestException("ID not found"));
        return new PermissionRequestBody(permission);
    }

    public PermissionRequestBody update(Long id, PermissionRequestBody dto){
        try {
            Permission permission = permissionRepository.getById(id);
            permission.setName(dto.getName());
            Permission permissionSaved = permissionRepository.save(permission);
            return new PermissionRequestBody(permissionSaved);
        } catch (Exception e){
            throw new BadRequestException("ID not found");
        }
    }

    public void delete(Long id){
        try {
            permissionRepository.deleteById(id);
        } catch (Exception e){
            throw new BadRequestException("ID not found");
        }
    }
}