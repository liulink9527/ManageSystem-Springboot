package com.link.mapper;

import com.link.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Link
 * @since 2022-08-13
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    int deleteByRoleId(@Param("roleId") Integer roleId);
    int insertRoleMenu(RoleMenu roleMenu);

    List<Integer> selectByRoleId(Integer roleId);
}
