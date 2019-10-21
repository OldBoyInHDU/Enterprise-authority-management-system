package com.ruki.eams.service.impl;

import com.github.pagehelper.PageHelper;
import com.ruki.eams.dao.IOrdersDao;
import com.ruki.eams.domain.Orders;
import com.ruki.eams.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        System.out.println("page: "+page+"---"+"size: "+size);
        //pageNum是页码值，pageSize是每页显示条数
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String orderId) throws Exception {
        return ordersDao.findById(orderId);
    }
}
