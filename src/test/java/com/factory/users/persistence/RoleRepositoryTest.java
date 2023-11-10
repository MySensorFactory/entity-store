package com.factory.users.persistence;

import com.factory.common.JpaIntegrationTest;
import com.factory.persistence.users.entity.Role;
import com.factory.persistence.users.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@JpaIntegrationTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void testFindByName() {
        // Given
        final String roleUser = "ROLE_USER";

        Role role = new Role();
        role.setName(roleUser);
        var result = roleRepository.save(role);

        // When
        Optional<Role> savedRole = roleRepository.findByName(roleUser);

        // Then
        assertTrue(savedRole.isPresent());
        assertEquals(result.getId(), savedRole.get().getId());
    }
}
