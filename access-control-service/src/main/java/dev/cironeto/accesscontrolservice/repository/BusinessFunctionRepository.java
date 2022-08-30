package dev.cironeto.accesscontrolservice.repository;

import dev.cironeto.accesscontrolservice.model.BusinessFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessFunctionRepository extends JpaRepository<BusinessFunction, Long> {

    @Query(nativeQuery = true, value =
            "SELECT * FROM business_function " +
            "WHERE application_name = :applicationName " +
            "AND function_name = :functionName")
    public BusinessFunction findByNameAndFunction(String applicationName, String functionName);

}
