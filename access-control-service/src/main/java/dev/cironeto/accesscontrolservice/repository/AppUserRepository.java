package dev.cironeto.accesscontrolservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.cironeto.accesscontrolservice.model.AppUser;

import java.util.UUID;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, UUID> {

    public AppUser findByEmail(String email);

    @Query(nativeQuery = true, value =
    "SELECT " +
        "au.email " +
    "FROM app_user au " +
        "INNER JOIN app_user_profile aup on au.id = aup.app_user_id " +
        "INNER JOIN profile p on aup.profile_id = p.id " +
        "INNER JOIN profile_business_function_permission pbfp on p.id = pbfp.profile_id " +
        "INNER JOIN business_function_permission bfp on pbfp.business_function_permission_id = bfp.id " +
        "INNER JOIN business_function bf on bfp.business_function_id = bf.id " +
        "INNER JOIN permission per on bfp.permission_id = per.id " +
    "WHERE bf.application_name = :applicationName " +
    "AND bf.function_name = :functionName " +
    "AND per.name = :permission " +
    "AND au.id = :userId"
    )
    String hasPermission(String applicationName, String functionName, String permission, String userId);
}
