package com.qxn.service;

import com.qxn.vo.ImageVO;
import com.qxn.vo.SysResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
@PropertySource("classpath:/properties/image.properties")//加载配置文件
public class FileServiceImpl implements FileService {
    //服务器存放路径（线下路径）
    @Value("${image.localDirPath}")
    private String localDirPath;
    //线上虚拟路径
    @Value("${image.urlDirPath}")
    private String urlDirPath;

    @Override
    public SysResult imageUpload(MultipartFile[] fileImages) {
        //1.验证图片类型
        //2.验证文件类型，是否有图片（高度、宽度）
        //3.分文件夹存储yyyy/MM/dd
        //4.防止文件重名，自定义文件名uuid.类型

        //1.验证图片类型
        List<ImageVO> list = new ArrayList<>();
        for (MultipartFile fileImage : fileImages) {
            String fileName = fileImage.getOriginalFilename();
            fileName = fileName.toLowerCase();
            if(!fileName.matches("^.+\\.(jpg|png|gif)$")) {
                return SysResult.success("图片格式错误");
            }
            try {
                //2.验证文件类型，是否有图片（高度、宽度），方法：ImageIO.read
                BufferedImage bImage = ImageIO.read(fileImage.getInputStream());
                int width = bImage.getWidth();
                int height = bImage.getHeight();
                if(width==0||height==0) {
                    return SysResult.success("文件类型错误");
                }
                System.out.println("是图片");
                //3.分文件夹存储yyyy/MM/dd
                String dateDir = new SimpleDateFormat("yyyy/MM/dd/").format(new Date());
                String dirFilePath = localDirPath + dateDir;
                File dirFile = new File(dirFilePath);
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
                fileImage.transferTo(new File(realFilePath));
                //数据回显
                //虚拟地址（线上路径），url = "http://域名/图片地址";
                String url = urlDirPath + dateDir + realFileName;
                ImageVO imageVO = new ImageVO(0,url,width,height);
                list.add(imageVO);
            } catch (IOException e) {
                e.printStackTrace();
                return SysResult.success(ImageVO.fail());
            }
        }
        return SysResult.success(list);
    }
}
