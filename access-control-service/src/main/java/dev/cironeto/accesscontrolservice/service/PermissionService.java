package dev.cironeto.accesscontrolservice.service;

import dev.cironeto.accesscontrolservice.dto.PermissionPostRequestBody;
import dev.cironeto.accesscontrolservice.exception.BadRequestException;
import dev.cironeto.accesscontrolservice.model.Permission;
import dev.cironeto.accesscontrolservice.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public String save(PermissionPostRequestBody dto) {
        Permission permission = permissionRepository.findByName(dto.getName());
        if (permission != null){
            throw new BadRequestException(String.format("Permission already exists. ID: %d",  permission.getId()));
        }else {
            Permission entity = new Permission();
            entity.setName(dto.getName());

            Permission savedEntity = permissionRepository.save(entity);
            return String.format("Permission created. ID: %d",  savedEntity.getId());
        }
    }
}