package com.ruki.eams.service.impl;

import com.ruki.eams.dao.IRoleDao;
import com.ruki.eams.domain.Permission;
import com.ruki.eams.domain.Role;
import com.ruki.eams.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;


    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role) ;
    }

    @Override
    public Role findById(String roleId) throws Exception {
        return roleDao.findById(roleId);
    }

    @Override
    public void deleteRoleByRoleId(String roleId) {
        //删除角色，要先把中间表的先删了
        roleDao.deleteFromUsers_RoleByRoleId(roleId);
        roleDao.deleteFromRole_PermissionByRoleId(roleId);
        roleDao.deleteFromRoleByRoleId(roleId);
    }

    @Override
    public List<Permission> findOtherPermissionsByRoleId(String roleId) throws Exception {
        return roleDao.findOtherPermissionsByRoleId(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for(String permissionId : permissionIds){
            roleDao.addPermissionToRole(roleId, permissionId);
        }
    }
}
