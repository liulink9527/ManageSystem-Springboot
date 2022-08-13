package com.link.service;

import com.link.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Link
 * @since 2022-08-11
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus();
}
