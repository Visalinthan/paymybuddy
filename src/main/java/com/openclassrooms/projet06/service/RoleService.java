package com.openclassrooms.projet06.service;

import com.openclassrooms.projet06.model.Role;
import com.openclassrooms.projet06.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role saveRole(Role role){
        return this.roleRepository.save(role);
    }

    public Role getRolebyName(String name){ return this.roleRepository.findByRoleName(name);}
}
