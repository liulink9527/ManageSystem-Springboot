package com.link.controller;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.link.common.Result;
import com.link.common.annotation.PassToken;
import com.link.controller.dto.UserDTO;
import com.link.entity.User;
import com.link.service.IUserService;
import com.link.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Link
 * @since 2022-08-03
 */

@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "查询所有User")
    @GetMapping
    public List<User> test() {
        List<User> list = userService.list();
        return list;
    }

    @ApiOperation(value = "添加或修改User")
    @PostMapping
    public Boolean save(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    @ApiOperation(value = "根据id删除User")
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
    }

    /**
     * @RequestParam用于接收?pageNum=1&pageSize=10这样的参数
     */
    @ApiOperation(value = "分页查询接口 Mybatis-plus")
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                                @RequestParam String username) {

        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.like("username", username);
        qw.orderByDesc("id");
        IPage<User> userIPage = userService.page(page, qw);
        return userIPage;
    }

    @ApiOperation(value = "批量删除用户")
    @DeleteMapping("/batch")
    public Integer deleteBatch(@RequestParam List<Integer> ids) {
        return userService.deleteBatch(ids);
    }

    @PassToken
    @ApiOperation(value = "导出接口")
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<User> list = userService.list();
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.addHeaderAlias("username", "用户名");
        writer.addHeaderAlias("password", "密码");
        writer.addHeaderAlias("nickname", "昵称");
        writer.addHeaderAlias("email", "邮箱");
        writer.addHeaderAlias("phone", "电话");
        writer.addHeaderAlias("address", "地址");
        writer.addHeaderAlias("createTime", "创建时间");
        //去除id
        writer.setOnlyAlias(true);

        writer.write(list, true);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }

    @PassToken
    @ApiOperation(value = "导入接口")
    @PostMapping("/import")
    public Boolean imp(MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(is);
        reader.addHeaderAlias("用户名", "username");
        reader.addHeaderAlias("密码", "password");
        reader.addHeaderAlias("昵称", "nickname");
        reader.addHeaderAlias("邮箱", "email");
        reader.addHeaderAlias("电话", "phone");
        reader.addHeaderAlias("地址", "address");
        reader.addHeaderAlias("创建时间", "createTime");
        List<User> list = reader.readAll(User.class);
        return userService.saveBatch(list);
    }

    @PassToken
    @ApiOperation(value = "登录接口")
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO user) {
        UserDTO userDTO = userService.login(user);
        return Result.success(userDTO);
    }

    @PassToken
    @ApiOperation(value = "注册接口")
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        Integer res = userService.register(user);
        return Result.success();
    }

    @ApiOperation(value = "根据username获取用户")
    @GetMapping("/findUser")
    public Result findUser(@RequestParam String username) {
        User user = userService.findUser(username);
        return Result.success(user);
    }

}

