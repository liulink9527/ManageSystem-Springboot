package com.link.service.impl;

import com.link.entity.Menu;
import com.link.mapper.MenuMapper;
import com.link.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Link
 * @since 2022-08-11
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> findMenus() {
        List<Menu> list = menuMapper.selectList(null);
        //找出一级菜单
        List<Menu> parentNodes = list.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
        for (Menu menu : parentNodes) {
            List<Menu> secondMenu = list.stream().filter(m ->  menu.getId().equals(m.getPid())).collect(Collectors.toList());
            menu.setChildren(secondMenu);
        }
        return parentNodes;
    }
}
