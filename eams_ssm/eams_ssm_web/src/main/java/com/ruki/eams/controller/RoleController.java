package com.ruki.eams.controller;

import com.ruki.eams.domain.Permission;
import com.ruki.eams.domain.Role;
import com.ruki.eams.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) String roleId, @RequestParam(name = "ids", required = true) String[] permissionIds) throws Exception {
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll.do";
    }

    /**
     * 根据roleId查询角色和对应的未拥有的权限
     * @param roleId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) String roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        List<Permission> permissions = roleService.findOtherPermissionsByRoleId(roleId);
        mv.addObject("role", role);
        mv.addObject("permissionList", permissions);
        mv.setViewName("role-permission-add");
        return mv;
    }
    /**
     * 查询全部角色信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findAll();
        mv.addObject("roleList", roles);
        mv.setViewName("role-list");
        return mv;
    }

    /**
     * 保存新建角色
     * @param role
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    /**
     * 根据ID查询角色
     * @param roleId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        mv.addObject("role", role);
        mv.setViewName("role-show");
        return mv;
    }

    /**
     * 根据ID删除角色
     * @param roleId
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteRoleByRoleId.do")
    public String deleteRoleByRoleId(@RequestParam(name = "id", required = true) String roleId) throws Exception {
        roleService.deleteRoleByRoleId(roleId);
        return "redirect:findAll.do";
    }

}
