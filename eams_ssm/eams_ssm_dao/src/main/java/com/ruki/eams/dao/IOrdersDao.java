package com.ruki.eams.dao;

import com.ruki.eams.domain.Member;
import com.ruki.eams.domain.Orders;
import com.ruki.eams.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {
    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.ruki.eams.dao.IProductDao.findById"))


    })
    List<Orders> findAll() throws Exception;

    @Select("select * from orders where id=#{orderId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.ruki.eams.dao.IProductDao.findById")),
            @Result(property = "member", column = "memberId", javaType = Member.class, one = @One(select = "com.ruki.eams.dao.IMemberDao.findById")),
            @Result(property = "travellers", column = "id", javaType = java.util.List.class, many = @Many(select = "com.ruki.eams.dao.ITravellerDao.findByOrdersId"))
    })
    Orders findById(String orderId) throws Exception;
}
