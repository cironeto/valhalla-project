package dev.cironeto.accesscontrolservice.repository;

import dev.cironeto.accesscontrolservice.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    public Permission findByName(String name);

}
