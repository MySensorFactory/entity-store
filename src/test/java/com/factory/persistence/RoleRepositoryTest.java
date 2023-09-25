package com.factory.persistence;

import com.factory.common.JpaIntegrationTest;
import com.factory.persistence.users.entity.Role;
import com.factory.persistence.users.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@JpaIntegrationTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@Import(RoleRepositoryTest.TestConfig.class)
class RoleRepositoryTest {

    @SpringBootConfiguration
    @ComponentScan({"com.factory.persistence.users.repository", "com.factory.persistence.users.entity"})
    public static class TestConfig {
    }

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
