package com.factory.persistence.users.repository;

import com.factory.persistence.users.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface RoleRepository extends CrudRepository<Role, UUID> {

    Optional<Role> findByName(String name);

    @Query("SELECT COUNT(DISTINCT r.name) = :count FROM Role r WHERE r.name IN :roleNames")
    boolean existsByRoleNames(@Param("roleNames") List<String> roleNames, @Param("count") long count);

    @Query("SELECT r FROM Role r WHERE r.name IN :roleNames")
    Set<Role> findAllRolesByName(@Param("roleNames") List<String> roleNames);
}
