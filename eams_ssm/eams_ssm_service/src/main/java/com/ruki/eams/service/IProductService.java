package com.ruki.eams.service;

import com.ruki.eams.domain.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll() throws Exception;
}
