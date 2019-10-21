package com.ruki.eams.service;

import com.ruki.eams.domain.Orders;

import java.util.List;


public interface IOrdersService {

    List<Orders> findAll(int page, int size) throws Exception;

    Orders findById(String orderId) throws Exception;
}
