package com.example.hrms.biz.role.service;

import com.example.hrms.biz.role.model.Role;
import com.example.hrms.biz.role.repository.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    public Role getRoleById(Long roleId) {
        return roleMapper.getRoleById(roleId);
    }

    public void insertRole(Role role) {
        roleMapper.insertRole(role);
    }
    public void updateRole(Role role) {
        roleMapper.updateRole(role);
    }
    public void deleteRole(Long roleId) {
        roleMapper.deleteRole(roleId);
    }
}