package com.ruki.eams.dao;

import com.ruki.eams.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {

    @Select("select * from traveller where id in(select travellerId from order_traveller where orderId=#{orderId})")
    List<Traveller> findByOrdersId(String ordersId) throws Exception;
}
