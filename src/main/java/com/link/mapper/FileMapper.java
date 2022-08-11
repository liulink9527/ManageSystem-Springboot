package com.link.mapper;

import com.link.entity.FileDao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Link
 * @since 2022-08-08
 */
@Mapper
public interface FileMapper extends BaseMapper<FileDao> {

    int insertFile(FileDao fileDao);
    FileDao selectByMd5(String md5);

    FileDao selectFileById(@Param("id") Integer id);

    int updateFileIsDelete(Integer id);
    int updateFileIsDeleteByIds(Integer[] ids);

    int updateFile(FileDao fileDao);
}
