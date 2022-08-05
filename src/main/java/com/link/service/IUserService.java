package com.link.service;

import com.link.controller.dto.UserDTO;
import com.link.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Link
 * @since 2022-08-03
 */
public interface IUserService extends IService<User> {
    Integer deleteBatch(List<Integer> ids);
    UserDTO login(UserDTO user);

    Integer register(User user);
}
