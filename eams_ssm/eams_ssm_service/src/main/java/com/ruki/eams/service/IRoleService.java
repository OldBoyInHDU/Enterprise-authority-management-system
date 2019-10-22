package com.ruki.eams.service;


import com.ruki.eams.domain.Permission;
import com.ruki.eams.domain.Role;

import java.util.List;

public interface IRoleService {


    /**
     * 查询所有角色信息
     * @return
     */
    List<Role> findAll() throws Exception;

    /**
     * 保存新建的角色信息
     * @param role
     */
    void save(Role role) throws Exception;

    /**
     * 根据角色Id查询角色
     * @param roleId
     * @return
     */
    Role findById(String roleId) throws Exception;

    /**
     * 删除角色
     * @param roleId
     */
    void deleteRoleByRoleId(String roleId) throws Exception;

    /**
     * 根据roleId查询其他未拥有的权限
     * @param roleId
     * @return
     */
    List<Permission> findOtherPermissionsByRoleId(String roleId) throws Exception;

    /**
     * 给当前角色添加未拥有的权限
     * @param roleId
     * @param permissionIds
     * @throws Exception
     */
    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;
}
