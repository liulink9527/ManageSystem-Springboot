package com.link.mapper;

import com.link.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Link
 * @since 2022-08-11
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    int deleteRoleById(Integer id);

    int deleteRoleByIds(List<Integer> ids);
}
