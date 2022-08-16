package dev.cironeto.accesscontrolservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.cironeto.accesscontrolservice.model.AppUser;

import java.util.UUID;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, UUID>{

}
