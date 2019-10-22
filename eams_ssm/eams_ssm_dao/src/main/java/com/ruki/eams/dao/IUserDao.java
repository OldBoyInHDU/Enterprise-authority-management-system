package com.ruki.eams.dao;

import com.ruki.eams.domain.Role;
import com.ruki.eams.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    @Select("select *from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.ruki.eams.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    @Insert("insert into users(id,email,username,password,phoneNum,status) values(UUID(),#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.ruki.eams.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findById(String id);

    @Select("select * from role where id not in(select roleId from users_role where userId=#{userId})")
    List<Role> findOtherRoles(String userId) throws Exception;

    @Insert("insert into users_role (userId, roleId) values(#{userId}, #{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId) throws Exception;
}
