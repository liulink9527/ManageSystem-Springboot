package com.link.mapper;

import com.link.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Link
 * @since 2022-08-03
 */
public interface UserMapper extends BaseMapper<User> {
    int deleteBatchIds(List<Integer> ids);
}
