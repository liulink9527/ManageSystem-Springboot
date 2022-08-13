package com.link.service;

import com.link.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.link.entity.RoleMenu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Link
 * @since 2022-08-11
 */
public interface IRoleService extends IService<Role> {

    boolean deleteById(Integer id);

    boolean deleteByIds(List<Integer> ids);

    boolean setRoleMenu(Integer roleId, List<Integer> menuIds);

    List<Integer> getRoleMenu(Integer roleId);
}
