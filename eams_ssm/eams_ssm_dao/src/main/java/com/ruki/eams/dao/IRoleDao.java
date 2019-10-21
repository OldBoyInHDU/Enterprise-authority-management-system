package com.ruki.eams.dao;

import com.ruki.eams.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    /**
     * 根据用户ID查询用户角色
     * @param userId
     * @return
     * @throws Exception
     */
    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.ruki.eams.dao.IPermissionDao.findPermissionByRoleId"))
    })
    List<Role> findRoleByUserId(String userId) throws Exception;

    /**
     * 查询全部角色信息
     * @return
     */
    @Select("select * from role")
    List<Role> findAll() throws Exception;

    /**
     * 新建角色信息
     * @param role
     */
    @Insert("insert into role(id, roleName, roleDesc) values(UUID(), #{roleName}, #{roleDesc})")
    void save(Role role) throws Exception;

    /**
     * 根据roleId查询角色
     * @param roleId
     * @return
     */
    @Select("select * from role where id=#{roleId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.ruki.eams.dao.IPermissionDao.findPermissionByRoleId"))
    })
    Role findById(String roleId) throws Exception;

    @Delete("delete from role where id=#{roleId}")
    void deleteFromRoleByRoleId(String roleId);

    @Delete("delete from users_role where roleId=#{roleId}")
    void deleteFromUsers_RoleByRoleId(String roleId);

    @Delete("delete from role_permission where roleId=#{roleId}")
    void deleteFromRole_PermissionByRoleId(String roleId);
}
