package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by jiangyuanyuan on 14/12/17.
 */
public interface IFileService {


    String upload(MultipartFile file, String path);
}
