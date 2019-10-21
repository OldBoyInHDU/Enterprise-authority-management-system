package com.ruki.eams.service.impl;

import com.ruki.eams.dao.IPermissionDao;
import com.ruki.eams.domain.Permission;
import com.ruki.eams.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;
    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(String permissionId) throws Exception {
        return permissionDao.findById(permissionId);
    }

    @Override
    public void deletePermission(String permissionId) throws Exception {
        //先从中间表开始删除
        permissionDao.deletePermissionFromRole_Permission(permissionId);
        permissionDao.deletePermission(permissionId);
    }
}
