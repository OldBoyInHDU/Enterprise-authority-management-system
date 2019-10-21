package com.ruki.eams.service;

import com.ruki.eams.domain.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll() throws Exception;

    void save(Permission permission) throws Exception;

    Permission findById(String permissionId) throws Exception;

    void deletePermission(String permissionId) throws Exception;
}
