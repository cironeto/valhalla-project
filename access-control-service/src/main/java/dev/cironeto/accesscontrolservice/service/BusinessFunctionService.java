package dev.cironeto.accesscontrolservice.service;

import dev.cironeto.accesscontrolservice.dto.BusinessFunctionPostRequestBody;
import dev.cironeto.accesscontrolservice.dto.BusinessFunctionResponseBody;
import dev.cironeto.accesscontrolservice.exception.BadRequestException;
import dev.cironeto.accesscontrolservice.model.BusinessFunction;
import dev.cironeto.accesscontrolservice.repository.BusinessFunctionRepository;
import dev.cironeto.accesscontrolservice.validation.BeanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessFunctionService {

    private final BusinessFunctionRepository businessFunctionRepository;

    public BusinessFunctionResponseBody create(BusinessFunctionPostRequestBody dto) {
        BeanValidator.validate(dto);

        BusinessFunction businessFunction = businessFunctionRepository
                .findByNameAndFunction(dto.getApplicationName(), dto.getFunctionName());
        if (businessFunction != null){
            throw new BadRequestException(String.format("Business Function already exists. ID: %d",  businessFunction.getId()));
        }else {
            BusinessFunction entity = new BusinessFunction();
            entity.setApplicationName(dto.getApplicationName());
            entity.setFunctionName(dto.getFunctionName());

            BusinessFunction savedEntity = businessFunctionRepository.save(entity);
            return new BusinessFunctionResponseBody(savedEntity.getId());
        }
    }
}