package com.ruki.eams.controller;

import com.github.pagehelper.PageInfo;
import com.ruki.eams.domain.Orders;
import com.ruki.eams.service.IOrdersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;

    /**
     * 未分页的查询全部订单
     * @return
     * @throws Exception
     */
    /*@RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> orders = ordersService.findAll();
        mv.addObject("ordersList", orders);
        mv.setViewName("orders-list");
        return mv;
    }*/

    /**
     * 有分页的查询全部订单
     * @param page
     * @param size
     * @return
     * @throws Exception
     */


    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")// @Secured注解下，前缀不能省略
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page, size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String orderId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(orderId);
        mv.addObject("orders", orders);
        mv.setViewName("orders-show");
        return mv;
    }

}
