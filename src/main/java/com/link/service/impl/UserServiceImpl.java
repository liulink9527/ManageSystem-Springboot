package com.link.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.link.controller.dto.UserDTO;
import com.link.entity.User;
import com.link.mapper.UserMapper;
import com.link.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Link
 * @since 2022-08-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer deleteBatch(List<Integer> ids) {
        return userMapper.deleteBatchIds(ids);
    }

    @Override
    public Boolean login(UserDTO user) {
        String username = user.getUsername();
        String password = user.getPassword();
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username", username);
        qw.eq("password", password);
        User u = userMapper.selectOne(qw);
        if (u == null) {
            return false;
        }
        return true;
    }
}
