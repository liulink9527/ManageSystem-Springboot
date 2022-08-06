package com.link.mapper;

import com.link.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

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
    int insertUser(User user);

    User selectUserByUsername(@Param("username") String username);
}
