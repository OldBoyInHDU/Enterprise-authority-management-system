package com.ruki.eams.dao;

import com.ruki.eams.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in(select permissionId from role_permission where roleId=#{roleId})")
    public List<Permission> findPermissionByRoleId(String roleId) throws Exception;

    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    @Insert("insert into permission (id, permissionName, url) values(UUID(), #{permissionName}, #{url})")
    void save(Permission permission) throws Exception;

    @Select("select * from permission where id=#{permissionId}")
    Permission findById(String permissionId) throws Exception;

    @Delete("delete from permission where id=#{permissionId}")
    void deletePermission(String permissionId) throws Exception;

    @Delete("delete from role_permission where permissionId=#{permissionId}")
    void deletePermissionFromRole_Permission(String permissionId) throws Exception;
}
