package dev.cironeto.accesscontrolservice.service;

import dev.cironeto.accesscontrolservice.dto.BusinessFunctionRequestBody;
import dev.cironeto.accesscontrolservice.dto.BusinessFunctionResponseBody;
import dev.cironeto.accesscontrolservice.exception.BadRequestException;
import dev.cironeto.accesscontrolservice.model.BusinessFunction;
import dev.cironeto.accesscontrolservice.repository.BusinessFunctionRepository;
import dev.cironeto.accesscontrolservice.validation.BeanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusinessFunctionService {

    private final BusinessFunctionRepository businessFunctionRepository;

    public BusinessFunctionResponseBody create(BusinessFunctionRequestBody dto) {
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
            return new BusinessFunctionResponseBody(savedEntity);
        }
    }

    public List<BusinessFunctionResponseBody> findAll(){
        List<BusinessFunction> businessFunctions = businessFunctionRepository.findAll();
        return businessFunctions.stream().map(p -> new BusinessFunctionResponseBody(p)).collect(Collectors.toList());
    }

    public BusinessFunctionResponseBody findById(Long id){
        Optional<BusinessFunction> businessFunctionOptional = businessFunctionRepository.findById(id);
        BusinessFunction businessFunction = businessFunctionOptional.orElseThrow(() -> new BadRequestException("ID not found"));
        return new BusinessFunctionResponseBody(businessFunction);
    }

    public BusinessFunctionResponseBody update(Long id, BusinessFunctionRequestBody dto){
        try {
            BusinessFunction businessFunction = businessFunctionRepository.getById(id);
            businessFunction.setApplicationName(dto.getApplicationName());
            businessFunction.setFunctionName(dto.getFunctionName());

            BusinessFunction businessFunctionSaved = businessFunctionRepository.save(businessFunction);
            return new BusinessFunctionResponseBody(businessFunctionSaved);
        } catch (Exception e){
            throw new BadRequestException("ID not found");
        }
    }

    public void delete(Long id){
        try {
            businessFunctionRepository.deleteById(id);
        } catch (Exception e){
            throw new BadRequestException("ID not found");
        }
    }
}