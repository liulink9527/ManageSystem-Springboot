package com.link.mapper;

import com.link.entity.FileDao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Link
 * @since 2022-08-08
 */
public interface FileMapper extends BaseMapper<FileDao> {

    int insertFile(FileDao fileDao);
    FileDao selectByMd5(String md5);
}
