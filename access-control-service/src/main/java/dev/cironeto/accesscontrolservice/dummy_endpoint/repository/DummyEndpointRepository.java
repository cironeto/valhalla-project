package dev.cironeto.accesscontrolservice.dummy_endpoint.repository;

import dev.cironeto.accesscontrolservice.dummy_endpoint.model.DummyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DummyEndpointRepository extends JpaRepository<DummyData, Long> {
}
