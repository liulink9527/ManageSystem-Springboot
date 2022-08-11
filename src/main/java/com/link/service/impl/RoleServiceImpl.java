package com.link.service.impl;

import com.link.common.Constants;
import com.link.entity.Role;
import com.link.exception.ServiceException;
import com.link.mapper.RoleMapper;
import com.link.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Link
 * @since 2022-08-11
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public boolean deleteById(Integer id) {
        try {
            int res = roleMapper.deleteRoleById(id);
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return true;
    }

    @Override
    public boolean deleteByIds(List<Integer> ids) {
        try {
            int res = roleMapper.deleteRoleByIds(ids);
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return true;
    }
}
