package com.link.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.link.common.Result;
import com.link.entity.Role;
import com.link.entity.RoleMenu;
import com.link.mapper.RoleMenuMapper;
import com.link.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Link
 * @since 2022-08-11
 */
@Api(tags = "角色权限模块")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "新增或更新接口")
    @PostMapping
    public Result save(@RequestBody Role role) {
        roleService.saveOrUpdate(role);
        return Result.success();
    }

    @ApiOperation(value = "根据id删除角色")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        roleService.deleteById(id);
        return Result.success();
    }

    @ApiOperation(value = "根据ids删除角色")
    @PostMapping("/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        roleService.deleteByIds(ids);
        return Result.success();
    }

    @ApiOperation(value = "查询所有角色")
    @GetMapping
    public Result findAll() {
        return Result.success(roleService.list());
    }

    @ApiOperation(value = "根据id查询角色")
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(roleService.getById(id));
    }

    @ApiOperation(value = "分页查询角色")
    @GetMapping("/page")
    public Result findPage(@RequestParam String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        queryWrapper.orderByDesc("id");
        return Result.success(roleService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @ApiOperation(value = "角色和菜单绑定")
    @PostMapping("/roleMenu/{roleId}")
    public Result roleMenu(@PathVariable Integer roleId, @RequestBody List<Integer> menuIds) {
        roleService.setRoleMenu(roleId, menuIds);
        return Result.success();
    }

    @ApiOperation(value = "根据roleId获取菜单权限")
    @GetMapping("/getRoleMenu/{roleId}")
    public Result getRoleMenu(@PathVariable Integer roleId) {
        List<Integer> roleMenu = roleService.getRoleMenu(roleId);
        return Result.success(roleMenu);
    }


}

