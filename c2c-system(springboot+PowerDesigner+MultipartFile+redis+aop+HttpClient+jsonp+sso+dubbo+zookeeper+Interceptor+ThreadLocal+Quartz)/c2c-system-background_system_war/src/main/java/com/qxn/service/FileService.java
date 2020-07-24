package com.qxn.service;

import com.qxn.vo.SysResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    SysResult imageUpload(MultipartFile[] fileImages);
}
