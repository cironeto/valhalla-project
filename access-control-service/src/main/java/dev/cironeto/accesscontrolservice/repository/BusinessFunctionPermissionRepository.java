package dev.cironeto.accesscontrolservice.repository;

import dev.cironeto.accesscontrolservice.model.BusinessFunctionPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessFunctionPermissionRepository extends JpaRepository<BusinessFunctionPermission, Long> {

    @Query(nativeQuery = true, value =
            "SELECT * FROM business_function_permission " +
            "WHERE business_function_id = :businessFunctionId " +
            "AND permission_id = :permissionId ")
    public BusinessFunctionPermission checkIfExists(Long businessFunctionId, Long permissionId);
}
