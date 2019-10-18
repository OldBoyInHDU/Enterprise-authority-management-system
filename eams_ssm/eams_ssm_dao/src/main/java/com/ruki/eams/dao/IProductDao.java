package com.ruki.eams.dao;

import com.ruki.eams.domain.Product;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 持久层
 */

public interface IProductDao {
    @Select("select * from product")
    List<Product> findAll() throws Exception;
}
