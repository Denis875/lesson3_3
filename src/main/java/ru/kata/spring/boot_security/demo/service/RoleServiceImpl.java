package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository rolesRepository;

    public RoleServiceImpl(RoleRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public List<Role> getAllRoles() {
        return (List<Role>) rolesRepository.findAll();
    }
}
