package com.link.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.link.common.Result;
import com.link.entity.FileDao;
import com.link.entity.User;
import com.link.mapper.FileMapper;
import com.link.service.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Link
 * @since 2022-08-08
 */
@RestController
@RequestMapping("/file")
@Api(tags = "文件模块")
public class FileController {

    @Value("${file.upload.path}")
    private String uploadPath;

    @Autowired
    private IFileService fileService;


    @ApiOperation(value = "文件上传接口")
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        //获取后缀
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();
        //先存储到磁盘
        File uploadParentFile = new File(uploadPath);
        if (!uploadParentFile.exists()) {
            uploadParentFile.mkdirs();
        }
        //定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileuuid = uuid + StrUtil.DOT + type;
        File uploadFile = new File(uploadPath, fileuuid);

        //获取文件的md5
        String md5 = SecureUtil.md5(file.getInputStream());
        //判断是否文件是否重复
        FileDao file1 = fileService.findFile(md5);
        String url;
        if (file1 != null) {
            url = file1.getUrl();
        } else {
            //把获取到的文件存储到磁盘目录中
            file.transferTo(uploadFile);
            //存储数据库
            url = "http://localhost:8081/file/download/" + fileuuid;
            FileDao fileDao = new FileDao();
            fileDao.setName(originalFilename);
            fileDao.setSize(size / 1024);
            fileDao.setType(type);
            fileDao.setUrl(url);
            fileDao.setMd5(md5);
            fileService.insertFile(fileDao);
        }

        return url;
    }

    /**
     * http://localhost:8081/file/download/9c2ac4e408dd4f56ab1562881fa0eec0.txt   这个就是下载url
     *
     * @param fileuuid
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "文件下载接口")
    @GetMapping("/download/{fileuuid}")
    public void download(@PathVariable String fileuuid, HttpServletResponse response) throws IOException {
        File uploadFile = new File(uploadPath, fileuuid);
        //设置输出流的格式
        //直接使用浏览器下载
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileuuid, "UTF-8"));
        response.setContentType("applicaiton/octet-stream");

        //读取文件字节流
        byte[] bytes = FileUtil.readBytes(uploadFile);

        //写出
        os.write(bytes);
        os.flush();
        os.close();
    }

    @ApiOperation(value = "分页查询接口 Mybatis-plus")
    @GetMapping("/page")
    public IPage<FileDao> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                                   @RequestParam(name = "name") String filename) {

        IPage<FileDao> page = new Page<>(pageNum, pageSize);
        QueryWrapper<FileDao> qw = new QueryWrapper<>();
        qw.like("name", filename);
        qw.ne("is_delete", true);
        qw.orderByDesc("id");
        IPage<FileDao> fileIPage = fileService.page(page, qw);
        return fileIPage;
    }

    @ApiOperation(value = "删除文件")
    @DeleteMapping("/{id}")
    public Result deleteFile(@PathVariable Integer id) {
        Integer res = fileService.deleteFile(id);
        return Result.success(res);
    }

    @ApiOperation(value = "批量删除文件")
    @PostMapping("/batch")
    public Result deleteBatchFile(@RequestBody Integer[] ids) {
        Integer res = fileService.deleteBatchFile(ids);
        return Result.success(res);
    }

    @ApiOperation(value = "更新文件启用状态")
    @PostMapping("/update")
    public Result update(@RequestBody FileDao fileDao) {
        Integer res = fileService.updateFile(fileDao);
        return Result.success(res);
    }

}

