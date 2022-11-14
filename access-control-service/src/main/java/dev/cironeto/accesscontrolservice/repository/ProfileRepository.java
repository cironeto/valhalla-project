package dev.cironeto.accesscontrolservice.repository;

import dev.cironeto.accesscontrolservice.model.Permission;
import dev.cironeto.accesscontrolservice.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    public Profile findByName(String name);
}
