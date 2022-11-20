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
import dev.cironeto.accesscontrolservice.validation.BeanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusinessFunctionPermissionService {

    private final BusinessFunctionPermissionRepository repository;
    private final BusinessFunctionRepository businessFunctionRepository;
    private final PermissionRepository permissionRepository;

    public BusinessFunctionPermissionResponseBody create(BusinessFunctionPermissionRequestBody dto) {
        BeanValidator.validate(dto);

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

            return new BusinessFunctionPermissionResponseBody(savedEntity);
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

    public List<BusinessFunctionPermissionResponseBody> findAll(){
        List<BusinessFunctionPermission> list = repository.findAll();
        return list.stream().map(p -> new BusinessFunctionPermissionResponseBody(p)).collect(Collectors.toList());
    }

    public BusinessFunctionPermissionResponseBody findById(Long id){
        Optional<BusinessFunctionPermission> optional = repository.findById(id);
        BusinessFunctionPermission entity = optional.orElseThrow(() -> new BadRequestException("ID not found"));
        return new BusinessFunctionPermissionResponseBody(entity);
    }

    public BusinessFunctionPermissionResponseBody update(Long id, BusinessFunctionPermissionRequestBody dto){
        try {
            BusinessFunctionPermission entity = repository.getById(id);
            BusinessFunction businessFunction =
                    businessFunctionRepository.findByNameAndFunction(dto.getApplicationName(), dto.getFunctionName());
            Permission permission =
                    permissionRepository.findByName(dto.getPermission());

            entity.setPermission(permission);
            entity.setBusinessFunction(businessFunction);

            BusinessFunctionPermission savedEntity = repository.save(entity);
            return new BusinessFunctionPermissionResponseBody(savedEntity);
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
