package com.link.service;

import com.link.entity.FileDao;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Link
 * @since 2022-08-08
 */
public interface IFileService extends IService<FileDao> {

    Boolean insertFile(FileDao fileDao);
    FileDao findFile(String md5);

    Integer deleteFile(Integer id);

    Integer deleteBatchFile(Integer[] ids);

    Integer updateFile(FileDao fileDao);
}
