package com.ruki.eams.dao;

import com.ruki.eams.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 持久层
 */

public interface IProductDao {

    /**
     * 根据ID查询产品
     * @param id
     * @return
     */
    @Select("select * from product where id=#{id}")
    Product findById(String id) throws Exception;

    /**
     * 查询所有产品
     * @return
     * @throws Exception
     */
    @Select("select * from product")
    List<Product> findAll() throws Exception;

    /**
     * 新建一个产品
     * @param product
     * @throws Exception
     */
    @Insert("insert into product(id,productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(UUID(),#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;
}
