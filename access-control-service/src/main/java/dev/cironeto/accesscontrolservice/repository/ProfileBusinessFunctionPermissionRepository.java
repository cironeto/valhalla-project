package dev.cironeto.accesscontrolservice.repository;

import dev.cironeto.accesscontrolservice.model.ProfileBusinessFunctionPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileBusinessFunctionPermissionRepository extends JpaRepository<ProfileBusinessFunctionPermission, Long> {

    @Query(nativeQuery = true, value =
            "SELECT * FROM profile_business_function_permission " +
            "WHERE business_function_permission_id = :businessFunctionPermissionId " +
            "AND profile_id = :profileId ")
    public ProfileBusinessFunctionPermission checkIfExists(Long businessFunctionPermissionId, Long profileId);
}
