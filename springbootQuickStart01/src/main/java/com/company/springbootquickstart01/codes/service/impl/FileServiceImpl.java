package com.company.springbootquickstart01.codes.service.impl;

import com.company.springbootquickstart01.codes.common.globalException.ServiceException;
import com.company.springbootquickstart01.codes.common.util.ConstantsUtil;
import com.company.springbootquickstart01.codes.common.util.ServiceUtil;
import com.company.springbootquickstart01.codes.common.util.ThreadLocalUtil;
import com.company.springbootquickstart01.codes.entity.File;
import com.company.springbootquickstart01.codes.mapper.FileMapper;
import com.company.springbootquickstart01.codes.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.springbootquickstart01.codes.vo.FileVo;
import com.company.springbootquickstart01.libs.loginRegisterProcess.normalWay.LoginVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 附件表 服务实现类
 * </p>
 *
 * @author Shon Qian
 * @since 2021-03-01
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public List<FileVo> uploadFiles(MultipartFile[] multipartFiles) {
        //1.验证图片类型
        //2.验证文件类型，是否有图片（高度、宽度）
        //3.分文件夹存储yyyy/MM/dd
        //4.防止文件重名，自定义文件名uuid.类型

        List<FileVo> list = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            String fileName = multipartFile.getOriginalFilename();
            long size = multipartFile.getSize();
            String contentType = multipartFile.getContentType();
            fileName = fileName.toLowerCase();
            //1.验证图片类型
            if(!fileName.matches("^.+\\.(jpg|png|gif)$")) {
                throw new ServiceException("图片格式错误");
            }
            try {
                BufferedImage bImage = ImageIO.read(multipartFile.getInputStream());
                int width = bImage.getWidth();
                int height = bImage.getHeight();
                //2.验证文件类型，是否有图片（高度、宽度），方法：ImageIO.read
                if(width == 0 || height == 0) {
                    throw new ServiceException("文件类型错误");
                }
//                System.out.println("是图片");
                //3.分文件夹存储yyyy/MM/dd
                String format = "yyyy/MM/dd/";
                String dateDir = LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
                String dirFilePath = ConstantsUtil.LOCAL_DIR_PATH + dateDir;
                java.io.File dirFile = new java.io.File(dirFilePath);
                if(!dirFile.exists()) {
                    dirFile.mkdirs();
                }
                //4.防止文件重名，自定义文件名uuid.类型
                String uuid = UUID.randomUUID().toString();
                String fileType = fileName.substring(fileName.lastIndexOf("."));
                String realFileName = uuid + fileType;
                //全路径，f:/xxx/xx/yyyy/MM/dd/sds.jpg
                String realFilePath = dirFilePath + realFileName;
                //文件输出
                multipartFile.transferTo(new java.io.File(realFilePath));
                //数据回显
                //虚拟地址（线上路径），url = "http://域名/图片地址";
                String url = ConstantsUtil.URL_DIR_PATH + dateDir + realFileName;
                File file = new File();
                file.setFileName(fileName);
                file.setFileSize(size);
                file.setFileType(contentType);
                LoginVo userInfo = ThreadLocalUtil.get("userInfo");
                File entity = ServiceUtil.createEntity(file, userInfo.getId());
                baseMapper.insert(entity);
                FileVo fileVo = new FileVo();
                BeanUtils.copyProperties(file,fileVo);
                list.add(fileVo);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServiceException("上传失败");
            }
        }
        return list;
    }
}
