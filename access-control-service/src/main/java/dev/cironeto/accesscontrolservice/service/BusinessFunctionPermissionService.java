package dev.cironeto.accesscontrolservice.service;

import dev.cironeto.accesscontrolservice.dto.BusinessFunctionPermissionRequestBody;
import dev.cironeto.accesscontrolservice.dto.BusinessFunctionPermissionResponseBody;
import dev.cironeto.accesscontrolservice.exception.BadRequestException;
import dev.cironeto.accesscontrolservice.exception.NotFoundException;
import dev.cironeto.accesscontrolservice.model.BusinessFunction;
import dev.cironeto.accesscontrolservice.model.BusinessFunctionPermission;
import dev.cironeto.accesscontrolservice.model.Permission;
import dev.cironeto.accesscontrolservice.repository.BusinessFunctionPermissionRepository;
import dev.cironeto.accesscontrolservice.repository.BusinessFunctionRepository;
import dev.cironeto.accesscontrolservice.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessFunctionPermissionService {

    private final BusinessFunctionPermissionRepository repository;
    private final BusinessFunctionRepository businessFunctionRepository;
    private final PermissionRepository permissionRepository;

    public BusinessFunctionPermissionResponseBody create(BusinessFunctionPermissionRequestBody dto) {
        if(!isBusinessFunctionExists(dto.getApplicationName(), dto.getFunctionName())
         || !isPermissionExists(dto.getPermission())) {
            throw new NotFoundException("Business Function and/or Permission does not exist");
        }

        BusinessFunction businessFunction =
                businessFunctionRepository.findByNameAndFunction(dto.getApplicationName(), dto.getFunctionName());
        Permission permission =
                permissionRepository.findByName(dto.getPermission());

        BusinessFunctionPermission entity = repository.checkIfExists(businessFunction.getId(), permission.getId());
        if (entity != null){
            throw new BadRequestException(String.format("Business Function/Permission relationship already exists. ID: %d",  entity.getId()));

        }else {
            BusinessFunctionPermission entityToBeSaved = new BusinessFunctionPermission();
            entityToBeSaved.setBusinessFunction(businessFunction);
            entityToBeSaved.setPermission(permission);

            BusinessFunctionPermission savedEntity = repository.save(entityToBeSaved);

            return new BusinessFunctionPermissionResponseBody(savedEntity.getId());
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
}
