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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusinessFunctionPermissionService {

    private final BusinessFunctionPermissionRepository repository;
    private final BusinessFunctionRepository businessFunctionRepository;
    private final PermissionRepository permissionRepository;

    public BusinessFunctionPermissionResponseBody create(BusinessFunctionPermissionRequestBody dto) {
        BeanValidator.validate(dto);

        BusinessFunction businessFunction = businessFunctionRepository
                .findById(dto.getBusinessFunctionId()).orElseThrow(() -> new NotFoundException("Business Function does not exist"));

        Permission permission = permissionRepository
                .findById(dto.getPermissionId()).orElseThrow(() -> new NotFoundException("Permission does not exist"));

        BusinessFunctionPermission entity = repository.checkIfExists(businessFunction.getId(), permission.getId());
        if (entity != null){
            throw new BadRequestException(String.format("Business Function/Permission relationship already exists. ID: %d",  entity.getId()));
        }

        BusinessFunctionPermission newEntity = new BusinessFunctionPermission();
        newEntity.setBusinessFunction(businessFunction);
        newEntity.setPermission(permission);

        BusinessFunctionPermission savedEntity = repository.save(newEntity);

        return new BusinessFunctionPermissionResponseBody(savedEntity);
    }

    public List<BusinessFunctionPermissionResponseBody> findAll(){
        List<BusinessFunctionPermission> list = repository.findAll();
        return list.stream().map(p -> new BusinessFunctionPermissionResponseBody(p)).collect(Collectors.toList());
    }

    public BusinessFunctionPermissionResponseBody findById(Long id){
        BusinessFunctionPermission entity = repository.findById(id).orElseThrow(() -> new BadRequestException("ID not found"));
        return new BusinessFunctionPermissionResponseBody(entity);
    }

    public BusinessFunctionPermissionResponseBody update(Long id, BusinessFunctionPermissionRequestBody dto){
        try {
            BusinessFunctionPermission entity = repository.getById(id);
            BusinessFunction businessFunction = businessFunctionRepository
                    .findById(dto.getBusinessFunctionId()).orElseThrow(() -> new NotFoundException("Business Function does not exist"));
            Permission permission = permissionRepository
                    .findById(dto.getPermissionId()).orElseThrow(() -> new NotFoundException("Permission does not exist"));

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
