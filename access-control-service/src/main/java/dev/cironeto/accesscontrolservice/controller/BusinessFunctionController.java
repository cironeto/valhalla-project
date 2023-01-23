package dev.cironeto.accesscontrolservice.controller;

import dev.cironeto.accesscontrolservice.dto.BusinessFunctionRequestBody;
import dev.cironeto.accesscontrolservice.dto.BusinessFunctionResponseBody;
import dev.cironeto.accesscontrolservice.service.BusinessFunctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business-function")
public class BusinessFunctionController {

    private final BusinessFunctionService businessFunctionService;

    @PostMapping(value = "/create")
    public ResponseEntity<BusinessFunctionResponseBody> createBusinessFunction(@RequestBody BusinessFunctionRequestBody dto){
        return ResponseEntity.ok(businessFunctionService.create(dto));
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<BusinessFunctionResponseBody>> findAll(){
        return ResponseEntity.ok(businessFunctionService.findAll());
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<BusinessFunctionResponseBody> findById(@PathVariable Long id){
        return ResponseEntity.ok(businessFunctionService.findById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BusinessFunctionResponseBody> update(@PathVariable Long id, @RequestBody BusinessFunctionRequestBody dto){
        return ResponseEntity.ok(businessFunctionService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        businessFunctionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
