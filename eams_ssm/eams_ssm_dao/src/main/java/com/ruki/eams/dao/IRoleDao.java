package com.ruki.eams.dao;

import com.ruki.eams.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IRoleDao {

    /**
     * 根据用户ID查询用户角色
     * @param userId
     * @return
     * @throws Exception
     */
    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
    List<Role> findRoleByUserId(String userId) throws Exception;
}
