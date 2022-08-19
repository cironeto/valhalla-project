package dev.cironeto.accesscontrolservice.dummy_endpoint.controller;

import dev.cironeto.accesscontrolservice.dummy_endpoint.model.DummyData;
import dev.cironeto.accesscontrolservice.dummy_endpoint.repository.DummyEndpointRepository;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/access-control-service")
@RequiredArgsConstructor
public class DummyEndpointController {
    private final DummyEndpointRepository dummyEndpointRepository;

    @GetMapping("/names")
    public ResponseEntity<Page<DummyData>> findAll(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(dummyEndpointRepository.findAll(pageable));
    }

    @GetMapping("/teste")
    public String teste() {
        return "Hellooowww";
    }
}
