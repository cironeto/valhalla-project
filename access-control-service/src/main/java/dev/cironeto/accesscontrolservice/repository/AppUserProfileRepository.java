package dev.cironeto.accesscontrolservice.repository;

import dev.cironeto.accesscontrolservice.model.AppUserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppUserProfileRepository extends JpaRepository<AppUserProfile, Long> {

    @Query(nativeQuery = true, value =
            "SELECT * FROM app_user_profile " +
            "WHERE app_user_id = :appUserId " +
            "AND profile_id = :profileId ")
    public AppUserProfile checkIfExists(String appUserId, Long profileId);
}
