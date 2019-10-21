package com.ruki.eams.controller;

import com.ruki.eams.domain.Permission;
import com.ruki.eams.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    /**
     * 查询所有资源权限
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mv.addObject("permissionList", permissionList);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception {
        ModelAndView mv = new ModelAndView();
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String permissionId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Permission permission = permissionService.findById(permissionId);
        mv.addObject("permission", permission);
        mv.setViewName("permission-show");
        return mv;
    }

    @RequestMapping("/deletePermission.do")
    public String deletePermission(@RequestParam(name = "id", required = true) String permissionId) throws Exception {
        permissionService.deletePermission(permissionId);
        return "redirect:findAll.do";
    }
}
