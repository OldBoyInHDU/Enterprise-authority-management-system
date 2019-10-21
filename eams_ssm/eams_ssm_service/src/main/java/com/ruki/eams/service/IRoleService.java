package com.ruki.eams.service;


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
}
