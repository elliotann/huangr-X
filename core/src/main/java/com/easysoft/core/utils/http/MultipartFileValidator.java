package com.easysoft.core.utils.http;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by huangxa on 2014/6/4.
 */
public class MultipartFileValidator {
    // 总上传文件大小限制
    private static final long MAX_SIZE            = 1024 * 1024 * 100;
    // 单个传文件大小限制
    private static final long MAX_FILE_SIZE        = 1024 * 1024 * 10;
    // 可接受的文件类型
    private static final String[] ACCEPT_TYPES    = {"txt", "pdf", "doc", ".Jpg", "*.zip", "*.RAR"};
    private long maxSize = MAX_SIZE;
    private String[] acceptTypes;

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    public void setAcceptTypes(String[] acceptTypes) {
        this.acceptTypes = acceptTypes;
    }

    public void validate(MultipartFile file) throws Exception{
        Assert.notNull(file,"The multipart file is null!");
        if(file.getSize()>maxSize){
            throw new Exception("The file uploaded is out of max file size!");
        }

    }
}
