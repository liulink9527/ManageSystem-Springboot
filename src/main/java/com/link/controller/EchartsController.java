package com.link.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.link.common.Result;
import com.link.entity.User;
import com.link.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Link
 * @Description
 * @date 2022-08-10 11:19
 */
@RestController
@RequestMapping("/echarts")
@Api(tags = "图表模块")
public class EchartsController {

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "获取用户数据")
    @GetMapping("/user")
    public Result getUser() {
        List<User> list = userMapper.selectList(null);
        return Result.success(list);
    }

    @ApiOperation(value = "获取图表数据")
    @GetMapping
    public Result getData() {
        List<User> list = userMapper.selectList(null);
        int q1 = 0;
        int q2 = 0;
        int q3 = 0;
        int q4 = 0;
        for (User u : list) {
            Quarter quarter = DateUtil.quarterEnum(u.getCreateTime());
            switch (quarter) {
                case Q1:
                    q1 += 1;
                    break;
                case Q2:
                    q2 += 1;
                    break;
                case Q3:
                    q3 += 1;
                    break;
                case Q4:
                    q4 += 1;
                    break;
            }
        }
        return Result.success(CollUtil.newArrayList(q1, q2, q3, q4));
    }
}
