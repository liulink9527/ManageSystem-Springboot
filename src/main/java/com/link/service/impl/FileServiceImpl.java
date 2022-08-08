package com.link.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.link.common.Constants;
import com.link.entity.FileDao;
import com.link.exception.ServiceException;
import com.link.mapper.FileMapper;
import com.link.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Link
 * @since 2022-08-08
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileDao> implements IFileService {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public Boolean insertFile(FileDao fileDao) {
        try {
            fileMapper.insertFile(fileDao);
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return true;

    }

    @Override
    public FileDao findFile(String md5) {
        FileDao fileDao;
        try {
            fileDao = fileMapper.selectByMd5(md5);
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return fileDao;
    }
}
