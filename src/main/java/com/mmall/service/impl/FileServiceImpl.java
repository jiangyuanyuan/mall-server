package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by jiangyuanyuan on 14/12/17.
 */
@Service("iFileService")
public class FileServiceImpl implements IFileService{
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    /*
        上传文件
     */
    public String upload(MultipartFile file, String path){

        String fileName = file.getOriginalFilename();

        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        logger.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);

        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);

        try {
            //上传到服务器
            file.transferTo(targetFile);

            //上传到ftp服务器
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));

            //删除服务器上的文件
            targetFile.delete();


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return targetFile.getName();
    }
}
