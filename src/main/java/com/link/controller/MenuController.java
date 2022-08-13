package com.link.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.link.common.Result;
import com.link.entity.Dict;
import com.link.entity.Menu;
import com.link.mapper.DictMapper;
import com.link.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Link
 * @since 2022-08-11
 */
@Api(tags = "菜单模块")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private DictMapper dictMapper;

    @ApiOperation(value = "新增或更新接口")
    @PostMapping
    public Result save(@RequestBody Menu menu) {
        menuService.saveOrUpdate(menu);
        return Result.success();
    }

    @ApiOperation(value = "根据id删除菜单")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        menuService.removeById(id);
        return Result.success();
    }

    @ApiOperation(value = "批量删除菜单")
    @PostMapping("/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        menuService.removeByIds(ids);
        return Result.success();
    }

    @ApiOperation(value = "根据id查找菜单")
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(menuService.getById(id));
    }

    @ApiOperation(value = "查找所有菜单")
    @GetMapping
    public Result findAll() {
        List<Menu> parentNode = menuService.findMenus();
        return Result.success(parentNode);
    }

    @ApiOperation(value = "分页查询接口")
    @GetMapping("/page")
    public Result findPage(@RequestParam String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        return Result.success(menuService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @ApiOperation(value = "获取icon图标")
    @GetMapping("/icons")
    public Result getIcons() {
        QueryWrapper<Dict> qw = new QueryWrapper<>();
        qw.eq("type", "icon");
        return Result.success(dictMapper.selectList(qw));
    }
}

